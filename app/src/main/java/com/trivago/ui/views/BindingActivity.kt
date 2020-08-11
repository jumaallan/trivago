package com.trivago.ui.views

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.trivago.ui.viewmodel.ThemeViewModel
import org.koin.android.ext.android.inject

/**
 * Abstract Activity to create a [ViewDataBinding] for the given type when setting the content view.
 */
abstract class BindingActivity<T : ViewDataBinding> : AppCompatActivity() {

    private val themeViewModel: ThemeViewModel by inject()

    abstract val layoutResId: Int
    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)

        themeViewModel.getAppTheme().observe(
            this,
            Observer {
                it.let {
                    when (it) {
                        "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        "System" ->
                            if (Build.VERSION.SDK_INT >= 29) {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                            } else {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                            }
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        if (::binding.isInitialized) {
            binding.unbind()
        }
        super.onDestroy()
    }
}