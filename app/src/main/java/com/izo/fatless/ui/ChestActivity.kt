package com.izo.fatless.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.izo.fatless.MainActivity
import com.izo.fatless.R
import com.izo.fatless.data.preferences.DataPref
import com.izo.fatless.databinding.ActivityChestBinding

class ChestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChestBinding
    private lateinit var dataPref: DataPref
    private val FIELD_REQUIRED = "Nilai tidak boleh kosong"

    val PREF_NAME = "DATA_USER"
    val KEY_CHEST = "key.chest"

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChestBinding.inflate(layoutInflater)
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
            val chest = binding.etAnswerChest.text.toString()

            if (chest.isEmpty()) {
                Toast.makeText(this, FIELD_REQUIRED, Toast.LENGTH_SHORT).show()
            } else {
                saveChest(chest.toString())
                Log.e("Chest", "Berhasil disimpan")
                startActivity(Intent(this, AbdomenActivity::class.java))
                overridePendingTransition(
                    androidx.appcompat.R.anim.abc_popup_enter,
                    androidx.appcompat.R.anim.abc_popup_exit
                )
            }
        }
    }

    private fun saveChest(chest: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_CHEST, chest)
        editor.apply()
    }
}