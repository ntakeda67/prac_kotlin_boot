package common.date

import java.time.LocalDate

/**
 * Local Date per day
 */
data class LocalDateRange(
        override val start: LocalDate
        , override val endInclusive:  LocalDate) :ClosedRange<LocalDate>, Iterable<LocalDate> {
    override fun iterator(): Iterator<LocalDate> = LocalDateIterator(this)
}

class LocalDateIterator(val range :LocalDateRange): Iterator<LocalDate> {
    var current : LocalDate = range.start

    override fun hasNext(): Boolean {
        return current <= range.endInclusive
    }

    override fun next(): LocalDate {
        val next = current
        current.plusDays(1L)
        return next
    }

}