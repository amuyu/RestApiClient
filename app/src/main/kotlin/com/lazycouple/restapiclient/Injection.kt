package com.lazycouple.restapiclient

import android.content.Context
import android.provider.Settings
import com.lazycouple.restapiclient.repository.RestRepository
import com.lazycouple.restapiclient.repository.local.RestLocalDataSource
import com.lazycouple.restapiclient.util.EncryptionUtils
import io.realm.RealmConfiguration
import java.nio.ByteBuffer


object Injection {

    fun provideRestRepository(context: Context): RestRepository {
        return RestRepository.getInstance(RestLocalDataSource.getInstance(context))
    }

    fun provideRealmConfiguration(context: Context): RealmConfiguration {
        val name = "restapi.realm"
        val id = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val encryptionBuffer = ByteBuffer.allocate(64).apply {
            put(EncryptionUtils.sha256(id.toByteArray()))
            put(EncryptionUtils.sha256(("TCallSyncRealmKey:" + name).toByteArray()))
        }

        return RealmConfiguration.Builder()
                //                .encryptionKey(encryptionBuffer.array())    // 암호화
                .name(name)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()   // migration 하지 않고 그냥 삭제

    }
}
