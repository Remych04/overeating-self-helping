android_binary(
     name = "app",
     manifest = "AndroidManifest.xml",
     deps = [
         ":res",
         "//app/src/main/java/com/remych04/overeating/self/helping:main_lib"
         ],
)

android_library(
    name = "res",
    resource_files = glob(["res/**"]),
    manifest = "AndroidManifest.xml",
    custom_package = "com.remych04.overeating.self.helping",
    visibility = ["//visibility:public"],
    deps = [
        "@maven//:com_google_android_material_material",
        "@maven//:androidx_swiperefreshlayout_swiperefreshlayout",
        "@maven//:androidx_constraintlayout_constraintlayout",
    ]
)

