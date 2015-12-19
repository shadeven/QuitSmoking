package com.steven.quitsmoking.Application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class GlobalApplicationState extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    RealmConfiguration configuration = new RealmConfiguration.Builder(this)
            .name("quitsmoking.realm")
            .build();
    Realm.setDefaultConfiguration(configuration);
  }
}
