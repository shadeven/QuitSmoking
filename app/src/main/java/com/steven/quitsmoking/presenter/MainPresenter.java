package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Observable;

public class MainPresenter {

  private MainActivity activity;
  private final Realm realm;
  private List<String> goals = new ArrayList<>();

  public MainPresenter(Realm realm) {
    this.realm = realm;
    loadAllGoals();
  }

  public void onTakeView(MainActivity activity) {
    this.activity = activity;
    publish();
  }

  private void publish() {
    if (activity != null) {
      if (goals != null) {
        activity.onItemNext(goals);
      }
    }
  }

  public void loadAllGoals() {
    RealmQuery<Goal> query = realm.where(Goal.class);
    RealmResults<Goal> results = query.findAll();
    Observable<RealmResults<Goal>> resultsObservable = results.asObservable();
    
    if (results.isEmpty()) {
      goals.add("No goal created yet.");
    } else {
      for (Goal goal : results) {
        goals.add(goal.getName());
      }
    }
  }
}
