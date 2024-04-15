buildscript {
    extra.apply {
        set("nav_version", "2.5.3")
        set("room_version", "2.5.2")
    }
}
plugins {
    id("com.android.application") version "8.3.0" apply false
    id("com.android.library") version "8.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}
