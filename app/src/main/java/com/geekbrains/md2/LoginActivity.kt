package com.geekbrains.md2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_contex.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonEnter.setOnClickListener {
            if((etPassword.text.toString() == GOOD_PASSWORD) and (etLogin.text.toString() == GOOD_USER)){
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Snackbar.make(it, "Не верный пароль(${etPassword.text})(${etLogin.text})", Snackbar.LENGTH_LONG)
                    .setAction("Помочь!") {
                        etLogin.setText(GOOD_USER)
                        etPassword.setText(GOOD_PASSWORD)
                    }
                    .show()
            }
        }
        buttonExit.setOnClickListener {
            finish()
        }
    }

    companion object {
        private const val GOOD_USER:String = "1"
        private const val GOOD_PASSWORD:String = "1"
    }
}
