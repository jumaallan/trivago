package com.trivago.ui.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.trivago.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                val intent = Intent(applicationContext, CharacterSearchActivity::class.java)
                startActivity(intent)
                finish()
            },
            1000
        )
    }
}