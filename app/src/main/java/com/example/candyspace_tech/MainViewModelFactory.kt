package com.example.candyspace_tech

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.candyspace_tech.Repository.Repository

//This is where the data class can be supplied to identify what object the Api should return
class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T{
        return MainViewModel(repository) as T
    }
}