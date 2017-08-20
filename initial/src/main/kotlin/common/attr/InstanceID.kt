package common.attr

import java.util.*

/**
 * Primary Key on Domain Repository
 */
open class InstanceID {
    private var id: UUID = UUID.randomUUID()
    fun getUUID(): UUID = id
}
class PersistedInstanceID : InstanceID()
class NotPersistedInstanceID : InstanceID()
