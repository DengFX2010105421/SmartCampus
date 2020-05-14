# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties { *; }

# If you DO use SQLCipher:
-keep class org.greenrobot.greendao.database.SqlCipherEncryptedHelper { *; }

# If you do NOT use SQLCipher:
-dontwarn net.sqlcipher.database.**
# If you do NOT use RxJava:
-dontwarn rx.**

# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep public class * extends android.widget.LinearLayout{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}

#---------------------------------2.第三方包-------------------------------

#Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
   @butterknife.* <methods>;
}

#OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#rxgalleryfinal
-keep class cn.finalteam.rxgalleryfinal.ui.widget** { *; }
-dontwarn cn.finalteam.rxgalleryfinal.**

#picasso
-dontwarn com.squareup.okhttp.**

#fresco
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}
-dontwarn com.facebook.infer.**
-dontwarn javax.annotation.**

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#universalimageloader
-keep class com.nostra13.universalimageloader.** { *; }
-keepclassmembers class com.nostra13.universalimageloader.** {*;}

#eventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#retrofit
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

#RxJava RxAndroid
#ProGuard rules for RxJava v2 are not required

#banner
-keep class com.youth.banner.** {    *;
}

#support-v7-appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

-keepclassmembers class * extends android.webkit.WebChromeClient {
    public void openFileChooser(...);
    public void onShowFileChooser(...);
}

#请避免混淆Bugly，在Proguard混淆文件中增加以下配置
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# 保留源文件名及行号
-keepattributes SourceFile,LineNumberTable

-dontwarn org.bouncycastle.**
-keep class org.bouncycastle.**{*;}

-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}

#sharesdk 分享
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class com.mob.**{*;}
-keep class com.bytedance.**{*;}
-dontwarn cn.sharesdk.**
-dontwarn com.sina.**
-dontwarn com.mob.**

-keep class com.google.**{*;}
#-keep class sun.misc.Unsafe { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }

#---------------------------------支付宝-------------------------------

#-------------------------------------------------------------------------

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-dontwarn android.net.**
-keep class android.net.SSLCertificateSocketFactory{*;}
# For retrolambda
-dontwarn java.lang.invoke.*
-dontwarn android.os.**
-dontwarn com.android.internal.**
-keep class cn.tongdun.android.**{*;}

#---------------------------------微信-------------------------------
-keep class com.tencent.mm.opensdk.** {*;}
-keep class com.tencent.wxop.** {*;}
-keep class com.tencent.mm.sdk.** {*;}
#-------------------------------------------------------------------------

#---------------------------------新浪微博-------------------------------
-keep class com.sina.weibo.sdk.** { *; }
#-------------------------------------------------------------------------

#---------------------------------ocr、活体------------------------------
-keep class com.oliveapp.camerasdk.** {*;}
#-------------------------------------------------------------------------

#---------------------------------友盟-------------------------------
-keep class com.umeng.commonsdk.** {*;}
-keep class com.umeng.analytics.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
#-------------------------------------------------------------------------

#---------------------------------通付盾-------------------------------
-keep class com.payegis.caesar.sdk.** {*;}
#-------------------------------------------------------------------------

#---------------------------------极光推送-------------------------------
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
#-------------------------------------------------------------------------

#---------------------------------百度地图-------------------------------
-keep class com.baidu.** { *; }
-dontwarn com.baidu.**
#-------------------------------------------------------------------------

#---------------------------------拍拍贷-------------------------------
 -optimizationpasses 5
  -dontusemixedcaseclassnames
  -dontskipnonpubliclibraryclassmembers
  -dontskipnonpubliclibraryclasses
  -verbose
  -ignorewarnings
  -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

  -keep class * extends java.lang.annotation.Annotation
  -keep public class * extends android.app.Activity
  -keep public class * extends android.app.Application
  -keep public class * extends android.app.Service
  -keep public class * extends android.content.BroadcastReceiver
  -keep public class * extends android.content.ContentProvider
  -keep public class * extends android.app.backup.BackupAgentHelper
  -keep public class * extends android.preference.Preference
  -keep public class com.android.vending.licensing.ILicensingService
  -keep public class com.tendcloud.tenddata.** { public protected *;}

  # ProGuard configurations for NetworkBench Lens
  -keep class com.networkbench.** { *; }
  -dontwarn com.networkbench.**
  -keepattributes Exceptions, Signature, InnerClasses
  # End NetworkBench Lens

  -keepattributes *Annotation*
  -keepattributes JavascriptInterface

  -keepclasseswithmembernames class * {
      native <methods>;
  }

  -keepclasseswithmembernames class * {
      public <init>(android.content.Context, android.util.AttributeSet);
  }

  -keepclasseswithmembernames class * {
      public <init>(android.content.Context, android.util.AttributeSet, int);
  }
  -keepattributes Signature

  -keepclassmembers class * implements java.io.Serializable {
      static final long serialVersionUID;
      private static final java.io.ObjectStreamField[] serialPersistentFields;
      private void writeObject(java.io.ObjectOutputStream);
      private void readObject(java.io.ObjectInputStream);
      java.lang.Object writeReplace();
      java.lang.Object readResolve();
      public <fields>;
      }

  -keepclassmembers enum * {
      public static **[] values();
      public static ** valueOf(java.lang.String);
  }

  -keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
  }

  -dontwarn android.support.**
  -dontwarn com.alibaba.fastjson.**
  -dontwarn org.apache.http.**

  -keep class org.apache.http.** { *; }
  -keep class com.alibaba.fastjson.**  {* ;}
  -keep class org.apache.http.entity.mime.**  {* ;}
  -keep class com.android.volley.**  {* ;}
  -keep class net.iharder.**  {* ;}

  -keep class com.ppdai.** {*;}
  #-------------------------------------------------------------------------

  #---------------------------------vlayout-------------------------------
  -keepattributes InnerClasses
  -keep class com.alibaba.android.vlayout.ExposeLinearLayoutManagerEx { *; }
  -keep class android.support.v7.widget.RecyclerView$LayoutParams { *; }
  -keep class android.support.v7.widget.RecyclerView$ViewHolder { *; }
  -keep class android.support.v7.widget.ChildHelper { *; }
  -keep class android.support.v7.widget.ChildHelper$Bucket { *; }
  -keep class android.support.v7.widget.RecyclerView$LayoutManager { *; }
  #-------------------------------------------------------------------------
  #--------------------------------androidx混淆-----------------------------------------
  -keep class com.google.android.material.** {*;}
  -keep class androidx.** {*;}
  -keep public class * extends androidx.**
  -keep interface androidx.** {*;}
  -dontwarn com.google.android.material.**
  -dontnote com.google.android.material.**
  -dontwarn androidx.**
  #-------------------------------------------------------------------------

  #--------------------------------一键登录-----------------------------------------
  -keep class rx.** { *; }
  -keep public class com.esandinfo.android.entity.** { *; }

  # 不混淆的SDK
  -keep class com.esandinfo.mno.** { *; }
  -keep class com.esandinfo.core.** { *; }
  -keep class org.ifaa.** {*;}
  -keep class cn.com.chinatelecom.gateway.lib.** {*;}
  -keep class com.unicom.xiaowo.login.** {*;}
  -keep class com.cmic.sso.sdk.** {*;}
  -keep class com.mobile.auth.gatewayauth.** {*;}
  -keep class org.json.**{*;}
  -keep class com.google.gson.**{*;}
  -keep class com.alibaba.fastjson.** {*;}
  -ignorewarnings
  #-------------------------------------------------------------------------

-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}