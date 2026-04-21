package com.example.kakaoapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CreditUiState(
    val kcbScore: Int = 757,
    val niceScore: Int = 876,
    val kcbHistory: List<CreditHistoryItem> = emptyList(),
    val niceHistory: List<CreditHistoryItem> = emptyList(),
    val isLoadingScores: Boolean = false,
    val isLoadingHistory: Boolean = false,
    val error: String? = null
)

class CreditViewModel : ViewModel() {

    private val api = ApiClient.service
    private val _state = MutableStateFlow(CreditUiState())
    val state: StateFlow<CreditUiState> = _state

    init {
        loadScores()
    }

    fun loadScores() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingScores = true, error = null)
            runCatching { api.getScores() }.onSuccess { res ->
                if (res.isSuccessful) {
                    val scores = res.body() ?: emptyList()
                    _state.value = _state.value.copy(
                        kcbScore = scores.find { it.agency == "KCB" }?.score ?: 757,
                        niceScore = scores.find { it.agency == "NICE" }?.score ?: 876,
                        isLoadingScores = false
                    )
                } else {
                    _state.value = _state.value.copy(isLoadingScores = false, error = "점수 로드 실패")
                }
            }.onFailure {
                _state.value = _state.value.copy(isLoadingScores = false, error = it.message)
            }
        }
    }

    fun updateScore(agency: String, score: Int) {
        viewModelScope.launch {
            runCatching { api.updateScore(UpdateScoreRequest(agency, score)) }.onSuccess { res ->
                if (res.isSuccessful) {
                    if (agency == "KCB") _state.value = _state.value.copy(kcbScore = score)
                    else _state.value = _state.value.copy(niceScore = score)
                }
            }
        }
    }

    fun loadHistory(agency: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingHistory = true, error = null)
            runCatching { api.getHistory(agency) }.onSuccess { res ->
                if (res.isSuccessful) {
                    val items = res.body() ?: emptyList()
                    if (agency == "KCB") _state.value = _state.value.copy(kcbHistory = items, isLoadingHistory = false)
                    else _state.value = _state.value.copy(niceHistory = items, isLoadingHistory = false)
                } else {
                    _state.value = _state.value.copy(isLoadingHistory = false, error = "히스토리 로드 실패")
                }
            }.onFailure {
                _state.value = _state.value.copy(isLoadingHistory = false, error = it.message)
            }
        }
    }

    fun addHistory(item: CreditHistoryItem, onDone: () -> Unit = {}) {
        viewModelScope.launch {
            runCatching { api.addHistory(item) }.onSuccess { res ->
                if (res.isSuccessful) { loadHistory(item.agency); onDone() }
            }
        }
    }

    fun updateHistory(id: Int, item: CreditHistoryItem, onDone: () -> Unit = {}) {
        viewModelScope.launch {
            runCatching { api.updateHistory(id, item) }.onSuccess { res ->
                if (res.isSuccessful) { loadHistory(item.agency); onDone() }
            }
        }
    }

    fun deleteHistory(id: Int, agency: String) {
        viewModelScope.launch {
            runCatching { api.deleteHistory(id) }.onSuccess { res ->
                if (res.isSuccessful) loadHistory(agency)
            }
        }
    }
}
