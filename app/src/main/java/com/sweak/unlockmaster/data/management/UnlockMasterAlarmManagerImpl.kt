package com.sweak.unlockmaster.data.management

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import com.sweak.unlockmaster.domain.management.UnlockMasterAlarmManager
import com.sweak.unlockmaster.domain.model.DailyWrapUpsNotificationsTime
import com.sweak.unlockmaster.domain.repository.TimeRepository
import com.sweak.unlockmaster.presentation.receivers.DailyWrapUpAlarmReceiver
import javax.inject.Inject

class UnlockMasterAlarmManagerImpl @Inject constructor(
    private val alarmManager: AlarmManager,
    private val timeRepository: TimeRepository,
    private val application: Application
) : UnlockMasterAlarmManager {

    override fun scheduleNewDailyWrapUpsNotifications(
        dailyWrapUpsNotificationsTime: DailyWrapUpsNotificationsTime
    ) {
        val alarmPendingIntent = PendingIntent.getBroadcast(
            application.applicationContext,
            DAILY_WRAP_UP_ALARM_REQUEST_CODE,
            Intent(application.applicationContext, DailyWrapUpAlarmReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        PendingIntent.FLAG_IMMUTABLE
                    else 0
        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            timeRepository.getFutureTimeInMillisOfSpecifiedHourOfDayAndMinute(
                dailyWrapUpsNotificationsTime.hourOfDay,
                dailyWrapUpsNotificationsTime.minute
            ),
            AlarmManager.INTERVAL_DAY,
            alarmPendingIntent
        )
    }
}