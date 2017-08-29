package common.domain

import common.attr.PersistedRecordID

/**
 * Created by nt67 on 8/9/17.
 */
interface Repository<T> {
    // null object? domainとは別のActiveRecordを用意?
    // null objectであることを表すinterfaceが必要になりそうで、かえってごちゃる？
    fun findByID(id: PersistedRecordID): T?
    fun makePersist(act: T)
    fun persistAll()
}