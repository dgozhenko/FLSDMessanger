package com.example.flsdmessanger.ui.registration

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.example.flsdmessanger.databinding.RegistrationFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.registration_fragment.*


class RegistrationFragment: Fragment() {
    private val registrationViewMode: RegistrationViewModel by lazy { ViewModelProvider(this).get(RegistrationViewModel::class.java) }
    private var selectedPhotoUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RegistrationFragmentBinding.inflate(inflater)


        binding.authTypeChange.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }

        binding.choosePhotoButton.setOnClickListener {
            Log.d("Registration", "Choose photo button clicked")
            ImagePicker.create(this)
                .returnMode(ReturnMode.ALL)
                .folderMode(true)
                .single()
                .toolbarFolderTitle("Folder")
                .toolbarImageTitle("Tap to select")
                .toolbarDoneButtonText("DONE")
                .start(0)
        }

        binding.registerButton.setOnClickListener {
            val email = email_edit_register.text.toString()
            val password = password_edit_register.text.toString()
            val username = username_edit_register.text.toString()
            val uid = FirebaseAuth.getInstance().uid

            if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                Toast.makeText(context, "Missing email or password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            registrationViewMode.registerUser(email, password, selectedPhotoUri!!, uid!!, username)
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val image = ImagePicker.getFirstImageOrNull(data)
        selectedPhotoUri = image.uri
            if (image != null) {
            Glide.with(circle_image_view)
                .load(image.uri)
                .into(circle_image_view)
        }
        choose_photo_button.alpha = 0f
        super.onActivityResult(requestCode, resultCode, data)
        }

}