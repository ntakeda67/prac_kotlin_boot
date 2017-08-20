package common.persistence

import common.attr.RecordID

/**
 * 永続化層とのやりとりを担う
 * 具体的には、以下を行う
 *  インスタンスを永続化層へ保存
 *   （永続化層にとって未知の情報については、RecordIDの採番を伴う）
 *  永続化層からインスタンスを構成
  */
interface Persister<T> {
    /**
     *
     */
    fun findByID(id: RecordID): T?
    fun makePersistent(instance:T)
    fun persistAll()
}