package common.date

import java.time.LocalDateTime

/**
 * Local Date per minute Iterator
 */
open class LocalDateTimeRange(
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

object NullLocalDateTimeRange : LocalDateTimeRange(LocalDateTime.of(1900, 1, 1, 0, 0, 0), LocalDateTime.of(1900, 1, 1, 0, 0, 0))