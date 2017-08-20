package common.domain

import common.attr.InstanceID

/**
 * Created by nt67 on 8/15/17.
 */
abstract class PersistableModel {
    val instanceID = InstanceID()
    get(): InstanceID {
        return field
    }
}