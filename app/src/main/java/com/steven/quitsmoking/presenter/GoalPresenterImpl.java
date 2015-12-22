package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.GoalActivityView;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GoalPresenterImpl implements GoalPresenter {

  private GoalActivityView view;
  private GoalInteractor interactor;

  public GoalPresenterImpl(GoalActivityView view, GoalInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  @Override
  public void saveAndLoadData(String name, String description) {
    Goal goal = new Goal();
    goal.setId(new Random().nextInt()); // FIXME: To be replaced
    goal.setName(name);
    goal.setDescription(description);
    interactor.saveGoal(goal);

    List<Goal> goals = interactor.getGoals();
    if (goals.isEmpty()) {
      // TODO:
    } else {
      view.onSaveData(goals);
    }
  }
}
