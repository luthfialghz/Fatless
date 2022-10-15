package com.izo.fatless.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.izo.fatless.MainActivity
import com.izo.fatless.R
import com.izo.fatless.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val logoAnimation = binding.ivLogo
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)
        logoAnimation.startAnimation(slideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
            overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
        }, 3000)
    }
}