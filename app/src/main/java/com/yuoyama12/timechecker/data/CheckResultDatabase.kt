package com.yuoyama12.timechecker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yuoyama12.timechecker.data.converter.TimeConverter

@Database(entities = [CheckResult::class], version = 1, exportSchema = false)
@TypeConverters(TimeConverter::class)
abstract class CheckResultDatabase : RoomDatabase() {
    abstract fun checkResultDao(): CheckResultDao

    companion object {
        @Volatile
        private var instance: CheckResultDatabase? = null

        fun getInstance(context: Context): CheckResultDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CheckResultDatabase {
            return Room.databaseBuilder(
                context,
                CheckResultDatabase::class.java,
                "checkResult.db"
            ).build()
        }
    }
}