package hello.activity.domain

import common.date.LocalDateTimeRange
import hello.activity.domain.attr.Calorie
import java.time.LocalDate

interface Activity {
    fun getWasteCalorie(): Calorie
    fun getDuration(): LocalDateTimeRange
    fun getActivityDate(): LocalDate = getDuration().start.toLocalDate()
}