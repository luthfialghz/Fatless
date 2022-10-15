package com.izo.fatless.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.izo.fatless.MainActivity
import com.izo.fatless.data.preferences.DataPref
import com.izo.fatless.databinding.ActivityGenderBinding
import com.izo.fatless.databinding.ActivityOnboardingBinding

class GenderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenderBinding
    private lateinit var dataPref: DataPref

    val PREF_NAME = "DATA_USER"
    val KEY_GENDER = "key.gender"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenderBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.btnMale.setOnClickListener {
            val male = "Laki-Laki"
            saveGender(male.toString())
            Log.e("Gender", "Berhasil disimpan")
            startActivity(Intent(this, AgeActivity::class.java))
        }
        binding.btnFemale.setOnClickListener {
            val female = "Perempuan"
            saveGender(female.toString())
            Log.e("Gender", "Berhasil disimpan")
            startActivity(Intent(this, AgeActivity::class.java))
            overridePendingTransition(androidx.appcompat.R.anim.abc_popup_enter, androidx.appcompat.R.anim.abc_popup_exit)
        }
    }

    private fun saveGender(gender:String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_GENDER, gender)
        editor.apply()
    }
}