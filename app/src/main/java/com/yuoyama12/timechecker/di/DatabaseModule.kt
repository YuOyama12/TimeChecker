package com.yuoyama12.timechecker.di

import android.content.Context
import com.yuoyama12.timechecker.data.CheckResultDao
import com.yuoyama12.timechecker.data.CheckResultDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideWordbookDateBase(
        @ApplicationContext context: Context
    ): CheckResultDatabase {
        return CheckResultDatabase.getInstance(context)
    }

    @Provides
    fun provideCheckResultDao(checkRResultDataBase: CheckResultDatabase): CheckResultDao {
        return checkRResultDataBase.checkResultDao()
    }
}