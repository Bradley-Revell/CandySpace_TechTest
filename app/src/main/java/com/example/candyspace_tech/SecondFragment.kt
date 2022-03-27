package com.example.candyspace_tech

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
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

        //Apply location
        val locationText = view.findViewById<TextView>(R.id.textView_location)
        locationText.text = Session_User.getUsersLocation()
        //Apply creation date
        val creationDateText = view.findViewById<TextView>(R.id.textView_creationDate)
        creationDateText.text = Session_User.getCreationDate()


        view.findViewById<TextView>(R.id.textView_goBack).setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun getUsersTopTags(){
        //RUNS API CALL TO GET TOP TAGS
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getTopTagID(userData.user_id)

        viewModel.usersTags.observe(requireActivity()) { response ->
            if (response.isSuccessful) {
                val tags = response.body()?.items
                //CHECKS TAGS ARE NOT EMPTY
                if (tags != null) {
                    val tagText = view?.findViewById<TextView>(R.id.textView_topTags)
                    if (tagText != null) {
                        var _tagsToDisplay = ""
                        //Filters through the results getting all of the tag names
                        for (i in 0 until tags.count()) {
                            _tagsToDisplay += "${tags.get(i).tag_name},"
                        }
                        tagText.text = "Top Tags - [$_tagsToDisplay]"
                    }
                }

            } else {
                Log.d("Response - Err", response.errorBody().toString())
                Log.e("Error", response.code().toString())
            }
        }
    }


}