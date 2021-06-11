package com.example.homeworkozge.ui.main.vm

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homeworkozge.R
import com.example.homeworkozge.data.model.User
import com.example.homeworkozge.data.repo.MainRepo
import com.example.homeworkozge.util.Resource
import kotlinx.coroutines.launch

class MainViewModel (
    application: Application,
    val mainRepo: MainRepo
): BaseVM(application) {

    val userListResponse: MutableLiveData<Resource<MutableList<User>>> = MutableLiveData()

    fun getUserList() = viewModelScope.launch {
        userListCall()
    }

    private suspend fun userListCall() {
        userListResponse.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = mainRepo.getUsers()
                if (response.isSuccessful) {
                    response.body()?.let { users ->
                        userListResponse.postValue(Resource.Success(users))
                    }
                } else{
                    userListResponse.postValue(Resource.Error(Resources.getSystem().getString(R.string.error_msg_unknown,response.code().toString())))
                }

            } else {
                userListResponse.postValue(Resource.Error(Resources.getSystem().getString(R.string.error_msg_internet_connection)))
            }
        } catch (e: Exception) {
            userListResponse.postValue(Resource.Error(e.message.toString()))
        }
    }

}