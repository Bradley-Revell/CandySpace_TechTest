package com.example.candyspace_tech

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.candyspace_tech.Repository.Repository


class FirstFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getUsers()

        //When view loads, this api call is processed
        viewModel.users.observe(requireActivity(), { response ->
            if(response.isSuccessful){
                Log.d("Response - Success", response.body()?.items.toString())

                //CREATE THE MONTH LIST ADAPTER
                val rv_user = view.findViewById<RecyclerView>(R.id.recyclerView_Users)
                rv_user.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )

                rv_user.adapter = response.body()?.items?.let { RecyclerAdapter_Users(it) }

            } else {
                Toast.makeText(requireContext(),"An error has occurred please try again later", Toast.LENGTH_LONG).show()
                Log.d("Response - Err", response.errorBody().toString())
                Log.e("Error", response.code().toString())
            }
        })

        val button_search = view.findViewById<Button>(R.id.button_NameSearch)
        val editText_name = view.findViewById<EditText>(R.id.editText_NameSearch)

        //Handles what happens when the user searches for a name
        button_search.setOnClickListener {

            val textToSearch = editText_name.text.toString()
            //Asks the user for a name if not already supplied
            if(textToSearch.count() == 0){
                Toast.makeText(requireContext(), "Please enter a name and try again", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //runs the api call
            viewModel.getUsersByName(textToSearch)


            viewModel.users.observe(requireActivity(), { response ->
                if (response.isSuccessful) {
                    //CREATE THE MONTH LIST ADAPTER
                    val rv_user = view.findViewById<RecyclerView>(R.id.recyclerView_Users)
                    rv_user.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL,
                        false
                    )

                    rv_user.adapter = response.body()?.items?.let { RecyclerAdapter_Users(it) }

                } else {
                    Toast.makeText(requireContext(),"No users found by that name", Toast.LENGTH_SHORT).show()
                    Log.d("Response - Err", response.errorBody().toString())
                    Log.e("Error", response.code().toString())
                }
            })
        }


    }
}