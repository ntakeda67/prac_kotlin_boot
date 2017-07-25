package hello.activity.domain

import common.date.LocalDateRange
import common.date.LocalDateTimeRange
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.assertEquals

/**
 * Created by nt67 on 7/21/17.
 */
class ActivitiesTest {
    @Test
    fun totalCalorie_valid() {
        val a = Activities()

        val r1 = Running(100
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0))
        )
        val r2 = Running(200
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0))
        )

        a.add(r1)
        assertEquals(a.totalCalorie(), 10000)

        a.add(r2)
        assertEquals(a.totalCalorie(), 30000)

        a.remove(r1)
        assertEquals(a.totalCalorie(), 20000)
    }

    @Test
    fun totalCalorie_specified_date_valid() {
        val a = Activities()

        val targetDate:LocalDate = LocalDate.of(2015, 1, 1)

        val targetDate1 = Running(100
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0))
        )
        val anotherDate1 = Running(200
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 2, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 2, 1, 0, 0))
        )

        val targetDate2 = Running(300
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 2, 1, 0, 0))
        )
        a.add(targetDate1)
        assertEquals(a.totalCalorie(targetDate), 10000)

        a.add(anotherDate1)
        assertEquals(a.totalCalorie(targetDate), 10000)

        a.add(targetDate2)
        assertEquals(a.totalCalorie(targetDate), 40000)
    }

    @Test
    fun getActivities_specified_daterange_valid() {

        val targetDateRange: LocalDateRange = LocalDateRange(
                 LocalDate.of(2015, 1, 1)
                ,LocalDate.of(2015, 12, 31)
        )

        val inRange1 = Running(100
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0))
        )
        val inRange2 = Running(100
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2015, 12, 31, 23, 59, 59)
                , endInclusive = LocalDateTime.of(2016, 1, 1, 1, 0, 0))
        )
        val outRange1 = Running(100
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2014, 12, 31, 23, 59, 59)
                , endInclusive = LocalDateTime.of(2015, 1, 1, 1, 0, 0))
        )
        val outRange2 = Running(100
                , Running.Distance(100)
                , LocalDateTimeRange(
                start = LocalDateTime.of(2016, 1, 1, 0, 0, 0)
                , endInclusive = LocalDateTime.of(2016, 1, 1, 1, 0, 1))
        )
        val a = Activities(listOf(inRange1, inRange2, outRange1, outRange2))
        val actual = a.getActivityList(targetDateRange)
        assert(actual.contains(inRange1))
        assert(actual.contains(inRange2))
        assert(!actual.contains(outRange1))
        assert(!actual.contains(outRange2))
    }
}