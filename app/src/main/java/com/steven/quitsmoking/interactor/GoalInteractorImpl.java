package com.steven.quitsmoking.interactor;

import com.steven.quitsmoking.model.Goal;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class GoalInteractorImpl implements GoalInteractor {

  private Realm realm;

  public GoalInteractorImpl(Realm realm) {
    this.realm = realm;
  }

  @Override
  public List<Goal> getGoals() {
    List<Goal> goals = new ArrayList<>();

    RealmQuery<Goal> query = realm.where(Goal.class);
    RealmResults<Goal> results = query.findAll();

    for (Goal goal : results) {
      goals.add(goal);
    }
    return goals;
  }

  @Override
  public void saveGoal(final Goal goal) {
    realm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        realm.copyToRealm(goal);
      }
    });
  }
}
