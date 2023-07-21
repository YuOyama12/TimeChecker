package com.yuoyama12.timechecker.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckResultRepository @Inject constructor(
    private val checkResultDao: CheckResultDao
) {
    fun getAll(): Flow<List<CheckResult>> {
        return checkResultDao.getAll()
    }

    fun insert(checkResult: CheckResult){
        checkResultDao.insert(checkResult)
    }
}