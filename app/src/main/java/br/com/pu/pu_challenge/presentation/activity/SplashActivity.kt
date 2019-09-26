package br.com.pu.pu_challenge.presentation.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.pu.pu_challenge.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        progress.visibility = View.VISIBLE
        val handler = Handler()
        handler.postDelayed({
            progress.visibility = View.INVISIBLE
            startActivity(Intent(this@SplashActivity, DealsActivity::class.java))
            finish()
        }, 5000)
    }
}