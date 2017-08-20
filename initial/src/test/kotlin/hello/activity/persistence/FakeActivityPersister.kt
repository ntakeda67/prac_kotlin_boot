package hello.activity.persistence

import common.attr.InstanceID
import common.attr.NotYetPersistedID
import common.attr.PersistedRecordID
import common.attr.RecordID
import common.persistence.Persister
import hello.activity.domain.Activity
import java.util.*
import kotlin.collections.HashSet

/**
 * アプリケーションの実行期間の間だけ、インメモリ保存するテスト用永続化層
 * 以下の要素は考慮しない
 *  - 実行時パフォーマンス（計算コストも記憶域サイズも、テストで問題になるまで棚上げ）
 *    大量データ実行は、本番ランタイムと同等の永続化構成で行うべきもの。Fakeはこの状況を考慮しない
 */
object FakeActivityPersister : Persister<Activity> {
    /** TODO Map関連は可能であれば基底クラスへ引き上げ */

    /**
     * ビジネスロジックのワントランザクション中でドメイン内に構成したモデルのインスタンス
     * 以下のものが存在しうる
     *  - 永続化層に未反映、永続化なし: ビジネスロジックで新たに組み上げたが、まだ永続化層に保存していないもの
     *  - 永続化層に未反映、永続化あり: 永続化層から読み出したが、ビジネスロジックで加えた変更が反映されていないもの
     *  - 永続化層に反映、永続化あり: 永続化層から読み出したまま、変更を加えていないもの
     */
    private val instanceMap: MutableMap<InstanceID, Activity> = HashMap()

    /**
     * 永続化層のキャッシュとして用いる、Identity Map
     */
    private val persistedMap: MutableMap<RecordID, Activity> = HashMap()

    private var currentRecordIDSequence = 0L
    private fun generateRecordID(): RecordID {
        return PersistedRecordID(++currentRecordIDSequence)
    }

    private fun getInstanceByRecordID(id:RecordID): Activity? {
        return instanceMap.map {
            // instanceMap中 での RecordIDの一意性はMap操作時に保証することを前提とする
            if( it.value.getActivityID() == id ){
                return it.value
            } else { null }
        }.firstOrNull()
    }


    private fun addToInstanceMap(a: Activity) {
        instanceMap.putIfAbsent(a.instanceID, a)
    }

    override fun findByID(id: RecordID): Activity? {
        val instanced = getInstanceByRecordID(id)
        return when (instanced) {
            null -> {
                val p = persistedMap[id]
                if (p != null) { addToInstanceMap(p) }
                return p
            }
            else -> instanced
        }
    }

    override fun makePersistent(instance: Activity) {
        addToInstanceMap(instance)
    }

    override fun persistAll() {
        var inserting: MutableSet<Activity> = HashSet()
        var updating : MutableSet<Activity> = HashSet()
        // TODO: partitionが使えそうな気配がする
        instanceMap.map { Pair(it.value.getActivityID(), it.value) }
                .forEach {
                    when(it.first) {
                        NotYetPersistedID -> it.second.setActivityID(generateRecordID())
                    }
                    persistedMap.put(it.second.getActivityID(), it.second)
                }
    }
}