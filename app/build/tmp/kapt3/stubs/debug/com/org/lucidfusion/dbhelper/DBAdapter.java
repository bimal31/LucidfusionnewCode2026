package com.org.lucidfusion.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.org.lucidfusion.MainActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u0000 %2\u00020\u00012\u00020\u0002:\u0001%B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\r\u001a\u00020\u000bH\u0002J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J \u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u0006\u0010\u001d\u001a\u00020\u0000J\u0006\u0010\u001e\u001a\u00020\u000bJ7\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010 \u001a\u0004\u0018\u00010\u00112\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\"\u00a2\u0006\u0002\u0010#J\b\u0010$\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/org/lucidfusion/dbhelper/DBAdapter;", "Landroid/database/sqlite/SQLiteOpenHelper;", "Lcom/org/lucidfusion/dbhelper/DBConstant;", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mDataBase", "Landroid/database/sqlite/SQLiteDatabase;", "checkDataBase", "", "close", "", "copyDataBase", "createDataBase", "executeRawQuery", "Landroid/database/Cursor;", "query", "", "insertintoTable", "", "tabName", "values", "Landroid/content/ContentValues;", "onCreate", "db", "onUpgrade", "oldVersion", "", "newVersion", "open", "openDataBase", "updateTable", "whereClause", "whereArgs", "", "(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)J", "version2Update", "Companion", "app_debug"})
public final class DBAdapter extends android.database.sqlite.SQLiteOpenHelper implements com.org.lucidfusion.dbhelper.DBConstant {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    @org.jetbrains.annotations.Nullable()
    private android.database.sqlite.SQLiteDatabase mDataBase;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String DB_PATH = "";
    private static final int DB_VERSION = 4;
    private static final int OBSOLETE_DB_VERSION = 2;
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.dbhelper.DBAdapter.Companion Companion = null;
    
    public DBAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super(null, null, null, 0);
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase db) {
    }
    
    @java.lang.Override()
    public void onUpgrade(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    
    private final void createDataBase() {
    }
    
    private final boolean checkDataBase() {
        return false;
    }
    
    private final void copyDataBase() {
    }
    
    public final void openDataBase() {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {android.database.SQLException.class})
    @org.jetbrains.annotations.NotNull()
    public final com.org.lucidfusion.dbhelper.DBAdapter open() throws android.database.SQLException {
        return null;
    }
    
    @java.lang.Override()
    @kotlin.jvm.Synchronized()
    public synchronized void close() {
    }
    
    private final void version2Update() {
    }
    
    public final long insertintoTable(@org.jetbrains.annotations.NotNull()
    java.lang.String tabName, @org.jetbrains.annotations.Nullable()
    android.content.ContentValues values) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.database.Cursor executeRawQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    public final long updateTable(@org.jetbrains.annotations.NotNull()
    java.lang.String tabName, @org.jetbrains.annotations.Nullable()
    android.content.ContentValues values, @org.jetbrains.annotations.Nullable()
    java.lang.String whereClause, @org.jetbrains.annotations.Nullable()
    java.lang.String[] whereArgs) {
        return 0L;
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.dbhelper.DBAdapter getInstance(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/org/lucidfusion/dbhelper/DBAdapter$Companion;", "", "()V", "DB_PATH", "", "DB_VERSION", "", "OBSOLETE_DB_VERSION", "getInstance", "Lcom/org/lucidfusion/dbhelper/DBAdapter;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic()
        @org.jetbrains.annotations.NotNull()
        public final com.org.lucidfusion.dbhelper.DBAdapter getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}