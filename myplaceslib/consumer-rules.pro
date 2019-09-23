-keepattributes SourceFile,LineNumberTable
# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keep class androidx.appcompat.widget.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
#Moshi
# JSR 305 annotations are for embedding nullability information.
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keep @com.squareup.moshi.JsonQualifier interface *
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-keep class kotlin.Metadata { *; }
-keep class com.jodhpurtechies.myplaceslib.model.** { *; }
-keepclassmembers class com.jodhpurtechies.myplaceslib.model.** {
  <init>(...);
  <fields>;
}
-keep class kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoaderImpl