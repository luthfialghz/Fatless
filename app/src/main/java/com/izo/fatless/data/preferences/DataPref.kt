package com.izo.fatless.data.preferences

import android.content.Context

internal class DataPref (context: Context) {
    companion object {
        const val DATA_USER = "data_pref"
        const val GENDER = "gender"
        const val AGE = "age"
        const val HEIGHT =  "height"
        const val WEIGHT =  "weight"
        const val CHEST =  "chest"
        const val ABDOMEN =  "abdomen"
        const val HIP =  "hip"
        const val THIGH =  "thigh"
        const val BICEPS =  "biceps"
    }

    val preferences = context.getSharedPreferences(DATA_USER, Context.MODE_PRIVATE)

    fun setData(value: DataModel){
        val prefEditor = preferences.edit()
        prefEditor.putString(GENDER, value.gender)
        prefEditor.putString(AGE, value.age)
        prefEditor.putString(HEIGHT, value.height)
        prefEditor.putString(WEIGHT, value.weight)
        prefEditor.putString(CHEST, value.chest)
        prefEditor.putString(ABDOMEN, value.abdomen)
        prefEditor.putString(HIP, value.hip)
        prefEditor.putString(THIGH, value.thigh)
        prefEditor.putString(BICEPS, value.biceps)
        prefEditor.apply()
    }

    fun getData(): DataModel{
        val model = DataModel()
        model.gender = preferences.getString(GENDER, "")
        model.age = preferences.getString(AGE, "")
        model.height = preferences.getString(HEIGHT, "")
        model.weight = preferences.getString(WEIGHT, "")
        model.chest = preferences.getString(CHEST, "")
        model.abdomen = preferences.getString(ABDOMEN, "")
        model.hip = preferences.getString(HIP, "")
        model.thigh = preferences.getString(THIGH, "")
        model.biceps = preferences.getString(BICEPS, "")
        return model
    }
}
