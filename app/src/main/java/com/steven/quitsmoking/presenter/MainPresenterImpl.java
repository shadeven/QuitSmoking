package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.MainActivityView;

import java.util.List;

public class MainPresenterImpl implements MainPresenter {

  private MainActivityView view;
  private GoalInteractor interactor;

  public MainPresenterImpl(MainActivityView view, GoalInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  @Override
  public void loadGoals() {
    List<Goal> goals = interactor.getGoals();

    if (goals.isEmpty()) {
      // TODO: Display a message
    }
    view.onGetData(goals);
  }
}
