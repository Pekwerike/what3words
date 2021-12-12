package deps

object Dependency {
    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Version.ANDROIDX_CORE_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.ANDROIDX_APP_COMPAT}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
    }

    const val GOOGLE_MATERIAL_DESIGN =
        "com.google.android.material:material:${Version.GOOGLE_MATERIAL_DESIGN}"

    const val WHAT_3_WORDS_ANDROID_WRAPPER = "com.what3words:w3w-android-wrapper:${Version.WHAT_3_WORDS_ANDROID_WRAPPER}"

    const val KOTLINX_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Version.KOTLIN_COROUTINE_EXTENSION}"
}