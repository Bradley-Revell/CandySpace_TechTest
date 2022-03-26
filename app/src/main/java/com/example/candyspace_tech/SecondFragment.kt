package com.example.candyspace_tech

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.candyspace_tech.Repository.Repository
import com.example.candyspace_tech.model.Session_User
import com.squareup.picasso.Picasso


class SecondFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    //Apply stored user data
    val userData = Session_User.userData

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call function to get top tags from API
        getUsersTopTags()

        //Apply user image
        val userAvatar = view.findViewById<ImageView>(R.id.imageView_avatar)
        Picasso
            .get().load(userData.profile_image).into(userAvatar)

        //Apply user name
        val userNameText = view.findViewById<TextView>(R.id.textView_userName)
        userNameText.text = userData.display_name

        //Apply reputation
        val reputationText = view.findViewById<TextView>(R.id.textView_reputation)
        reputationText.text = userData.reputation.toString()

        //Apply badges
        val badgesText = view.findViewById<TextView>(R.id.textView_badges)
        badgesText.text = Session_User.getBadgesText()




        view.findViewById<TextView>(R.id.textView_goBack).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    fun getUsersTopTags(){
        //RUNS API CALL TO GET TAG
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getTopTagID(userData.user_id)

        viewModel.usersTags.observe(requireActivity(), Observer { response ->
            if(response.isSuccessful){
                Log.d("Response - Success TAG", response.body()?.items.toString())

            } else {
                Log.d("Response - Err", response.errorBody().toString())
                Log.e("Error", response.code().toString())
            }
        })
    }
}