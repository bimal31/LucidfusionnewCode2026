package com.org.lucidfusion;

import android.app.Application;
import com.org.lucidfusion.dbhelper.DBAdapter;
import com.org.lucidfusion.dbhelper.DBQueries;
import dagger.hilt.android.HiltAndroidApp;

@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u00a8\u0006\u0006"}, d2 = {"Lcom/org/lucidfusion/ApplicationClass;", "Landroid/app/Application;", "()V", "onCreate", "", "Companion", "app_debug"})
public final class ApplicationClass extends android.app.Application {
    private static com.org.lucidfusion.ApplicationClass instance;
    @org.jetbrains.annotations.Nullable()
    private static com.org.lucidfusion.dbhelper.DBAdapter dbAdapter;
    @org.jetbrains.annotations.Nullable()
    private static com.org.lucidfusion.dbhelper.DBQueries dbQueries;
    @org.jetbrains.annotations.Nullable()
    private static android.net.Network boundNetwork;
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.ApplicationClass.Companion Companion = null;
    
    public ApplicationClass() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.ApplicationClass getInstance() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final com.org.lucidfusion.dbhelper.DBAdapter getDbAdapter() {
        return null;
    }
    
    public static final void setDbAdapter(@org.jetbrains.annotations.Nullable()
    com.org.lucidfusion.dbhelper.DBAdapter p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final com.org.lucidfusion.dbhelper.DBQueries getDbQueries() {
        return null;
    }
    
    public static final void setDbQueries(@org.jetbrains.annotations.Nullable()
    com.org.lucidfusion.dbhelper.DBQueries p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final android.net.Network getBoundNetwork() {
        return null;
    }
    
    public static final void setBoundNetwork(@org.jetbrains.annotations.Nullable()
    android.net.Network p0) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR&\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R&\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198\u0006@BX\u0087.\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0002\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/org/lucidfusion/ApplicationClass$Companion;", "", "()V", "boundNetwork", "Landroid/net/Network;", "getBoundNetwork$annotations", "getBoundNetwork", "()Landroid/net/Network;", "setBoundNetwork", "(Landroid/net/Network;)V", "dbAdapter", "Lcom/org/lucidfusion/dbhelper/DBAdapter;", "getDbAdapter$annotations", "getDbAdapter", "()Lcom/org/lucidfusion/dbhelper/DBAdapter;", "setDbAdapter", "(Lcom/org/lucidfusion/dbhelper/DBAdapter;)V", "dbQueries", "Lcom/org/lucidfusion/dbhelper/DBQueries;", "getDbQueries$annotations", "getDbQueries", "()Lcom/org/lucidfusion/dbhelper/DBQueries;", "setDbQueries", "(Lcom/org/lucidfusion/dbhelper/DBQueries;)V", "<set-?>", "Lcom/org/lucidfusion/ApplicationClass;", "instance", "getInstance$annotations", "getInstance", "()Lcom/org/lucidfusion/ApplicationClass;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.org.lucidfusion.ApplicationClass getInstance() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        @java.lang.Deprecated()
        public static void getInstance$annotations() {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.org.lucidfusion.dbhelper.DBAdapter getDbAdapter() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        @java.lang.Deprecated()
        public static void getDbAdapter$annotations() {
        }
        
        public final void setDbAdapter(@org.jetbrains.annotations.Nullable()
        com.org.lucidfusion.dbhelper.DBAdapter p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.org.lucidfusion.dbhelper.DBQueries getDbQueries() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        @java.lang.Deprecated()
        public static void getDbQueries$annotations() {
        }
        
        public final void setDbQueries(@org.jetbrains.annotations.Nullable()
        com.org.lucidfusion.dbhelper.DBQueries p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.net.Network getBoundNetwork() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        @java.lang.Deprecated()
        public static void getBoundNetwork$annotations() {
        }
        
        public final void setBoundNetwork(@org.jetbrains.annotations.Nullable()
        android.net.Network p0) {
        }
    }
}