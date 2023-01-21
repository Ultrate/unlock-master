package com.sweak.unlockmaster.presentation.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweak.unlockmaster.domain.use_case.unlock_events.GetTodayUnlockEventsCountUseCase
import com.sweak.unlockmaster.domain.use_case.unlock_limits.GetUnlockLimitAmountForTodayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodayUnlockEventsCountUseCase: GetTodayUnlockEventsCountUseCase,
    private val getUnlockLimitAmountForTodayUseCase: GetUnlockLimitAmountForTodayUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())

    fun refresh() = viewModelScope.launch {
        // This one-second delay should give the UnlockMasterService enough time to update all
        // values (in case of e.g. unlocking the screen and immediately landing on the HomeScreen)
        // before we post them to the UI:
        delay(1000)

        state = state.copy(
            isInitializing = false,
            unlockCount = getTodayUnlockEventsCountUseCase(),
            unlockLimit = getUnlockLimitAmountForTodayUseCase()
        )
    }
}