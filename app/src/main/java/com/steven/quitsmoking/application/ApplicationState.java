package com.steven.quitsmoking.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class ApplicationState extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Realm database
    RealmConfiguration configuration = new RealmConfiguration.Builder(this)
            .name("quitsmoking.realm")
            .build();
    Realm.setDefaultConfiguration(configuration);

    // Timber logging
    Timber.plant(new Timber.DebugTree());
  }
}
