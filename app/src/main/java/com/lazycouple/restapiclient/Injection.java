package com.lazycouple.restapiclient;

import android.content.Context;
import android.provider.Settings;

import com.lazycouple.restapiclient.data.RestRepository;
import com.lazycouple.restapiclient.data.local.RestLocalDataSource;
import com.lazycouple.restapiclient.util.EncryptionUtils;

import java.nio.ByteBuffer;

import io.realm.RealmConfiguration;


/**
 * Created by amuyu on 2017. 6. 13..
 */

public class Injection {

    public static RestRepository provideRestRepository(Context context) {
        return RestRepository.getInstance(RestLocalDataSource.getInstance(context));
    }

    public static RealmConfiguration provideRealmConfiguration(Context context) {
        String name = "restapi.realm";
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.put(EncryptionUtils.sha256(id.getBytes()));
        buffer.put(EncryptionUtils.sha256(("TCallSyncRealmKey:" + name).getBytes()));
        return new RealmConfiguration.Builder()
//                .encryptionKey(buffer.array())    // 암호화
                .name(name)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();   // migration 하지 않고 그냥 삭제

    }
}
