package hello.activity.domain

import common.date.LocalDateTimeRange
import hello.activity.domain.Activity
import hello.activity.domain.Running
import hello.activity.domain.attr.CalorieUnit
import org.junit.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

/**
 * Created by nt67 on 7/21/17.
 */
class RunningTest {
    @Test(expected = IllegalArgumentException::class)
    fun tooLargeDistance_throwException() {
        Running.buildInstance(500, Running.Distance(100001), LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0)
        ))
    }

    @Test(expected = IllegalArgumentException::class)
    fun tooSmallDistance_throwException() {
        Running.buildInstance(500, Running.Distance(-1), LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0)
        ))
    }

    @Test
    fun getCallorie_value() {
        val weightKg = 500
        val distanceM = 1000
        val activity: Activity = Running.buildInstance(weightKg, Running.Distance(distanceM), LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0)
        ))

        assertEquals(weightKg * distanceM / 1000, activity.getWasteCalorie().get(CalorieUnit.KCAL))
    }


}