package com.izo.fatless.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.izo.fatless.MainActivity
import com.izo.fatless.data.preferences.DataPref
import com.izo.fatless.databinding.ActivityAgeBinding
import com.izo.fatless.databinding.ActivityHeightBinding

class HeightActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeightBinding
    private lateinit var dataPref: DataPref
    private val FIELD_REQUIRED = "Nilai tidak boleh kosong"

    val PREF_NAME = "DATA_USER"
    val KEY_HEIGHT = "key.height"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeightBinding.inflate(layoutInflater)
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
        binding.btnNext.setOnClickListener {
            val height = binding.etAnswerHeight.text.toString()

            if (height.isEmpty()) {
                Toast.makeText(this, FIELD_REQUIRED, Toast.LENGTH_SHORT).show()
            } else {
                saveHeight(height.toString())
                Log.e("Height", "Berhasil disimpan")
                startActivity(Intent(this, WeightActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_popup_enter,
                    androidx.appcompat.R.anim.abc_popup_exit
                )
            }
        }
    }

    private fun saveHeight(height:String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_HEIGHT, height)
        editor.apply()
    }
}