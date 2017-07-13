package com.lazycouple.restapiclient.ui.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
class User : Parcelable {
    var login: String? = null
    var id: Long = 0
    var url: String? = null
    var email: String? = null

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.login = `in`.readString()
        this.id = `in`.readLong()
        this.url = `in`.readString()
        this.email = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.login)
        dest.writeLong(this.id)
        dest.writeString(this.url)
        dest.writeString(this.email)
    }

    override fun toString(): String {
        return "User{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", url='" + url + '\'' +
                ", email='" + email + '\'' +
                '}'
    }

    companion object {
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User {
                return User(source)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }
}
