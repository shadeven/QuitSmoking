package com.steven.quitsmoking.presenter;

import android.support.annotation.NonNull;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.GoalActivityView;

import java.util.UUID;

public class GoalPresenterImpl implements GoalPresenter {

  private GoalActivityView view;
  private GoalInteractor interactor;

  public GoalPresenterImpl(GoalActivityView view, GoalInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  @Override
  public void saveGoal(String name, String description) {
    Goal goal = createGoal(name, description);
    interactor.saveGoal(goal);
    view.onGoalSaved();
  }

  @NonNull
  private Goal createGoal(String name, String description) {
    Goal goal = new Goal();
    goal.setId(UUID.randomUUID().toString());
    goal.setName(name);
    goal.setDescription(description);
//    goal.setDueDate(); // TODO: To be implemented
    return goal;
  }
}
