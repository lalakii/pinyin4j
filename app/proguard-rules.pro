-ignorewarnings
-optimizationpasses 7
-dontusemixedcaseclassnames
-overloadaggressively
-flattenpackagehierarchy
-adaptresourcefilenames
-dontwarn java.lang.**
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static *** isLoggable(java.lang.String, ...);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
    public static java.lang.String getStackTraceString(java.lang.Throwable);}
-assumenosideeffects class java.io.PrintStream {
     public void println(%);
     public void println(...);
     public void println(**);
     public *** print(...);
     public *** println(...);}
-assumenosideeffects class java.util.logging.Logger {*;}
-assumenosideeffects class androidx.profileinstaller.**{ *;}
-assumenosideeffects class androidx.view.menu.**{ *;}
-assumenosideeffects class androidx.versionedparcelable.**{ *;}
-assumenosideeffects class androidx.activity.ImmLeaksCleaner{ *;}
-assumenosideeffects class androidx.activity.OnBackPressedDispatcher{ *;}
-assumenosideeffects class androidx.activity.OnBackPressedDispatcher$LifecycleOnBackPressedCancellable{ *;}
# -assumenosideeffects class androidx.startup.**{ *;} ignored
-assumenosideeffects class java.lang.Throwable {
    public void printStackTrace();
}