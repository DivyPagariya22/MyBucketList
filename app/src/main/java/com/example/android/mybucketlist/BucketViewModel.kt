package com.example.android.mybucketlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Now that you have a database and a UI, you need to collect data, add the data to the database, and display the data.
All this work is done in the view model. Your sleep-tracker view model will handle button clicks, interact with the database via the DAO,
and provide data to the UI via LiveData. All database operations will have to be run away from the main UI thread,
and you'll do that using coroutines.

In Kotlin, coroutines are the way to handle long-running tasks elegantly and efficiently instead of callbacks.
*/
class BucketViewModel(application: Application) : AndroidViewModel(application) {
    val allGoals : LiveData<List<Bucket>>
    val repository : BucketRepository

    init {
        val dao = BucketDatabase.getBucketDatabase(application).getBucketDao()
        repository = BucketRepository(dao)
        allGoals = repository.allGoals
    }

    fun deleteGoal(goal : Bucket ) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(goal)
    }

    fun insertGoal(goal : Bucket) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(goal)
    }

    fun deleteAll()= viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }

    fun update(goal: Bucket) = viewModelScope.launch(Dispatchers.IO){
        repository.update(goal)
    }
}

