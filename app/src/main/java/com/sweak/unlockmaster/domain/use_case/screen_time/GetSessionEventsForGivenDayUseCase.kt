package com.sweak.unlockmaster.domain.use_case.screen_time

import com.sweak.unlockmaster.domain.model.SessionEvent
import com.sweak.unlockmaster.domain.repository.CounterPausedEventsRepository
import com.sweak.unlockmaster.domain.repository.CounterUnpausedEventsRepository
import com.sweak.unlockmaster.domain.repository.LockEventsRepository
import com.sweak.unlockmaster.domain.repository.TimeRepository
import com.sweak.unlockmaster.domain.repository.UnlockEventsRepository
import com.sweak.unlockmaster.domain.repository.UserSessionRepository
import javax.inject.Inject

class GetSessionEventsForGivenDayUseCase @Inject constructor(
    private val unlockEventsRepository: UnlockEventsRepository,
    private val lockEventsRepository: LockEventsRepository,
    private val counterPausedEventsRepository: CounterPausedEventsRepository,
    private val counterUnpausedEventsRepository: CounterUnpausedEventsRepository,
    private val timeRepository: TimeRepository,
    private val userSessionRepository: UserSessionRepository
) {
    suspend operator fun invoke(
        dayTimeInMillis: Long = timeRepository.getCurrentTimeInMillis()
    ): List<SessionEvent> {
        return emptyList()
    }
}