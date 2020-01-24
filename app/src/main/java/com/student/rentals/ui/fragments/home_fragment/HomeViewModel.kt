package com.student.rentals.ui.fragments.home_fragment

import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.*
import com.student.Api.ApiInterface
import kotlinx.coroutines.launch

class HomeViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    val TAG:String = "HomeViewModel"
    private val _uiState = MutableLiveData<UserData>()
    val uiState: LiveData<UserData> =_uiState
    init {
        retrieveMovies()
    }


    fun retrieveMovies(){
        viewModelScope.launch {
            kotlin.runCatching {
                apiInterface.getUser()

            }.onSuccess {
                Log.d(TAG,"Hurressssssssssssssssss")
            }.onFailure {
                Log.d(TAG, "Faileediiiiiiiiii")
            }
        }
    }
}
