package com.example.android.mybucketlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Base class
@Entity(tableName = "BucketList")
class Bucket(
    @PrimaryKey(autoGenerate = true)var id:Int,
    @ColumnInfo(name = "goal") var goal : String,
    @ColumnInfo(name = "done") var done : Int) {
}


