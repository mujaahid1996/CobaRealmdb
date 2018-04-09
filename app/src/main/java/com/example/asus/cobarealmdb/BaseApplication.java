package com.example.asus.cobarealmdb;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by asus on 29/12/17.
 * cek AndroidManifest, tambahkan attribut android:name
 * <application
    android:name=".BaseApplication"
    ...
 */

public class BaseApplication extends Application {

    // override here
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * The default Realm file is "default.realm"
         * https://realm.io/docs/java/latest/#realms
         */


        Realm.init(this); //inisialisasi Realm
        // konfigurasi minimal
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }
}
