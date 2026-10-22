package com.org.lucidfusion

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.json.JSONException
import org.json.JSONObject

object Utils {

    const val PREF_NAME = "com.org.calculation"
    const val PREF_ACCEPT_TERMS = "ACCEPTED"
    const val PREF_LANGAUGE = "LANGAUGE"
    const val PREF_SSID = "SSID"
    const val PREF_PASSWORD = "PASSWORD"
    const val PREF_IS_LANGAUGE_SELECTED = "IS_LANGAUGE_SELECTED"
    const val REQUEST_URL = "http://192.168.4.1/"

    @JvmStatic
    fun getStringSetting(mContext: Context, key: String): String {
        val pref: SharedPreferences = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)
        return pref.getString(key, "") ?: ""
    }

    @JvmStatic
    fun setStringSetting(mContext: Context, key: String, valParam: String) {
        val pref: SharedPreferences = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        val valueToSave = if (valParam == "null") "" else valParam
        editor.putString(key, valueToSave)
        editor.apply()
    }

    @JvmStatic
    fun GetFormattedPrice(nPrice: Double): String {
        return String.format("$%.2f", nPrice)
    }

    @JvmStatic
    fun isEmailValid(email: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    @JvmStatic
    fun isJSONValid(test: String): Boolean {
        return try {
            JSONObject(test)
            true
        } catch (ex: JSONException) {
            false
        }
    }

    @JvmStatic
    fun getT1Value(avgThickness: Float, divider: Int): Float {
        return (6 * avgThickness) / divider
    }

    @JvmStatic
    fun getT2Value(avgThickness: Float, divider: Int): Float {
        return (15 * avgThickness) / divider
    }

    @JvmStatic
    fun getCoolingTime(avgThickness: Float): Float {
        return 1.5f * avgThickness
    }

    @JvmStatic
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    @JvmStatic
    fun shareReport(context: Context, reportData: JSONObject) {
        try {
            val stringBuilder = StringBuilder()
            stringBuilder.append("LUCID FUSION\n")
            val welderIdx = reportData.optString(AppConstants.KEY_LOGIC_WELDER, "0").toIntOrNull() ?: 0
            val welderVal = AppConstants.LOGIC_WELDER.get(welderIdx) ?: ""
            stringBuilder.append("MODEL:\t${AppConstants.KEY_LOGIC_WELDER} $welderVal\n")
            stringBuilder.append("------------------------------\n\n")

            stringBuilder.append("MACHINE ID:\t${reportData.optString(AppConstants.KEY_MACHINE_ID)}\n")
            stringBuilder.append("WELD NO:\t${reportData.optString(AppConstants.KEY_WELDNO)}\n")
            stringBuilder.append("COMPANY NAME:\t${reportData.optString(AppConstants.KEY_COMPANY_NAME)}\n")
            stringBuilder.append("DATE AND TIME:\t${reportData.optString(AppConstants.KEY_DATE_TIME)}\n")
            stringBuilder.append("WELDER NAME:\t${reportData.optString(AppConstants.KEY_WELDER_NAME)}\n")
            stringBuilder.append("PROJECT NAME:\t${reportData.optString(AppConstants.KEY_PROJECT_NAME)}\n")
            stringBuilder.append("PROJECT SITE:\t${reportData.optString(AppConstants.KEY_SITE_PLACE)}\n")
            stringBuilder.append("------------------------------\n")

            stringBuilder.append("PIPE PARAMETER\t\n\n")
            stringBuilder.append("PIPE DIAMETER:\t${reportData.optString(AppConstants.KEY_PIPE_DIA)}\n")

            try {
                val gradeIdx = reportData.optString(AppConstants.KEY_MATERIAL_GRADE).toIntOrNull() ?: 0
                stringBuilder.append("MATERIAL GRADE:\t${AppConstants.MATERIAL_GRADE.get(gradeIdx)}\n")
            } catch (e: Exception) {
                stringBuilder.append("MATERIAL GRADE:\tN/A\n")
            }

            try {
                val ratingIdx = reportData.optString(AppConstants.KEY_PIPE_PRESSURE_RATING).toIntOrNull() ?: 0
                stringBuilder.append("PIPE PRESSURE RATING:\t${AppConstants.PIPE_PRESSURE_RATING.get(ratingIdx)}\n")
            } catch (e: Exception) {
                stringBuilder.append("PIPE PRESSURE RATING:\tN/A\n")
            }

            stringBuilder.append("WALL THICKNESS(mm):\t${reportData.optString(AppConstants.KEY_WALL_THICKNESS)}\n")
            stringBuilder.append("SDR:\t${reportData.optString(AppConstants.KEY_SDR)}\n")

            try {
                val lengthIdx = reportData.optString(AppConstants.KEY_PIPE_LENGTH).toIntOrNull() ?: 0
                stringBuilder.append("PIPE LENGTH:\t${AppConstants.PIPE_LENGTH.get(lengthIdx)}\n")
            } catch (e: Exception) {
                stringBuilder.append("PIPE LENGTH:\tN/A\n")
            }

            stringBuilder.append("------------------------------\n")
            stringBuilder.append("WELDING DATA\t\n\n")

            val kgCmLabel = context.getString(R.string.lbl_kg_cm)
            val celciusLabel = context.getString(R.string.lbl_celcius_degree)
            val minSecLabel = context.getString(R.string.lbl_min_sec)

            appendField(stringBuilder, "SET DRAG PRESSURE", reportData.optString(AppConstants.KEY_SET_DRAG_PRESS), kgCmLabel)
            appendField(stringBuilder, "ACHIEVED DRAG PRESS", reportData.optString(AppConstants.KEY_ACHIVED_DRAG_PRESS), kgCmLabel)
            appendField(stringBuilder, "SET JOINT PRESS", reportData.optString(AppConstants.KEY_SET_JOINT_PRESS), kgCmLabel)
            appendField(stringBuilder, "SET PRESS @ JOINT", reportData.optString(AppConstants.KEY_SET_PRESS_AT_JOINT), kgCmLabel)
            appendField(stringBuilder, "ACHI. PRESS@ JOINT", reportData.optString(AppConstants.KEY_SET_PRESS_AT_JOINT), kgCmLabel)
            appendField(stringBuilder, "HOT PLATE TEMP", reportData.optString(AppConstants.KEY_HOT_PLATE_TEMP), celciusLabel)

            stringBuilder.append("------------------------------\n")
            stringBuilder.append("MIRROR TIME\t\n\n")

            appendField(stringBuilder, "BEAD UP TIME(T1)", reportData.optString(AppConstants.KEY_BEAD_UP_TIME), minSecLabel)
            appendField(stringBuilder, "SOAKING TIME(T2)", reportData.optString(AppConstants.KEY_SOAKING_TIME), minSecLabel)
            appendField(stringBuilder, "CHANGEOVER TIME", reportData.optString(AppConstants.KEY_CHANGEOVER_TIME), minSecLabel)
            appendField(stringBuilder, "COLLING TIME", reportData.optString(AppConstants.KEY_COOLING_TIME), minSecLabel)

            stringBuilder.append("\nCOMMENT:\t${reportData.optString(AppConstants.KEY_COMMENT)}\n")

            val items = arrayOf<CharSequence>("Mail", "WhatsApp")
            AlertDialog.Builder(context)
                .setTitle("Share via")
                .setItems(items) { _, which ->
                    if (which == 0) {
                        shareOnMail(context, stringBuilder.toString())
                    } else {
                        shareOnWhatsapp(context, stringBuilder.toString())
                    }
                }
                .show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun appendField(sb: StringBuilder, label: String, value: String, unit: String) {
        if (value != "N/A" && value.isNotBlank()) {
            sb.append("$label:\t$value $unit\n")
        } else {
            sb.append("$label:\t$value\n")
        }
    }

    @JvmStatic
    fun shareOnWhatsapp(context: Context, shareText: String) {
        try {
            val whatsappIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                setPackage("com.whatsapp")
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            try {
                context.startActivity(whatsappIntent)
            } catch (ex: android.content.ActivityNotFoundException) {
                AlertDialog.Builder(context)
                    .setTitle("Alert!")
                    .setMessage("WhatsApp Not installed...!!!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun shareOnMail(context: Context, shareText: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(""))
                putExtra(Intent.EXTRA_SUBJECT, "Report")
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
