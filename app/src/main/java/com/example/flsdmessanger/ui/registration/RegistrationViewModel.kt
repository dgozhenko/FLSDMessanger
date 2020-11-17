package com.example.flsdmessanger.ui.registration

import android.app.Application
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.flsdmessanger.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RegistrationViewModel(application: Application): AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext

    fun registerUser(email: String, password: String, uri: Uri, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(!it.isSuccessful){
                        return@addOnCompleteListener
                    } else {
                        uploadImageToFirebaseStorage(uri, username)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun uploadImageToFirebaseStorage(uri: Uri, username: String) {
        val filename = UUID.randomUUID().toString()
        val reference = FirebaseStorage.getInstance().getReference("/images/$filename")

        reference.putFile(uri)
                .addOnSuccessListener {
                    Log.d("Registration", "Successfully upload image: ${it.metadata?.path}")
                    reference.downloadUrl.addOnSuccessListener {
                        Log.d("Registration", "File location: $it")
                        createUserInDatabase(username, it.toString())
                    }
                }
    }

    private fun createUserInDatabase(username: String, profileImage: String){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val user = User(uid, username, profileImage)
        val reference = FirebaseDatabase.getInstance().getReference("/users/$uid")
        reference.setValue(user)
                .addOnSuccessListener {
                    Log.d("Registration", "User created in database")
                }
    }


}