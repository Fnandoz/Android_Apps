package com.example.fernando.kotlinteste

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val a: Int = 1
    val c: Int = 2
    var nome: String ?= ""
    var email: String ?= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("Olá mundo")
        toast("Soma $a e $c é ${a+c}")

        val campoNome = findViewById(R.id.campoNome) as EditText
        val campoEmail = findViewById(R.id.campoEmail) as EditText

        nome = campoNome.text.toString()
        email = campoEmail.text.toString()


    }

    fun Activity.toast(msg : String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, msg, duration).show()
    }

    fun click() {
        toast("Nome: $nome\nEmail: $email")

    }

}
