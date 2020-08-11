@file:Suppress("DEPRECATION")

package com.trivago.ui.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.trivago.R
import com.trivago.core.utils.makeStatusBarTransparent
import com.trivago.databinding.ActivitySplashBinding

class SplashActivity : BindingActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.window.setFlags(
//            FLAG_FULLSCREEN,
//            FLAG_FULLSCREEN
//        )

        makeStatusBarTransparent()

        Handler().postDelayed(
            {
                val intent = Intent(applicationContext, CharacterSearchActivity::class.java)
                startActivity(intent)
                finish()
            },
            2000
        )
    }

    override val layoutResId: Int
        get() = R.layout.activity_splash
}