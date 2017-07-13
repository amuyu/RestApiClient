package com.lazycouple.restapiclient.db.model

import io.realm.RealmObject

/**
 * Created by noco on 2016-10-13.
 */
open class Parameter : RealmObject {
    var id: String? = null
    var key: String? = null
    var value: String? = null

    constructor() {}

    constructor(key: String, value: String) {
        this.key = key
        this.value = value
    }

    override fun toString(): String {
        return "Parameter{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}'
    }

    class Builder {
        private var key: String? = null
        private var value: String? = null


        fun key(key: String): Builder {
            this.key = key
            return this
        }

        fun value(value: String): Builder {
            this.value = value
            return this
        }

        fun build(): Parameter {
            val parameter = Parameter()
            parameter.key = key
            parameter.value = value
            return parameter
        }
    }
}
