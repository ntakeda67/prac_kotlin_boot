package common.attr

/**
 * Primary Key of a Record on Persistence Layer
 */
interface RecordID
class PersistedRecordID(val id: Long) : RecordID
object NotYetPersistedID : RecordID