plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services) // Google Services plugin
}

android {
    namespace = "com.example.waterparkgrid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.waterparkgrid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Android dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Firebase dependencies
    implementation(libs.firebase.database) // Firebase Realtime Database
    implementation("com.google.firebase:firebase-auth:21.3.0") // Firebase Authentication

    // AndroidX dependencies
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity:1.9.3")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    // Google Maps API dependency
    implementation("com.google.android.gms:play-services-maps:18.1.0") // Add this line for Maps API
    implementation ("com.google.android.gms:play-services-location:17.0.0")

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

// Apply the Google Services plugin
apply(plugin = "com.google.gms.google-services")
