package com.oztrna.newsapi.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oztrna.newsapi.model.Source

@Dao
interface SourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllSource(quote: List<Source>)

    @Query("SELECT * FROM Source")
    fun getSource(): LiveData<List<Source>>

}