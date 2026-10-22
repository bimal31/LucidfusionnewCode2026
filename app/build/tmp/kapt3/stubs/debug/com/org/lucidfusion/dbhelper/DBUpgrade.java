package com.org.lucidfusion.dbhelper;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import kotlinx.coroutines.Dispatchers;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\f"}, d2 = {"Lcom/org/lucidfusion/dbhelper/DBUpgrade;", "", "()V", "upgradeCalcData", "", "sqLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "upgradeMachineData", "upgradePipeGradeData", "upgradePipeSizeData", "upgradePressureSizeData", "UpgradeTask", "app_debug"})
public final class DBUpgrade {
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.dbhelper.DBUpgrade INSTANCE = null;
    
    private DBUpgrade() {
        super();
    }
    
    public final void upgradePressureSizeData(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
    }
    
    public final void upgradePipeGradeData(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
    }
    
    public final void upgradeMachineData(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
    }
    
    public final void upgradePipeSizeData(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
    }
    
    public final void upgradeCalcData(@org.jetbrains.annotations.NotNull()
    android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/org/lucidfusion/dbhelper/DBUpgrade$UpgradeTask;", "", "mContext", "Landroid/content/Context;", "sqLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V", "execute", "", "app_debug"})
    public static final class UpgradeTask {
        @org.jetbrains.annotations.NotNull()
        private final android.content.Context mContext = null;
        @org.jetbrains.annotations.NotNull()
        private final android.database.sqlite.SQLiteDatabase sqLiteDatabase = null;
        
        public UpgradeTask(@org.jetbrains.annotations.NotNull()
        android.content.Context mContext, @org.jetbrains.annotations.NotNull()
        android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
            super();
        }
        
        public final void execute() {
        }
    }
}