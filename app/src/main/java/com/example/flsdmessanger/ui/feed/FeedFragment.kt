package com.example.flsdmessanger.ui.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flsdmessanger.R
import com.example.flsdmessanger.databinding.FeedFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class FeedFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FeedFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_new_message -> {

            }

            R.id.menu_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToRegistrationFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }


}