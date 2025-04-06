plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.testapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.testapplication"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    dependencies {
        // Jetpack Compose
        implementation("androidx.activity:activity-compose:1.7.2")
        implementation("androidx.compose.ui:ui:1.5.1")
        //implementation("androidx.compose.material3:material3:1.1.2")
        implementation("androidx.compose.material:material-icons-extended")

        implementation("com.google.accompanist:accompanist-swiperefresh:0.33.2-alpha")


        implementation("com.google.accompanist:accompanist-placeholder-material:0.32.0")
        implementation("com.google.accompanist:accompanist-placeholder-material:0.31.5-beta")

        implementation("androidx.compose.foundation:foundation:1.6.0")



        implementation("androidx.compose.material3:material3:1.2.0-alpha08")
        implementation("androidx.navigation:navigation-compose:2.7.0")

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")

        // Coil for image loading
        implementation("io.coil-kt:coil-compose:2.2.2")

        // ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    }

}