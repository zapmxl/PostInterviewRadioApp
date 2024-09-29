plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.0-1.0.12"
    id("com.github.gmazzo.buildconfig") version "3.1.0"
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

        // Inject API_BASE_URL into BuildConfig from .env file
        buildConfigField("String", "API_BASE_URL", "\"${project.findProperty("API_BASE_URL")}\"")
    }

    buildFeatures {
        compose = true
        buildConfig = true // Enable BuildConfig generation
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    // UI components
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    // ViewModel with Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Retrofit for API
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Room for local storage
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Coil for image loading
    implementation(libs.coil.compose)

    // ExoPlayer for streaming
    implementation(libs.exoplayer)

    // LiveData support
    implementation(libs.androidx.runtime.livedata)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
}
