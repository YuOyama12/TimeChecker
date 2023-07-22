package com.yuoyama12.timechecker.ui.resultlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuoyama12.timechecker.data.CheckResult
import com.yuoyama12.timechecker.data.CheckResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultListViewModel @Inject constructor(
    private val repo: CheckResultRepository
) : ViewModel() {
    val resultList: Flow<List<CheckResult>> = repo.getAll()

    fun deleteItem(checkResult: CheckResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(checkResult)
        }
    }
    fun clearList() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAll()
        }
    }
}