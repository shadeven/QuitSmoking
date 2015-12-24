package com.steven.quitsmoking.ui.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.view.GoalActivityView;
import com.steven.quitsmoking.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import timber.log.Timber;

public class GoalPresenter {

  private GoalActivityView view;
  private GoalInteractor interactor;

  public GoalPresenter(GoalActivityView view, GoalInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  public void saveGoal(String name, String description, String dueDate) {
    if (StringUtils.isEmpty(name) || dueDate == null) {
      view.onShowError();
      return;
    }

    Date date = null;
    try {
      if (!StringUtils.isEmpty(dueDate)) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormatter.parse(dueDate);
      }
    } catch (ParseException e) {
      Timber.e(e, "Error occurred.");
    }

    Goal goal = createGoal(name, description, date);
    interactor.saveGoal(goal);
    view.onGoalSaved();
  }

  @NonNull
  private Goal createGoal(String name, String description, Date dueDate) {
    Goal goal = new Goal();
    goal.setId(UUID.randomUUID().toString());
    goal.setName(name);
    goal.setDescription(description);
    goal.setDueDate(dueDate);
    return goal;
  }
}
