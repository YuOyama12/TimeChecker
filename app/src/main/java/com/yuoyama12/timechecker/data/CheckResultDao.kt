package com.yuoyama12.timechecker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckResultDao {
    @Query("SELECT * FROM checkResult")
    fun getAll(): Flow<List<CheckResult>>

    @Query("DELETE FROM checkResult")
    fun deleteAll()

    @Insert
    fun insert(checkResult: CheckResult)

    @Delete
    fun delete(checkResult: CheckResult)
}