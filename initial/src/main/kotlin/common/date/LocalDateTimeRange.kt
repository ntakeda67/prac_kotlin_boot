package common.date

import java.time.LocalDateTime

/**
 * Local Date per minute Iterator
 */
data class LocalDateTimeRange(
        override val start: LocalDateTime
        , override val endInclusive:  LocalDateTime) :ClosedRange<LocalDateTime>, Iterable<LocalDateTime> {
    override fun iterator(): Iterator<LocalDateTime> = LocalDateTimeIterator(this)
}

class LocalDateTimeIterator(val range :LocalDateTimeRange): Iterator<LocalDateTime> {
    var current : LocalDateTime = range.start

    override fun hasNext(): Boolean {
        return current <= range.endInclusive
    }

    override fun next(): LocalDateTime {
        val next = current
        current.plusMinutes(1L)
        return next
    }

}