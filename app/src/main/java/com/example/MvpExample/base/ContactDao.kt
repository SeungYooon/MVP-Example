package com.example.MvpExample.base

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.MvpExample.model.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    // 중복된 데이터 경우 대체
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}