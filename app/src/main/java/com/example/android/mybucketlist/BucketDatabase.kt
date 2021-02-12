package com.example.android.mybucketlist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Bucket::class), version = 1, exportSchema = false)
public abstract class BucketDatabase : RoomDatabase() {

    abstract fun getBucketDao(): BucketDao

    /*
    Below this abstract value, define a companion object. The companion object allows clients to access the methods for
     creating or getting the database without instantiating the class. Since the only purpose
     of this class is to provide a database, there is no reason to ever instantiate it.
    */
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: BucketDatabase? = null

        fun getBucketDatabase(context: Context): BucketDatabase {
            /*
            if the INSTANCE is not null, then return it,
            if it is, then create the database.
            Multiple threads can potentially ask for a database instance at the same time, resulting in two databases instead of one.
            This problem is not likely to happen in this sample app, but it's possible for a more complex app. Wrapping the code to get the database
            into synchronized means that only one thread of execution at a time can enter this block of code, which makes sure the database only gets
            initialized once.
            */
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BucketDatabase::class.java,
                    "bucket_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}

