package com.example.happydb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//android view model can access by many activity,viewmodel only can access one
class FeelingViewModel(application: Application):AndroidViewModel(application)
{
    private val repository: FeelingRepository
    val allFeelings:LiveData<List<Feeling>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val feelingDao = FeelingDatabase.getDatabase(application).feelingDao()
        repository = FeelingRepository(feelingDao)
        allFeelings = repository.allFeelings
    }

    //A coroutine function ,'globalscope run on coroutine'
    fun insertFeeling(feeling: Feeling) = viewModelScope.launch {
        repository.insertFeeling(feeling)
    }
}