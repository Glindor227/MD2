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
            if((etPassword.text.toString() == "1") and (etLogin.text.toString() == "1")){
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Snackbar.make(it, "Не верный пароль(${etPassword.text})(${etLogin.text})", Snackbar.LENGTH_LONG)
                    .setAction("Помочь!") {
                        etLogin.setText("1")
                        etPassword.setText("1")
                    }
                    .show()
            }
        }
    }
}
