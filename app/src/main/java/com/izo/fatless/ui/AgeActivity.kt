package com.izo.fatless.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.izo.fatless.MainActivity
import com.izo.fatless.data.preferences.DataPref
import com.izo.fatless.databinding.ActivityAgeBinding
import com.izo.fatless.databinding.ActivityGenderBinding

class AgeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgeBinding
    private lateinit var dataPref: DataPref
    private val FIELD_REQUIRED = "Nilai tidak boleh kosong"

    val PREF_NAME = "DATA_USER"
    val KEY_AGE = "key.age"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.hide()

        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        dataPref = DataPref(this)

        val buttonBack = binding.btnBack

        buttonBack.setOnClickListener {
            onBackPressed()
        }

        configurationSharedPreferences()
    }

    private fun configurationSharedPreferences(){
        val preferences = getSharedPreferences("DATA_USER", MODE_PRIVATE)
        binding.btnNext.setOnClickListener {
            val age = binding.etAnswerAge.text.toString()

            if (age.isEmpty()) {
                Toast.makeText(this, FIELD_REQUIRED, Toast.LENGTH_SHORT).show()
            } else {
                saveAge(age.toString())
                Log.e("Age", "Berhasil disimpan")
                startActivity(Intent(this, HeightActivity::class.java))
                overridePendingTransition(androidx.appcompat.R.anim.abc_popup_enter, androidx.appcompat.R.anim.abc_popup_exit)
            }

        }
    }

    private fun saveAge(age:String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_AGE, age)
        editor.apply()
    }
}