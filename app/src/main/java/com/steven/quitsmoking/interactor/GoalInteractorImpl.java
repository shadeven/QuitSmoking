package com.steven.quitsmoking.interactor;

import com.steven.quitsmoking.model.Goal;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;
import timber.log.Timber;

public class GoalInteractorImpl implements GoalInteractor {

  private Realm realm;

  public GoalInteractorImpl(Realm realm) {
    this.realm = realm;
  }

  @Override
  public Observable<List<Goal>> getGoalsAsObservable() {
    final List<Goal> goals = new ArrayList<>();
    RealmQuery<Goal> query = realm.where(Goal.class);
    RealmResults<Goal> results = query.findAll();

    return results.asObservable()
            .flatMap(new Func1<RealmResults<Goal>, Observable<Goal>>() {
              @Override
              public Observable<Goal> call(RealmResults<Goal> realmResults) {
                for (Goal goal : realmResults) {
                  goals.add(goal);
                }
                return Observable.from(goals);
              }
            })
            .toList();
  }

  @Override
  public List<Goal> getGoals() {
    final List<Goal> goals = new ArrayList<>();
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
        realm.copyToRealmOrUpdate(goal);
      }
    }, new Realm.Transaction.Callback() {
      @Override
      public void onSuccess() {
        Timber.d("Goal saved asynchronously.");
      }

      @Override
      public void onError(Exception e) {
        Timber.e(e, "Error occurred.");
      }
    });
  }
}