package com.popspot.app.presentation.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.popspot.app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val user: FirebaseUser? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val userTags: List<String> = emptyList() // 선택한 관심사 태그 리스트
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext context: Context
) : ViewModel() {

    // 휴대폰 내부에 데이터를  SharedPreferences
    private val prefs = context.getSharedPreferences("popspot_prefs", Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(
        AuthUiState(
            user = authRepository.getCurrentUser(),
            userTags = loadTags()
        )
    )
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun handleGoogleSignIn(idToken: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            authRepository.signInWithGoogle(idToken)
                .onSuccess { user ->
                    _uiState.value = _uiState.value.copy(user = user, isLoading = false)
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(error = e.message ?: "로그인에 실패했어요", isLoading = false)
                }
        }
    }

    //  태그 저장 함수
    fun saveTags(tags: List<String>) {
        prefs.edit().putStringSet("user_tags", tags.toSet()).apply()
        _uiState.value = _uiState.value.copy(userTags = tags)
    }

    // 태그 불러오기 함수
    private fun loadTags(): List<String> {
        return prefs.getStringSet("user_tags", emptySet())?.toList() ?: emptyList()
    }

    fun logout() {
        authRepository.signOut()
        _uiState.value = AuthUiState(user = null, userTags = loadTags())
    }
}