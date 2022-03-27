package com.example.candyspace_tech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candyspace_tech.Repository.Repository
import com.example.candyspace_tech.model.TopTagResponse
import com.example.candyspace_tech.model.UserResponse
import kotlinx.coroutines.launch
import retrofit2.Response

//View model handles returning the relevant api data back to the view/fragment
class MainViewModel(private val repository: Repository) : ViewModel(){

    val users: MutableLiveData<Response<UserResponse>> = MutableLiveData()
    val usersTags: MutableLiveData<Response<TopTagResponse>> = MutableLiveData()

    fun getUsers(){
        viewModelScope.launch{
            val response : Response<UserResponse> = repository.getUsers()
            users.value = response
        }
    }

    fun getUsersByName(name: String){
        viewModelScope.launch {
            val response: Response<UserResponse> = repository.getUsersByName(name)
            users.value = response
        }
    }

    fun getTopTagID(id: Int){
        viewModelScope.launch {
            val response: Response<TopTagResponse> = repository.getUsersTags(id)
            usersTags.value = response
        }
    }
}