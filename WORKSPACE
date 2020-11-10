load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
http_archive(
    name = "build_bazel_rules_android",
    urls = ["https://github.com/bazelbuild/rules_android/archive/v0.1.1.zip"],
    sha256 = "cd06d15dd8bb59926e4d65f9003bfc20f9da4b2519985c27e190cddc8b7a7806",
    strip_prefix = "rules_android-0.1.1",
)

RULES_JVM_EXTERNAL_TAG = "3.3"
RULES_JVM_EXTERNAL_SHA = "d85951a92c0908c80bd8551002d66cb23c3434409c814179c0ff026b53544dab"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

rules_kotlin_version = "legacy-1.3.0"
rules_kotlin_sha = "4fd769fb0db5d3c6240df8a9500515775101964eebdf85a3f9f0511130885fde"
http_archive(
    name = "io_bazel_rules_kotlin",
    urls = ["https://github.com/bazelbuild/rules_kotlin/archive/%s.zip" % rules_kotlin_version],
    type = "zip",
    strip_prefix = "rules_kotlin-%s" % rules_kotlin_version,
    sha256 = rules_kotlin_sha,
)


FRAGMENT_VERSION = "1.2.5"
KOIN_VERSION = "2.1.5"
LIFECYCLE_VERSION = "2.2.0"
APPCENTER_SDK_VERSION = "3.3.0"
ROOM_VERSION = "2.2.5"

load("@rules_jvm_external//:defs.bzl", "maven_install")
maven_install(
    artifacts = [
        "androidx.appcompat:appcompat:1.2.0",
        "androidx.core:core-ktx:1.3.1",
        "androidx.savedstate:savedstate:1.0.0",
        "androidx.constraintlayout:constraintlayout:2.0.1",
        "androidx.legacy:legacy-support-v4:1.0.0",
        "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0",
        "androidx.fragment:fragment-ktx:" + FRAGMENT_VERSION,
        "com.google.android.material:material:1.2.1",
        "ru.terrakok.cicerone:cicerone:5.1.1",
        "org.koin:koin-core:"+KOIN_VERSION,
        "org.koin:koin-androidx-scope:"+KOIN_VERSION,
        "org.koin:koin-androidx-viewmodel:"+KOIN_VERSION,
        "org.koin:koin-androidx-fragment:"+KOIN_VERSION,
        "androidx.lifecycle:lifecycle-viewmodel-ktx:"+LIFECYCLE_VERSION,
        "androidx.lifecycle:lifecycle-livedata-ktx:"+LIFECYCLE_VERSION,
        "androidx.lifecycle:lifecycle-livedata-core:"+LIFECYCLE_VERSION,
        "androidx.lifecycle:lifecycle-runtime-ktx:"+LIFECYCLE_VERSION,
        "androidx.lifecycle:lifecycle-compiler:"+LIFECYCLE_VERSION,
        "androidx.lifecycle:lifecycle-common:"+LIFECYCLE_VERSION,
        "com.microsoft.appcenter:appcenter-analytics:"+APPCENTER_SDK_VERSION,
        "com.microsoft.appcenter:appcenter-crashes:"+APPCENTER_SDK_VERSION,
        "androidx.room:room-runtime:"+ROOM_VERSION,
        "androidx.room:room-compiler:"+ROOM_VERSION,
        "androidx.room:room-ktx:"+ROOM_VERSION
    ],
    repositories = [
        "https://maven.google.com",
        "https://jcenter.bintray.com/"
    ],
    jetify = False,
)



load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")
kotlin_repositories() # if you want the default. Otherwise see custom kotlinc distribution below
kt_register_toolchains() # to use the default toolchain, otherwise see toolchains below


load("@build_bazel_rules_android//android:rules.bzl", "android_sdk_repository")
android_sdk_repository(
    name = "androidsdk",
    path = "/Users/remych04/Library/Android/sdk",
)
