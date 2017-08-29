package hello.activity.persistence

import common.attr.PersistedRecordID
import common.date.LocalDateTimeRange
import hello.activity.domain.Activity
import hello.activity.domain.Running
import org.junit.Test
import org.junit.Before
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.fail

/**
 * Created by nt67 on 8/20/17.
 * TODO: printlnは Logging が決まったらそれに置き換える
 */
class FakeActivityPersisterTest {

    @Before
    fun initalize() {
        FakeActivityPersister.clear()
    }

    @Test
    fun not_found_record_return_null(){
        assertNull(FakeActivityPersister.findByID(PersistedRecordID(1L)))
    }

    @Test
    fun found_persistedInstance(){
        val a: Activity = Running.buildInstance(
                70
                , Running.Distance(1000)
                , LocalDateTimeRange(
                LocalDateTime.of(2017, 1, 1, 12, 0, 0)
                ,  LocalDateTime.of(2017, 1, 1, 13, 0, 0))
        )

        FakeActivityPersister.makePersistent(a)
        FakeActivityPersister.persistAll()
        val foundA = FakeActivityPersister.findByID(a.getActivityID())
        assertNotNull(foundA)
        assertEquals(a, foundA)
    }

    /**
     * 同じRecordIDを持つインスタンスを、永続管理することは許可しない
     */
    @Test(expected = IllegalStateException::class)
    fun same_record_cannot_be_added(){
        val a1: Activity = Running.buildInstance(
                PersistedRecordID(1L)
                , 70
                , Running.Distance(1000)
                , LocalDateTimeRange(
                LocalDateTime.of(2017, 1, 1, 12, 0, 0)
                ,  LocalDateTime.of(2017, 1, 1, 13, 0, 0))
        )
        FakeActivityPersister.makePersistent(a1)

        val a2: Activity = Running.buildInstance(
                PersistedRecordID(1L)
                , 70
                , Running.Distance(1000)
                , LocalDateTimeRange(
                LocalDateTime.of(2017, 1, 1, 12, 0, 0)
                ,  LocalDateTime.of(2017, 1, 1, 13, 0, 0))
        )
        FakeActivityPersister.makePersistent(a2)
    }

    @Test
    fun found_record_corresponding_to_recordID(){
        val a: Activity = Running.buildInstance(
                PersistedRecordID(1L)
                , 70
                , Running.Distance(1000)
                , LocalDateTimeRange(
                LocalDateTime.of(2017, 1, 1, 12, 0, 0)
                ,  LocalDateTime.of(2017, 1, 1, 13, 0, 0))
        )
        FakeActivityPersister.makePersistent(a)

        val b: Activity = Running.buildInstance(
                PersistedRecordID(2L)
                , 70
                , Running.Distance(1000)
                , LocalDateTimeRange(
                LocalDateTime.of(2017, 1, 1, 12, 0, 0)
                ,  LocalDateTime.of(2017, 1, 1, 13, 0, 0))
        )
        FakeActivityPersister.makePersistent(b)

        val actual1 = FakeActivityPersister.findByID(PersistedRecordID(1L))
        val actual2 = FakeActivityPersister.findByID(PersistedRecordID(2L))

        assertEquals(actual1, a)
        assertEquals(actual2, b)
    }

    @Test
    fun destroyed_not_persisted(){
        val a: Activity = Running.buildInstance(
                  70
                , Running.Distance(1000)
                , LocalDateTimeRange(
                LocalDateTime.of(2017, 1, 1, 12, 0, 0)
                ,  LocalDateTime.of(2017, 1, 1, 13, 0, 0))
        )
        FakeActivityPersister.makePersistent(a)
        FakeActivityPersister.destroyPersistanceMap()

        val b: Activity = Running.buildInstance(
                  70
                , Running.Distance(1000)
                , LocalDateTimeRange(
                LocalDateTime.of(2017, 1, 1, 12, 0, 0)
                ,  LocalDateTime.of(2017, 1, 1, 13, 0, 0))
        )

        FakeActivityPersister.makePersistent(b)
        FakeActivityPersister.persistAll()
        FakeActivityPersister.destroyPersistanceMap()

        val actual1 = FakeActivityPersister.findByID(PersistedRecordID(2L))
        val actual2 = FakeActivityPersister.findByID(PersistedRecordID(1L))

        assertNull(actual1)
        assertEquals(actual2, b)
    }

    @Test
    fun update_persisted_record() {
        val a: Activity = Running.buildInstance(
                70
                , Running.Distance(1000)
                , LocalDateTimeRange(
                LocalDateTime.of(2017, 1, 1, 12, 0, 0)
                ,  LocalDateTime.of(2017, 1, 1, 13, 0, 0))
        )
        FakeActivityPersister.makePersistent(a)
        FakeActivityPersister.persistAll()

        FakeActivityPersister.destroyPersistanceMap()

        val persisted = FakeActivityPersister.findByID(PersistedRecordID(1L))
        // TODO 早期 fail して Running として扱い ネスト減らしたい
        when (persisted) {
            is Running -> {
                assertEquals(Running.Distance(1000), persisted.getDistance())
                persisted.setDistance(Running.Distance(2000))
                FakeActivityPersister.persistAll()
            }
            else -> fail("Saved Running instance colud not treat as Running")
        }

        FakeActivityPersister.destroyPersistanceMap();
        val updated = FakeActivityPersister.findByID(PersistedRecordID(1L))
        when (updated) {
            is Running -> {
                assertEquals(Running.Distance(2000), persisted.getDistance())
            }
            else -> fail("Saved Running instance colud not treat as Running")
        }



    }
}