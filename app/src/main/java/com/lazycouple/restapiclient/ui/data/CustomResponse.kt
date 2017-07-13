package com.lazycouple.restapiclient.ui.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by noco on 2016-10-26.
 */
class CustomResponse : Parcelable {
    var code: Int = 0
    var body: String? = null
    var errorbody: String? = null

    constructor() {}

    protected constructor(`in`: Parcel) {
        code = `in`.readInt()
        body = `in`.readString()
        errorbody = `in`.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(code)
        dest.writeString(body)
        dest.writeString(errorbody)
    }

    override fun describeContents(): Int {
        return 0
    }


    class CustomResponseBuilder {
        private var errorbody: String? = null
        private var code: Int = 0
        private var body: String? = null

        fun errorbody(errorbody: String): CustomResponseBuilder {
            this.errorbody = errorbody
            return this
        }

        fun code(code: Int): CustomResponseBuilder {
            this.code = code
            return this
        }

        fun body(body: String): CustomResponseBuilder {
            this.body = body
            return this
        }

        fun build(): CustomResponse {
            val customResponse = CustomResponse()
            customResponse.body = this.body
            customResponse.code = this.code
            customResponse.errorbody = this.errorbody
            return customResponse
        }

        companion object {

            fun aCustomResponse(): CustomResponseBuilder {
                return CustomResponseBuilder()
            }
        }
    }

    companion object {

        val CREATOR: Parcelable.Creator<CustomResponse> = object : Parcelable.Creator<CustomResponse> {
            override fun createFromParcel(`in`: Parcel): CustomResponse {
                return CustomResponse(`in`)
            }

            override fun newArray(size: Int): Array<CustomResponse?> {
                return arrayOfNulls<CustomResponse>(size)
            }
        }
    }
}
