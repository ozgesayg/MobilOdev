package com.example.homeworkozge.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homeworkozge.data.repo.MainRepo
import com.example.homeworkozge.ui.main.vm.MainViewModel

class MainVmProviderFactory (
    val app: Application,
    val mainRepo: MainRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(app, mainRepo) as T
    }
}