import deps.Dependency
import deps.TestDependency

plugins {
    androidLibrary
    kotlinAndroid
    kotlinKapt
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.GOOGLE_MATERIAL_DESIGN)
    testImplementation(TestDependency.Test.JUNIT)
    androidTestImplementation(TestDependency.AndroidTest.AndroidX.JUNIT)
    androidTestImplementation(TestDependency.AndroidTest.AndroidX.EXPRESSO)

    // What3Words wrapper
    implementation(Dependency.WHAT_3_WORDS_ANDROID_WRAPPER)

    // kotlin coroutine
    implementation(Dependency.KOTLINX_COROUTINES)

    // mockito
    testImplementation(TestDependency.Test.Mockito.MOCKITO_CORE)
    testImplementation(TestDependency.Test.Mockito.MOCKITO_INLINE)
    androidTestImplementation(TestDependency.AndroidTest.MOCKITO)

}