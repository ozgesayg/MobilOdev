package com.example.homeworkozge.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.homeworkozge.R
import com.example.homeworkozge.toast
import com.example.homeworkozge.ui.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        splashViewModel = ViewModelProvider(this, SplashVmProviderFactory(application)).get(SplashViewModel::class.java)
        splashViewModel.checkInternet()
        splashViewModel.intConnectionResponse.observe(this, {
            it.data?.let { conn ->
                when (conn) {
                    false -> {
                        toast("Lütfen internet bağlantınızı kontrol ediniz.",3000)
                        finish()
                    }
                    true -> {
                        initSplashAndAction()
                    }
                }
            }

        })
    }

    fun initSplashAndAction(){
        Timer().schedule(timerTask {
            Log.e(Log.ERROR.toString(),"Yeni ekrana geçiş");
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        },3000)
    }
}