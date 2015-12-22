package com.steven.quitsmoking.interactor;

import com.steven.quitsmoking.model.Goal;

import java.util.List;

import rx.Observable;

public interface GoalInteractor {

  Observable<List<Goal>> getGoalsAsObservable();
  List<Goal> getGoals();
  void saveGoal(Goal goal);
}
