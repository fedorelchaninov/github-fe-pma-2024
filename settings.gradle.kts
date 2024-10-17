pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "github-fe-pma-2024"
include(":myapp01linearlayout")
include(":myapp002myownlinearlayout")
include(":myapp004objednavka")
include(":myapp005jetpackcompose")
include(":myapp005myownlinearlayout")
include(":myapp006moreactivities")
