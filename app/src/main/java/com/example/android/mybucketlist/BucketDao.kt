package com.example.android.mybucketlist

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface BucketDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bucket: Bucket)

    @Delete
    suspend fun delete(bucket: Bucket)

    @Query("Select * from BucketList order by id ASC")
    fun getAllgoals() : LiveData<List<Bucket>>

    @Query("DELETE FROM BucketList")
    suspend fun nukeTable()

    @Update
    suspend fun update(bucket: Bucket) : Int
}
