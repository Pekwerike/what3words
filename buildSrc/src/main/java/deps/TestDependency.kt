package deps

object TestDependency {
    object AndroidTest {
        object AndroidX {
            const val EXPRESSO =
                "androidx.test.espresso:espresso-core:${Version.ANDROIDX_EXPRESSO_CORE}"
            const val JUNIT = "androidx.test.ext:junit:${Version.ANDROIDX_JUNIT}"
            const val TEST_RULE = "androidx.test:rules:${Version.ANDROIDX_TEST_RULES}"
        }

        const val MOCKITO = "org.mockito:mockito-android:${Version.MOCKITO}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Version.JUNIT}"

        object Mockito {
            const val MOCKITO_CORE = "org.mockito:mockito-core:${Version.MOCKITO}"
            const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Version.MOCKITO}"
        }
    }

}