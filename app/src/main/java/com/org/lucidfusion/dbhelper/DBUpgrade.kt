package com.org.lucidfusion.dbhelper

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DBUpgrade {

    class UpgradeTask(private val mContext: Context, private val sqLiteDatabase: SQLiteDatabase) {
        fun execute() {
            try {
                upgradePressureSizeData(sqLiteDatabase)
                upgradePipeGradeData(sqLiteDatabase)
                upgradeMachineData(sqLiteDatabase)
                upgradePipeSizeData(sqLiteDatabase)
                upgradeCalcData(sqLiteDatabase)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun upgradePressureSizeData(sqLiteDatabase: SQLiteDatabase) {
        val pressureSizeData = listOf(
            "insert into PressureSize values (8,20);",
            "insert into PressureSize values (9,25);"
        )
        for (insertStr in pressureSizeData) {
            sqLiteDatabase.execSQL(insertStr)
        }
    }

    fun upgradePipeGradeData(sqLiteDatabase: SQLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("alter table PipeGrade add Standard text")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val values = ContentValues().apply { put("Standard", "4984") }
        sqLiteDatabase.update(DBConstant.TBL_PIPEGRADE, values, null, null)

        val pipeGradeData = listOf(
            "insert into PipeGrade values (4,'PE80','PE80',4472);",
            "insert into PipeGrade values (5,'PE100','PE100',4472);"
        )
        for (insertStr in pipeGradeData) {
            sqLiteDatabase.execSQL(insertStr)
        }
    }

    fun upgradeMachineData(sqLiteDatabase: SQLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("alter table Machine add Standard text")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val values = ContentValues().apply { put("Standard", "4984") }
        sqLiteDatabase.update(DBConstant.TBL_MACHINE, values, null, null)

        val machineData = listOf(
            "insert into Machine values (9 ,'160 Intelligent Welder','50 To 160mm Jointing Pressure',4472);",
            "insert into Machine values (10,'250 Intelligent Welder','90 to 250 Jointing Pressure',4472);",
            "insert into Machine values (11,'315 Intelligent Welder','90 To 315mm Jointing Pressure',4472);",
            "insert into Machine values (12,'450 Intelligent Welder','90 To 450mm Jointing Pressure',4472);",
            "insert into Machine values (13,'630 Intelligent Welder','315 To 630mm Jointing Pressure',4472);",
            "insert into Machine values (14,'800 Intelligent Welder','450 To 800mm Jointing Pressure',4472);",
            "insert into Machine values (15,'1000 Intelligent Welder','630 To 1000mm Jointing Pressure',4472);",
            "insert into Machine values (16,'1200 Intelligent Welder','630 To 1200mm Jointing Pressure',4472);"
        )
        for (insertStr in machineData) {
            try { sqLiteDatabase.execSQL(insertStr) } catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun upgradePipeSizeData(sqLiteDatabase: SQLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("alter table PipeSize add Standard text")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val values = ContentValues().apply { put("Standard", "4984") }
        sqLiteDatabase.update(DBConstant.TBL_PIPESIZE, values, null, null)
    }

    fun upgradeCalcData(sqLiteDatabase: SQLiteDatabase) {
        // upgrade calculations when needed
    }
}
