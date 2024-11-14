package com.example.myapp012ajednoduchamatematika

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var TimeTextView: TextView? = null
    private var QuestionTextText: TextView? = null
    private var ScoreTextView: TextView? = null
    private var AlertTextView: TextView? = null
    private var FinalScoreTextView: TextView? = null
    private var btn0: Button? = null
    private var btn1: Button? = null
    private var btn2: Button? = null
    private var btn3: Button? = null
    private var countDownTimer: CountDownTimer? = null
    private var random: Random = Random
    private var a = 0
    private var b = 0
    private var indexOfCorrectAnswer = 0
    private var answers = ArrayList<Int>()
    private var points = 0
    private var totalQuestions = 0
    private var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cals = intent.getStringExtra("cals") ?: "+"
        TimeTextView = findViewById(R.id.TimeTextView)
        QuestionTextText = findViewById(R.id.QuestionTextText)
        ScoreTextView = findViewById(R.id.ScoreTextView)
        AlertTextView = findViewById(R.id.AlertTextView)
        btn0 = findViewById(R.id.button0)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)

        startGame()
    }

    private fun startGame() {
        points = 0
        totalQuestions = 0
        ScoreTextView?.text = "$points/$totalQuestions"
        startTimer()
        NextQuestion(cals)
    }

    private fun NextQuestion(cal: String) {
        a = random.nextInt(10)
        b = random.nextInt(10)
        QuestionTextText?.text = "$a $cal $b"
        indexOfCorrectAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..3) {
            if (indexOfCorrectAnswer == i) {
                val correctAnswer = when (cal) {
                    "+" -> a + b
                    "-" -> a - b
                    "*" -> a * b
                    "/" -> if (b != 0) a / b else 0
                    else -> 0
                }
                answers.add(correctAnswer)
            } else {
                var wrongAnswer = random.nextInt(20)
                while (wrongAnswer == a + b || wrongAnswer == a - b || wrongAnswer == a * b || (b != 0 && wrongAnswer == a / b)) {
                    wrongAnswer = random.nextInt(20)
                }
                answers.add(wrongAnswer)
            }
        }

        try {
            btn0?.text = answers[0].toString()
            btn1?.text = answers[1].toString()
            btn2?.text = answers[2].toString()
            btn3?.text = answers[3].toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun optionSelect(view: View?) {
        totalQuestions++
        if (view?.tag.toString() == indexOfCorrectAnswer.toString()) {
            points++
            AlertTextView?.text = "Correct"
        } else {
            AlertTextView?.text = "Wrong"
        }
        ScoreTextView?.text = "$points/$totalQuestions"
        NextQuestion(cals)
    }

    fun PlayAgain(view: View?) {
        // Restart the current activity
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("cals", cals)
        finish() // Close the current instance of MainActivity
        startActivity(intent) // Start a new instance of MainActivity
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(10000, 500) {
            override fun onTick(p0: Long) {
                TimeTextView?.text = "${p0 / 1000}s"
            }

            override fun onFinish() {
                TimeTextView?.text = "Konec času"
                openDialog()
            }
        }.start()
    }

    private fun openDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.win_layout, null)
        FinalScoreTextView = dialogView.findViewById(R.id.FinalScoreTextView)
        val btnPlayAgain = dialogView.findViewById<Button>(R.id.buttonPlayAgain)
        val btnBack = dialogView.findViewById<Button>(R.id.buttonBack)

        val dialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setView(dialogView)
            .create()

        FinalScoreTextView?.text = "$points/$totalQuestions"

        btnPlayAgain.setOnClickListener {
            dialog.dismiss()
            PlayAgain(it)
        }

        btnBack.setOnClickListener {
            dialog.dismiss()
            onBackPressedDispatcher.onBackPressed()
        }

        dialog.show()
    }
}
