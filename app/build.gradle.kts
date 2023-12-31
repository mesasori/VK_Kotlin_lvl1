plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.vk_kotlin_lvl1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vk_kotlin_lvl1"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/\"")
        buildConfigField("String", "API_KEY", "\"live_EG2WcJ3UGgnxHbv1yVHRVnbZ1u5OHdEr9lQENhQwNvXoJ9N78ZTj3aOujLezwJlC\"")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val nav_version = "2.7.4"

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.nostra13.universalimageloader:universal-image-loader:1.9.5")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //json convertor factory
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}