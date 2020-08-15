package dev.steelahhh.buildsrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.1"

    const val junit = "junit:junit:4.13"

    object Kotlin {
        private const val version = "1.3.72"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Google {
        const val material = "com.google.android.material:material:1.2.0"

        object Maps {
            const val service = "com.google.android.gms:play-services-maps:17.0.0"
            const val utils = "com.google.maps.android:android-maps-utils:2.0.3"
        }
    }

    object Coroutines {
        private const val version = "1.3.8"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Coil {
        private const val version = "0.11.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object OkHttp {
        private const val version = "4.8.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Moshi {
        private const val version = "1.9.2"
        const val core = "com.squareup.moshi:moshi:$version"
        const val kotlin = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Hilt {
        private const val version = "2.28.3-alpha"
        const val library = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object AndroidX {
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val core = "androidx.core:core-ktx:1.3.1"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"

        object Navigation {
            private const val version = "2.3.0"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.3.0-alpha07"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.3.0-alpha06"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Hilt {
            private const val version = "1.0.0-alpha02"
            const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2-rc01"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }
    }
}
