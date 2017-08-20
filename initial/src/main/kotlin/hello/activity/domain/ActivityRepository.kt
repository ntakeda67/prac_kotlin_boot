package hello.activity.domain

import common.attr.RecordID
import common.domain.Repository
import common.persistence.Persister
import hello.user.domain.User

/**
 * Created by nt67 on 8/9/17.
 */
class ActivityRepository(private val persister: Persister<Activity>) : Repository<Activity> {

    override fun findByID(id: RecordID): Activity? {
        return persister.findByID(id)
    }

    override fun makePersist(act: Activity) {
        this.persister.makePersistent(act)
    }

    override fun persistAll() {
        this.persister.persistAll()
    }

    /* Query Objectを考えるまで棚上げ
    fun findByUser(u: User): MutableList<Activity> {
    }
    */
}