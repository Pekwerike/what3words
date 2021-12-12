import deps.Dependency
import deps.TestDependency

plugins {
    androidApplication
    kotlinAndroid
    kotlinKapt
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.pekwerike.what3words"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

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
    configurations {
        all {
            exclude(group = "com.google.guava", module = "listenablefuture")
        }
    }
}

dependencies {
    implementation(project(":lib"))
    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.GOOGLE_MATERIAL_DESIGN)
    implementation(Dependency.AndroidX.CONSTRAINT_LAYOUT)
    testImplementation(TestDependency.Test.JUNIT)
    androidTestImplementation(TestDependency.AndroidTest.AndroidX.JUNIT)
    androidTestImplementation(TestDependency.AndroidTest.AndroidX.EXPRESSO)
    androidTestImplementation(TestDependency.AndroidTest.AndroidX.TEST_RULE)

}