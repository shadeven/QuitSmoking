package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.GoalActivityView;

import java.util.List;
import java.util.UUID;

public class GoalPresenterImpl implements GoalPresenter {

  private GoalActivityView view;
  private GoalInteractor interactor;

  public GoalPresenterImpl(GoalActivityView view, GoalInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  @Override
  public void saveData(String name, String description) {
    Goal goal = new Goal();
    goal.setId(UUID.randomUUID().toString());
    goal.setName(name);
    goal.setDescription(description);
//    goal.setDueDate(); // TODO: To be implemented
    interactor.saveGoal(goal);
    view.onSaveData();
  }
}
