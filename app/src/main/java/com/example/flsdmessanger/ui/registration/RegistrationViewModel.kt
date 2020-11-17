package com.example.flsdmessanger.ui.registration

import android.app.Application
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.flsdmessanger.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class RegistrationViewModel(application: Application): AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext

    fun registerUser(email: String, password: String, uri: Uri, uid: String, username: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    return@addOnCompleteListener
                } else {
                    uploadImageToFirebaseStorage(uri, uid, username)
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

   private fun uploadImageToFirebaseStorage(uri: Uri, username: String, uid: String) {
        val filename = UUID.randomUUID().toString()
        val reference = FirebaseStorage.getInstance().getReference("/images/$filename")

        reference.putFile(uri)
            .addOnSuccessListener {
                Log.d("Registration", "Successfully upload image: ${it.metadata?.path}")
                reference.downloadUrl.addOnSuccessListener {
                    it.toString()
                    Log.d("Registration", "File location: $it")
                    createUserInDatabase(uid, username, it.toString())
                }
            }
    }

    fun createUserInDatabase(uid: String, username: String, profileImage: String){
        val user = User(uid, username, profileImage)
        val uid = FirebaseAuth.getInstance().uid
        val reference = FirebaseDatabase.getInstance().getReference("/users/$uid")
        reference.setValue(user)
            .addOnSuccessListener {
                Log.d("Registration", "User created in database")
            }
    }
}