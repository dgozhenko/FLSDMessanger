package com.example.flsdmessanger.ui.registration

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth

class RegistrationViewModel(application: Application): AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext

    fun registerUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener

                // else if successful
                Log.d("Registration", "Registered: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}