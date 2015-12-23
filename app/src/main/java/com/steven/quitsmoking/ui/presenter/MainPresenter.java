package com.steven.quitsmoking.ui.presenter;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.view.MainActivityView;

import java.util.List;

public class MainPresenter {

  private MainActivityView view;
  private GoalInteractor interactor;

  public MainPresenter(MainActivityView view, GoalInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  public void loadGoals() {
    List<Goal> goals = interactor.getGoals();
    view.onDataLoaded(goals);
  }
}