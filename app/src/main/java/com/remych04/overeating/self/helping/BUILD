load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_android_library")

kt_android_library(
    name = "main_lib",
    srcs = glob(["**/*.kt"]),
    visibility = ["//app/src/main:__subpackages__"],
    custom_package = "com.remych04.overeating.self.helping",
    deps = [
            "//app/src/main:res",
            "@maven//:androidx_appcompat_appcompat",
            "@maven//:androidx_activity_activity_ktx",
            "@maven//:androidx_core_core_ktx",
            "@maven//:androidx_savedstate_savedstate",
            "@maven//:androidx_constraintlayout_constraintlayout",
            "@maven//:androidx_legacy_legacy_support_v4",
            "@maven//:androidx_fragment_fragment_ktx",
            "@maven//:com_google_android_material_material",
            "@maven//:ru_terrakok_cicerone_cicerone",
            "@maven//:org_koin_koin_core",
            "@maven//:org_koin_koin_androidx_scope",
            "@maven//:org_koin_koin_androidx_viewmodel",
            "@maven//:org_koin_koin_androidx_fragment",
            "@maven//:com_microsoft_appcenter_appcenter_analytics",
            "@maven//:com_microsoft_appcenter_appcenter_crashes",
            ":androidx_room_room_compiler_library",
            ":androidx_lifecycle_lifecycle_compiler_library"
    ]
)

java_plugin(
    name = "androidx_lifecycle_lifecycle_compiler_plugin",
    processor_class = "androidx.lifecycle.LifecycleProcessor",
    deps = [
        "@maven//:androidx_lifecycle_lifecycle_compiler",
    ],
)

java_library(
    name = "androidx_lifecycle_lifecycle_compiler_library",
    exported_plugins = [
        ":androidx_lifecycle_lifecycle_compiler_plugin",
    ],
    neverlink = True,
    exports = [
        "@maven//:androidx_lifecycle_lifecycle_viewmodel_ktx",
        "@maven//:androidx_lifecycle_lifecycle_livedata_ktx",
        "@maven//:androidx_lifecycle_lifecycle_livedata_core",
        "@maven//:androidx_lifecycle_lifecycle_runtime_ktx",
        "@maven//:androidx_lifecycle_lifecycle_common",
    ],
)

java_plugin(
    name = "androidx_room_room_compiler_plugin",
    processor_class = "androidx.room.RoomProcessor",
    deps = [
        "@maven//:androidx_room_room_compiler",
    ],
)

java_library(
    name = "androidx_room_room_compiler_library",
    exported_plugins = [
        ":androidx_room_room_compiler_plugin",
    ],
    exports = [
        "@maven//:androidx_room_room_common",
        "@maven//:androidx_room_room_runtime",
        "@maven//:androidx_room_room_ktx"
    ]
)