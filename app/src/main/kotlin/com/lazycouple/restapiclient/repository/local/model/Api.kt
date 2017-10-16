package com.lazycouple.restapiclient.repository.local.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by amuyu on 2017. 6. 16..
 */

open class Api : RealmObject() {
    @PrimaryKey
    var id: String = ""
    var url: String? = null
    var parameters: RealmList<Parameter> = RealmList()
    var date: Date? = null
    var method: Int = 0


    fun setParameters(parameters: List<Parameter>) {
        this.parameters.clear()
        for (param in parameters) {
            param.id = this.id
            this.parameters.add(param)
        }
    }

    override fun toString(): String {
        return "Api{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", parameters=" + parameters +
                ", date=" + date +
                '}'
    }
}
