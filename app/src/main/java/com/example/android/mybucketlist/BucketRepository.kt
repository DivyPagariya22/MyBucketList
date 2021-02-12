package com.example.android.mybucketlist

import android.text.BoringLayout
import androidx.lifecycle.LiveData

class BucketRepository(private val bucketDao: BucketDao) {
    val allGoals : LiveData<List<Bucket>> = bucketDao.getAllgoals()

    suspend fun insert(bucket: Bucket){
        bucketDao.insert(bucket)
    }
    suspend fun delete(bucket: Bucket){
        bucketDao.delete(bucket)
    }

    suspend fun deleteAll(){
        bucketDao.nukeTable()
    }
    suspend fun update(bucket: Bucket){
        bucketDao.update(bucket)
    }
}

