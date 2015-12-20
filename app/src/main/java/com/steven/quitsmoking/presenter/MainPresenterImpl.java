package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.MainView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainPresenterImpl implements MainPresenter {

  private MainView mainView;
  private Realm realm;

  public MainPresenterImpl(Realm realm, MainView mainView) {
    this.realm = realm;
    this.mainView = mainView;
  }

  @Override
  public void loadGoals() {
    RealmQuery<Goal> query = realm.where(Goal.class);
    RealmResults<Goal> results = query.findAll();
    List<Goal> goals = new ArrayList<>();

    for (Goal goal : results) {
      goals.add(goal);
    }

    mainView.onGetData(goals);
  }
}
