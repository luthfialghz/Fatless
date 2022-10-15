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
import com.izo.fatless.R
import com.izo.fatless.data.preferences.DataPref
import com.izo.fatless.databinding.ActivityBicepsBinding

class  BicepsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBicepsBinding
    private lateinit var dataPref: DataPref
    private val FIELD_REQUIRED = "Nilai tidak boleh kosong"

    val PREF_NAME = "DATA_USER"
    val KEY_BICEPS = "key.biceps"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBicepsBinding.inflate(layoutInflater)
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

    private fun configurationSharedPreferences() {
        val preferences = getSharedPreferences("DATA_USER", MODE_PRIVATE)
        binding.btnNext.setOnClickListener {
            val biceps = binding.etAnswerBiceps.text.toString()

            if (biceps.isEmpty()) {
                Toast.makeText(this, FIELD_REQUIRED, Toast.LENGTH_SHORT).show()
            } else {
                saveBiceps(biceps.toString())
                Log.e("Biceps", "Berhasil disimpan")
                startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_popup_enter,
                    androidx.appcompat.R.anim.abc_popup_exit
                )
            }
        }
    }

    private fun saveBiceps(biceps: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_BICEPS, biceps)
        editor.apply()
    }
}