package com.icarus.androidtest;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class AndroiTest extends Application {

    public static AndroiTest INSTANCE;
    static Context mContext;


    public static AndroiTest getInstance() {
        if (INSTANCE == null)
            INSTANCE = (AndroiTest) mContext;
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

//        RealmConfiguration myConfig = new RealmConfiguration.Builder(this)
//                .name("myrealm.realm").
//                        schemaVersion(2)
//                .setModules(new Login())
//                .build();
//        Realm myRealm = Realm.getInstance(myConfig);
        Realm.setDefaultConfiguration(realmConfiguration);

    }


}
