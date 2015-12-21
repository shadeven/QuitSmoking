package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.AddGoalActivityView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AddGoalPresenterImpl implements AddGoalPrsenter {

  private Realm realm;
  private AddGoalActivityView view;

  public AddGoalPresenterImpl(Realm realm, AddGoalActivityView view) {
    this.realm = realm;
    this.view = view;
  }

  @Override
  public void saveAndLoadData() {
    realm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        Goal goal = realm.createObject(Goal.class);
        goal.setId(1);
        goal.setName("Quit smoking in 1 week");
        goal.setDueDate(new Date());
      }
    });

    RealmQuery<Goal> query = realm.where(Goal.class);
    RealmResults<Goal> results = query.findAll();

    List<Goal> goals = new ArrayList<>();
    for (Goal goal : results) {
      goals.add(goal);
    }
    view.onSaveData(goals);
  }
}
