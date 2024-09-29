plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.0-1.0.12"  // Make sure KSP is added
}
android {
    namespace = "com.example.radiopostinterview3ds"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.radiopostinterview3ds"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true  // Enable Jetpack Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"  // Ensure this matches your Kotlin version
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    // Compose BOM (Bill of Materials) for managing Compose versions
    implementation(platform(libs.androidx.compose.bom))
    // Compose UI components
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    // ViewModel integration with Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material3.android)  // Add this line
    // Debugging and testing tools
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    // Retrofit for API
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Room for local database
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    // Coil for image loading in Compose
    implementation(libs.coil.compose)
    // Optional: ExoPlayer for audio streaming
    implementation(libs.exoplayer)
    // LiveData support for Compose
    implementation(libs.androidx.runtime.livedata)
    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
}