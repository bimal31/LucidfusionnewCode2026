package com.org.lucidfusion.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.org.lucidfusion.MainActivity
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class DBAdapter(private val appContext: Context) : SQLiteOpenHelper(appContext, DBConstant.DB_NAME, null, DB_VERSION), DBConstant {

    private var mDataBase: SQLiteDatabase? = null

    init {
        try {
            val dbDir = appContext.getDatabasePath(DBConstant.DB_NAME).parentFile
            if (dbDir != null && !dbDir.exists()) {
                dbDir.mkdirs()
            }
            DB_PATH = (dbDir?.path ?: ("/data/data/" + appContext.packageName + "/databases")) + File.separator
            createDataBase()
            openDataBase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        this.mDataBase = db
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.e(MainActivity.TAG, "Old Version: $oldVersion")
        Log.e(MainActivity.TAG, "New Version: $newVersion")
        if (newVersion > oldVersion) {
            if (oldVersion < 2) {
                version2Update()
            }
            if (oldVersion < 4) {
                DBUpgrade.UpgradeTask(appContext, db).execute()
            }
            db.version = newVersion
        }
    }

    private fun createDataBase() {
        try {
            val dbExist = checkDataBase()
            Log.e("LUCID FUSION", "dbExist: $dbExist")
            if (dbExist) {
                openDataBase()
                val currentDb = mDataBase
                if (currentDb != null) {
                    if (currentDb.version <= OBSOLETE_DB_VERSION) {
                        val myPath = DB_PATH + DBConstant.DB_NAME
                        appContext.deleteDatabase(myPath)
                        mDataBase = null
                        copyDataBase()
                        openDataBase()
                    } else {
                        onUpgrade(currentDb, currentDb.version, DB_VERSION)
                        close()
                    }
                }
            } else {
                copyDataBase()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = appContext.getDatabasePath(DBConstant.DB_NAME)
        return dbFile.exists() && dbFile.length() > 0
    }

    private fun copyDataBase() {
        try {
            File(DB_PATH).mkdirs()
            val myInput: InputStream = appContext.assets.open(DBConstant.DB_NAME)
            val outFileName = DB_PATH + DBConstant.DB_NAME
            val myOutput: OutputStream = FileOutputStream(outFileName)
            val buffer = ByteArray(1024)
            var length: Int
            while (myInput.read(buffer).also { length = it } > 0) {
                myOutput.write(buffer, 0, length)
            }
            myOutput.flush()
            myOutput.close()
            myInput.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openDataBase() {
        try {
            val myPath = DB_PATH + DBConstant.DB_NAME
            val db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)
            mDataBase = db
            if (db.version < 3) {
                db.version = 3
            }
            onUpgrade(db, db.version, DB_VERSION)
            version2Update()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(SQLException::class)
    fun open(): DBAdapter {
        mDataBase = writableDatabase
        return this
    }

    @Synchronized
    override fun close() {
        mDataBase?.close()
        super.close()
    }

    private fun version2Update() {
        try {
            val createQuery = ("CREATE TABLE IF NOT EXISTS " + DBConstant.TBL_REPORT + " ( " +
                    DBConstant._FILENAME + " TEXT PRIMARY KEY," +
                    DBConstant._REPORT_DATA + " TEXT, " +
                    DBConstant._IS_UPLOADED + " INTEGER DEFAULT ( 0 ) " +
                    ");")
            mDataBase?.execSQL(createQuery)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun insertintoTable(tabName: String, values: ContentValues?): Long {
        return mDataBase?.insert(tabName, null, values) ?: -1
    }

    fun executeRawQuery(query: String): Cursor? {
        return mDataBase?.rawQuery(query, null)
    }

    fun updateTable(tabName: String, values: ContentValues?, whereClause: String?, whereArgs: Array<String>?): Long {
        return mDataBase?.update(tabName, values, whereClause, whereArgs)?.toLong() ?: -1
    }

    companion object {
        private var DB_PATH = ""
        private const val DB_VERSION = 4
        private const val OBSOLETE_DB_VERSION = 2

        @JvmStatic
        fun getInstance(context: Context): DBAdapter {
            return DBAdapter(context)
        }
    }
}
