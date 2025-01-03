package com.example.myapp016avanocniappka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import org.maplibre.android.MapLibre
import org.maplibre.android.annotations.IconFactory
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var maplibreMap: MapLibreMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapLibre.getInstance(this)

        val inflater = LayoutInflater.from(this)
        val rootView = inflater.inflate(R.layout.activity_main, null)
        setContentView(rootView)

        mapView = rootView.findViewById(R.id.mapView)
        mapView.getMapAsync { map ->

            maplibreMap = map
            maplibreMap.setStyle("https://demotiles.maplibre.org/style.json")

            maplibreMap.cameraPosition = CameraPosition.Builder()
                .target(LatLng(54.5260, 15.2551))
                .zoom(3.0)
                .build()

            val santaLocations = listOf(
                Triple(LatLng(66.543, 25.847), "Santa Claus", "Location: Santa Claus Village, Finland"),
                Triple(LatLng(60.7635, 46.2993), "Ded Moroz", "Location: Veliky Ustyug, Russia"),
                Triple(LatLng(50.0755, 14.4378), "Ježíšek", "Location: Prague, Czech Republic"),
                Triple(LatLng(48.8566, 2.3522), "Père Noël", "Location: Paris, France"),
                Triple(LatLng(68.0741, 29.3151), "Joulupukki", "Location: Korvatunturi, Finland"),
                Triple(LatLng(49.4521, 11.0767), "Christkind", "Location: Nuremberg, Germany"),
                Triple(LatLng(52.3676, 4.9041), "Sinterklaas", "Location: Amsterdam, Netherlands"),
                Triple(LatLng(41.9028, 12.4964), "La Befana", "Location: Rome, Italy"),
                Triple(LatLng(36.2459, 29.9855), "Saint Nicholas", "Location: Demre, Turkey"),
                Triple(LatLng(43.3183, -1.9812), "Olentzero", "Location: San Sebastián, Basque Country"),
                Triple(LatLng(59.9139, 10.7522), "Julenissen", "Location: Oslo, Norway"),
                Triple(LatLng(-34.6037, -58.3816), "Papa Noël", "Location: Buenos Aires, Argentina"),
                Triple(LatLng(35.6895, 139.6917), "Hoteiosho", "Location: Tokyo, Japan"),
                Triple(LatLng(48.1391, 8.1972), "Knecht Ruprecht", "Location: Black Forest, Germany"),
                Triple(LatLng(52.5200, 13.4050), "Weihnachtsmann", "Location: Berlin, Germany"),
                Triple(LatLng(59.3293, 18.0686), "Tomte", "Location: Stockholm, Sweden"),
                Triple(LatLng(54.6872, 25.2797), "Kalėdų Senelis", "Location: Vilnius, Lithuania"),
                Triple(LatLng(56.9496, 24.1052), "Ziemassvētku Vecītis", "Location: Riga, Latvia"),
                Triple(LatLng(59.4370, 24.7536), "Jõuluvana", "Location: Tallinn, Estonia"),
                Triple(LatLng(42.6977, 23.3219), "Дядо Коледа (Dyado Koleda)", "Location: Sofia, Bulgaria"),
                Triple(LatLng(45.8150, 15.9819), "Djed Božićnjak", "Location: Zagreb, Croatia"),
                Triple(LatLng(47.4979, 19.0402), "Mikulás", "Location: Budapest, Hungary"),
                Triple(LatLng(44.7866, 20.4489), "Božić Bata", "Location: Belgrade, Serbia"),
                Triple(LatLng(43.8563, 18.4131), "Djed Mraz", "Location: Sarajevo, Bosnia and Herzegovina"),
                Triple(LatLng(42.0015, 21.4328), "Dedo Mraz", "Location: Skopje, North Macedonia"),
                Triple(LatLng(41.3275, 19.8189), "Babadimri", "Location: Tirana, Albania"),
                Triple(LatLng(46.7695, 23.5897), "Moș Crăciun", "Location: Cluj-Napoca, Romania"),
                Triple(LatLng(45.9432, 24.9668), "Moș Nicolae", "Location: Sibiu, Romania"),
                Triple(LatLng(46.0511, 14.5058), "Dedek Mraz", "Location: Ljubljana, Slovenia"),
                Triple(LatLng(53.9006, 27.5590), "Дед Мороз (Ded Moroz)", "Location: Minsk, Belarus"),
                Triple(LatLng(50.4501, 30.5234), "Святий Миколай (Svyatyy Mykolay)", "Location: Kyiv, Ukraine"),
                Triple(LatLng(37.9838, 23.7275), "Άγιος Βασίλης (Agios Vasilis)", "Location: Athens, Greece"),
                Triple(LatLng(52.2297, 21.0122), "Święty Mikołaj", "Location: Warsaw, Poland")
            )

            val infoIconDrawable = ResourcesCompat.getDrawable(
                this.resources,
                R.drawable.christmas_hat,
                null
            )!!

            val bitmap = infoIconDrawable.toBitmap(width = 30, height = 30)
            val icon = IconFactory.getInstance(this).fromBitmap(bitmap)

            for (location in santaLocations) {
                maplibreMap.addMarker(
                    MarkerOptions()
                        .position(location.first)
                        .title(location.second)
                        .snippet(location.third)
                        .icon(icon)
                )
            }

        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}
