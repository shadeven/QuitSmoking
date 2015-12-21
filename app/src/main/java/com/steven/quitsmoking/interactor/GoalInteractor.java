package com.steven.quitsmoking.interactor;

import com.steven.quitsmoking.model.Goal;

import java.util.List;

public interface GoalInteractor {

  List<Goal> getGoals();
  void saveGoal(Goal goal);
}
