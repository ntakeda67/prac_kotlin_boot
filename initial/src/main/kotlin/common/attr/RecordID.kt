package common.attr

/**
 * Primary Key of a Record on Persistence Layer
 */
interface RecordID {
    override operator fun equals(other: Any?): Boolean
}
class PersistedRecordID(val id: Long) : RecordID {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is PersistedRecordID -> other.id.equals(this.id)
            else -> false
        }
    }

    /**
     * id値が一致するものは同じものとして扱う
     */
    override fun hashCode(): Int {
        return id.hashCode()
    }
}

object NotYetPersistedID : RecordID {
    override fun equals(other: Any?) = false
}