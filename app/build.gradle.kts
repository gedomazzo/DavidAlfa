import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

// Kotlin
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { load(it) }
    }
}

android {
    namespace = "com.example.davidalfa"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.davidalfa"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // Kotlin
        buildConfigField("String", "Gemini_API_Key",
            "\"${localProperties.getProperty("Gemini_API_Key", "")}\"")
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    buildFeatures {
        // Kotlin
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation("com.jjoe64:graphview:4.2.2")
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}