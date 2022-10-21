package com.practica_s06

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.practica_s06.databinding.ActivityMainBinding


class Inicio : AppCompatActivity() {

    //Objeto para tgener acceso a los controles creados en la vista...
    private lateinit var binding: ActivityMainBinding
    //Objeto para realizar la comunicación con FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializo el objeto de autenticación Firebase
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btLogin.setOnClickListener { haceLogin() }
        binding.btRegistro.setOnClickListener { haceRegistro() }

    }

    private fun haceRegistro() {
        val correo = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(correo,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { //Si se hizo el registro
                    val user = auth.currentUser
                    refresca(user)
                } else {  //Si no se hizo el registro...
                    Toast.makeText(baseContext,getString(R.string.msg_fallo), Toast.LENGTH_LONG).show()
                    refresca(null)
                }
            }
    }

    private fun haceLogin() {
        val correo = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        auth.signInWithEmailAndPassword(correo,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { //Si se hizo el registro
                    val user = auth.currentUser
                    refresca(user)
                } else {  //Si no se hizo el registro...
                    Toast.makeText(baseContext,getString(R.string.msg_fallo), Toast.LENGTH_LONG).show()
                    refresca(null)
                }
            }
    }

    private fun refresca(user: FirebaseUser?) {
        if (user!=null) {
            //ME paso a la pantalla principal...
            val intent= Intent(this,Central::class.java)
            startActivity(intent)
        }
    }

    // una vez autenticado no vuelve a pedir.. sólo que cierre sesión
    override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        refresca(usuario)
    }
}