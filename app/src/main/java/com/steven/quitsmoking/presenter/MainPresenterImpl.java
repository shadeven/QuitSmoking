package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.MainActivityView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainPresenterImpl implements MainPresenter {

  private MainActivityView view;
  private Realm realm;

  public MainPresenterImpl(Realm realm, MainActivityView view) {
    this.realm = realm;
    this.view = view;
  }

  public MainPresenterImpl(MainActivityView view) {
    this.view = view;
  }

  @Override
  public void loadGoals() {
    List<Goal> goals = new ArrayList<>();

    if (realm != null) {
      RealmQuery<Goal> query = realm.where(Goal.class);
      RealmResults<Goal> results = query.findAll();

      for (Goal goal : results) {
        goals.add(goal);
      }
    }
    view.onGetData(goals);
  }
}
