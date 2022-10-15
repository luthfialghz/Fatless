package com.izo.fatless.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.izo.fatless.data.preferences.DataPref
import com.izo.fatless.databinding.ActivityWeightBinding

class WeightActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeightBinding
    private lateinit var dataPref: DataPref
    private val FIELD_REQUIRED = "Nilai tidak boleh kosong"

    val PREF_NAME = "DATA_USER"
    val KEY_WEIGHT = "key.weight"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
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

    private fun configurationSharedPreferences() {
        val preferences = getSharedPreferences("DATA_USER", MODE_PRIVATE)
        binding.btnNext.setOnClickListener {
            val weight = binding.etAnswerWeight.text.toString()

            if (weight.isEmpty()) {
                Toast.makeText(this, FIELD_REQUIRED, Toast.LENGTH_SHORT).show()
            } else {
                saveWeight(weight.toString())
                Log.e("Weight", "Berhasil disimpan")
                startActivity(Intent(this, ChestActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_popup_enter,
                    androidx.appcompat.R.anim.abc_popup_exit
                )
            }
        }
    }

    private fun saveWeight(weight: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_WEIGHT, weight)
        editor.apply()
    }
}