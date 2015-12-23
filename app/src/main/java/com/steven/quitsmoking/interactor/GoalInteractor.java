package com.steven.quitsmoking.interactor;

import com.steven.quitsmoking.model.Goal;

import java.util.List;

import rx.Observable;

public interface GoalInteractor {
  Observable<List<Goal>> getGoalsAsObservable();
  List<Goal> getGoals();
  void saveGoalAsync(Goal goal);
  void saveGoal(Goal goal);
}
