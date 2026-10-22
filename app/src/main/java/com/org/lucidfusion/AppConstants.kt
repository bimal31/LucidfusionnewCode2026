package com.org.lucidfusion

import android.util.SparseArray

object AppConstants {
    const val API_URL = "https://weldreports.com/api.php?request=addreportdata&PLCNumber=%1\$s&FileName=%2\$s"

    @JvmField val KEYS: HashMap<String, String> = HashMap()

    const val KEY_FN = "FN"
    const val KEY_MACHINE_ID = "Machine ID"
    const val KEY_LOGIC_WELDER = "Logic Welder"
    const val KEY_WELDNO = "Weld No"
    const val KEY_DATE = "date"
    const val KEY_MONTH = "month"
    const val KEY_YEAR = "year"
    const val KEY_HOUR = "hour"
    const val KEY_MIN = "min"
    const val KEY_SEC = "sec"
    const val KEY_PIPE_DIA = "Pipe Dia"
    const val KEY_MATERIAL_GRADE = "Material Grade"
    const val KEY_PIPE_PRESSURE_RATING = "Pipe pressure rating"
    const val KEY_WALL_THICKNESS = "Wall thickness"
    const val KEY_SET_DRAG_PRESS = "Set drag press"
    const val KEY_ACHIVED_DRAG_PRESS = "Achived drag press"
    const val KEY_SET_JOINT_PRESS = "Set joint press"
    const val KEY_SET_PRESS_AT_JOINT = "Set press @joint"
    const val KEY_ACHI_PRESS_JOINT = "Achi. press joint"
    const val KEY_HOT_PLATE_TEMP = "Hot plate temp"
    const val KEY_BEAD_UP_TIME = "Bead up time"
    const val KEY_SOAKING_TIME = "Soaking time"
    const val KEY_CHANGEOVER_TIME = "Changeover time"
    const val KEY_COOLING_TIME = "Cooling time"
    const val KEY_COMPANY_NAME = "company_name"
    const val KEY_DATE_TIME = "date_time"
    const val KEY_WELDER_NAME = "Welder_name"
    const val KEY_PROJECT_NAME = "project_name"
    const val KEY_SITE_PLACE = "site_place"
    const val KEY_SDR = "sdr"
    const val KEY_PIPE_LENGTH = "pipe_length"
    const val KEY_COMMENT = "comment"

    @JvmField val MATERIAL_GRADE: SparseArray<String> = SparseArray()
    @JvmField val LOGIC_WELDER: SparseArray<String> = SparseArray()
    @JvmField val PIPE_PRESSURE_RATING: SparseArray<String> = SparseArray()
    @JvmField val PIPE_LENGTH: SparseArray<String> = SparseArray()
}

// Deprecated alias interface to retain legacy name compatibility
typealias AppCostants = AppConstants
