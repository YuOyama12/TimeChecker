package com.yuoyama12.timechecker.ui.resultlist

import androidx.lifecycle.ViewModel
import com.yuoyama12.timechecker.data.CheckResult
import com.yuoyama12.timechecker.data.CheckResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ResultListViewModel @Inject constructor(
    repo: CheckResultRepository
) : ViewModel() {
    val resultList: Flow<List<CheckResult>> = repo.getAll()
}