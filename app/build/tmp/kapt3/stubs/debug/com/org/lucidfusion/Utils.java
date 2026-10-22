package com.org.lucidfusion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Patterns;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import org.json.JSONException;
import org.json.JSONObject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J,\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0007J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0007J\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0018\u0010 \u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0007J\u0010\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0004H\u0007J \u0010\'\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004H\u0007J\u0018\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u0004H\u0007J\u0018\u0010,\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u0004H\u0007J\u0018\u0010-\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020/H\u0007J\u0018\u00100\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/org/lucidfusion/Utils;", "", "()V", "PREF_ACCEPT_TERMS", "", "PREF_IS_LANGAUGE_SELECTED", "PREF_LANGAUGE", "PREF_NAME", "PREF_PASSWORD", "PREF_SSID", "REQUEST_URL", "GetFormattedPrice", "nPrice", "", "appendField", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "label", "value", "unit", "getCoolingTime", "", "avgThickness", "getStringSetting", "mContext", "Landroid/content/Context;", "key", "getT1Value", "divider", "", "getT2Value", "isEmailValid", "", "email", "", "isJSONValid", "test", "setStringSetting", "valParam", "shareOnMail", "context", "shareText", "shareOnWhatsapp", "shareReport", "reportData", "Lorg/json/JSONObject;", "showToast", "message", "app_debug"})
public final class Utils {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_NAME = "com.org.calculation";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_ACCEPT_TERMS = "ACCEPTED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_LANGAUGE = "LANGAUGE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_SSID = "SSID";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_PASSWORD = "PASSWORD";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_IS_LANGAUGE_SELECTED = "IS_LANGAUGE_SELECTED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REQUEST_URL = "http://192.168.4.1/";
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.Utils INSTANCE = null;
    
    private Utils() {
        super();
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getStringSetting(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext, @org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void setStringSetting(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String valParam) {
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GetFormattedPrice(double nPrice) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final boolean isEmailValid(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence email) {
        return false;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final boolean isJSONValid(@org.jetbrains.annotations.NotNull()
    java.lang.String test) {
        return false;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final float getT1Value(float avgThickness, int divider) {
        return 0.0F;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final float getT2Value(float avgThickness, int divider) {
        return 0.0F;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final float getCoolingTime(float avgThickness) {
        return 0.0F;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void shareReport(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.json.JSONObject reportData) {
    }
    
    private final void appendField(java.lang.StringBuilder sb, java.lang.String label, java.lang.String value, java.lang.String unit) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void shareOnWhatsapp(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String shareText) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void shareOnMail(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String shareText) {
    }
}