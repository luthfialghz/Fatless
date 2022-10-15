package com.izo.fatless

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.izo.fatless.data.api.ApiConfig
import com.izo.fatless.data.api.ApiService
import com.izo.fatless.data.preferences.DataPref
import com.izo.fatless.data.request.RequestPredict
import com.izo.fatless.data.response.PredictResponse
import com.izo.fatless.databinding.ActivityMainBinding
import com.izo.fatless.ui.OnboardingActivity
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var sharedPref: SharedPreferences
    private lateinit var dataPref: DataPref

    val PREF_NAME = "DATA_USER"
    val KEY_GENDER = "key.gender"
    val KEY_AGE = "key.age"
    val KEY_HEIGHT = "key.height"
    val KEY_WEIGHT = "key.weight"
    val KEY_CHEST = "key.chest"
    val KEY_ABDOMEN = "key.abdomen"
    val KEY_HIP = "key.hip"
    val KEY_THIGH = "key.thigh"
    val KEY_BICEPS = "key.biceps"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        dataPref = DataPref(this)

        var gender : String? = sharedPref.getString(KEY_GENDER, null)
        var age : String? = sharedPref.getString(KEY_AGE, null)
        var height : String? = sharedPref.getString(KEY_HEIGHT, null)
        var weight : String? = sharedPref.getString(KEY_WEIGHT, null)
        var chest: String? = sharedPref.getString(KEY_CHEST, null)
        var abdomen: String? = sharedPref.getString(KEY_ABDOMEN, null)
        var hip: String? = sharedPref.getString(KEY_HIP, null)
        var thigh: String? = sharedPref.getString(KEY_THIGH, null)
        var biceps: String? = sharedPref.getString(KEY_BICEPS, null)
        val density = 1.0708

        if (gender == "Laki-Laki"){
            val imageGenderMale = binding.ivGenderDataMale
            imageGenderMale.visibility = View.VISIBLE
        }else if (gender == "Perempuan"){
            val imageGenderFemale = binding.ivGenderDataFemale
            imageGenderFemale.visibility = View.VISIBLE
        }
       binding.tvUserAge.text = age

        Log.e("Data", "{$gender},{$age},{$height},{$weight},{$chest},{$abdomen},{$hip},{$thigh},{$biceps}")
        val requestPredictItem = RequestPredict.RequestPredictItem(0.0,0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0)

        requestPredictItem.Density = chest?.toDouble()!!
        requestPredictItem.Age = age?.toInt()!!
        requestPredictItem.Height = abdomen?.toDouble()!!
        requestPredictItem.Weight = biceps?.toDouble()!!
        requestPredictItem.Chest = height?.toDouble()!!
        requestPredictItem.Abdomen = density.toDouble()
        requestPredictItem.Hip = hip?.toDouble()!!
        requestPredictItem.Thigh = thigh?.toDouble()!!
        requestPredictItem.Biceps = weight?.toDouble()!!

        val requestPredict = RequestPredict()
        requestPredict.add(requestPredictItem)

        getPredict(requestPredict, gender)


        binding.apply {
            tvHeight.text = "$height cm"
            tvWeight.text = "$weight kg"
            tvChest.text = "$chest cm"
            tvAbdomen.text = "$abdomen cm"
            tvHip.text = "$hip cm"
            tvThigh.text = "$thigh cm"
            tvBiceps.text = "$biceps cm"
        }

        binding.btnDone.setOnClickListener{
            startActivity(Intent(this, OnboardingActivity::class.java))
            overridePendingTransition(androidx.appcompat.R.anim.abc_popup_enter, androidx.appcompat.R.anim.abc_popup_exit)
            finish()
        }

    }

    override fun onBackPressed() {
        val back = Intent(this, OnboardingActivity::class.java)
        back.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(back)
        finishAffinity()
        finish()
    }


    private fun getPredict(requestPredict: RequestPredict, gender: String?) {
        showLoading(true)
        val retrofit = ApiConfig().getRetrofitClientInstance().create(ApiService::class.java)
        retrofit.getPredict(requestPredict).enqueue(object : retrofit2.Callback<PredictResponse>{
            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>
            ) {
                val responseBody = response.body()
                val prediction = responseBody?.prediction
                Log.e("MainActivity", "{${prediction.toString()}}")
                if (responseBody != null) {
                    showLoading(false)
                    binding.tvResultPredict.text = "${String.format("%.2f", prediction)}%"
                    val predictResult = prediction?.toInt()
                    binding.btnInfo.setOnClickListener {
                        showInfoDialog(predictResult, gender)
                    }

                }
                else {
                    recreate()
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                Log.e("API Failed", "Gagal fetching API")
                recreate()
            }
        })
    }

    private fun showInfoDialog(predictResult: Int?, gender: String?) {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        Log.e("InfoGender", "$gender")
        val view = layoutInflater.inflate(R.layout.information_dialog, null)
        val info = view.findViewById<TextView>(R.id.tv_infoPredict)
        if (gender == "Perempuan") {
            Log.e("InfoDialog", "Hasil : $predictResult")
            if (predictResult != null) {
                when {
                    predictResult >= 10 && predictResult <= 12 -> info?.text = "Lemak esensial"
                    predictResult >= 14 && predictResult <= 20 -> info?.text = "Atletis"
                    predictResult >= 21 && predictResult <= 24 -> info?.text = "Fitnes / Berotot"
                    predictResult >= 25 && predictResult <= 31 -> info?.text = "Normal"
                    predictResult > 32 -> info?.text = "Obesitas"
                }
            }
        } else if (gender == "Laki-Laki") {
            Log.e("InfoDialog", "Hasil : $predictResult")
            if (predictResult != null) {
                when {
                    predictResult >= 2 && predictResult <= 4 -> info?.text = "Lemak esensial"
                    predictResult >= 6 && predictResult <= 13 -> info?.text = "Atletis"
                    predictResult >= 14 && predictResult <= 17 -> info?.text = "Fitnes / Berotot"
                    predictResult >= 18 && predictResult <= 25 -> info?.text = "Normal"
                    predictResult > 25 -> info?.text = "Obesitas"
                }
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
