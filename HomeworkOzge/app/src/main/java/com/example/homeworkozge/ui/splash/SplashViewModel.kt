package com.example.homeworkozge.ui.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homeworkozge.ui.main.vm.BaseVM
import com.example.homeworkozge.util.Resource
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : BaseVM(application)  {

    val intConnectionResponse: MutableLiveData<Resource<Boolean>> = MutableLiveData()


    fun checkInternet() = viewModelScope.launch {
        val internetConn = hasInternetConnection()
        intConnectionResponse.postValue(Resource.Success(internetConn))
    }

}

