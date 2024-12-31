plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.diego.tictronome"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.diego.tictronome"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    signingConfigs {
        create("release") {
            var tmpFilePath = System.getProperty("user.home") + "/work/_temp/keystore/"
            var allFilesFromDir = File(tmpFilePath).listFiles()

            if (allFilesFromDir != null) {
                var keystoreFile = allFilesFromDir.first()
                keystoreFile.renameTo(File("keystore/your_keystore.jks"))
            }

            storeFile = file("keystore/your_keystore.jks")
            storePassword = System.getenv("SIGNING_STORE_PASSWORD")
            keyAlias = "tictronome"
            keyPassword = System.getenv("SIGNING_STORE_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("com.google.android.gms:play-services-wearable:18.2.0")
    implementation("androidx.percentlayout:percentlayout:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.wear.compose:compose-material:1.0.0")
    implementation("androidx.wear.compose:compose-foundation:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.wear.compose:compose-navigation:1.4.0")
}