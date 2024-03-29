# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
-verbose
-dontusemixedcaseclassnames

#-dontskipnonpubliclibraryclasses
-dontoptimize
-dontnote java.**, javax.**, org.**
-dontwarn android.support.**
#-overloadaggressively

#-allowaccessmodification

#-useuniqueclassmembernames

-keepattributes SourceFile, LineNumberTable, *Annotation*, Exceptions, InnerClasses

-keep class cn.tongdun.mobrisk.TDRisk {
    public static *;
    public *;
}
-keep class cn.tongdun.mobrisk.TDRisk$Builder {
    public static *;
    public *;
}
-keep class cn.tongdun.mobrisk.beans.DeviceInfo {
    private *;
}
-keep class cn.tongdun.mobrisk.providers.** {
    public *;
}
-keep interface cn.tongdun.mobrisk.TDRiskCallback {*;}

-keepclasseswithmembernames class * {
    native <methods>;
 }