package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.ui.presenter.GoalPresenter;
import com.steven.quitsmoking.ui.view.GoalActivityView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GoalPresenterTest {

  @Test
  public void saveGoalShouldCallOnGoalSaved() {
    // Arrange
    GoalActivityView mockView = mock(GoalActivityView.class);
    GoalInteractor mockInteractor = mock(GoalInteractor.class);

    // Assert
    GoalPresenter presenter = new GoalPresenter(mockView, mockInteractor);
    presenter.saveGoal("Quit smoking", "Quit smoking in one week", "01/12/2015");

    // Act
    verify(mockView).onGoalSaved();
  }

  @Test
  public void emptyNameShouldCallOnShowError() {
    // Arrange
    GoalActivityView mockView = mock(GoalActivityView.class);
    GoalInteractor mockInteractor = mock(GoalInteractor.class);

    // Assert
    GoalPresenter presenter = new GoalPresenter(mockView, mockInteractor);
    presenter.saveGoal(null, "How to quit smoking in a week?", "01/12/2015");

    // Act
    verify(mockView).onShowError();
  }

  @Test
  public void emptyDueDateShouldCallOnShowError() {
    // Arrange
    GoalActivityView mockView = mock(GoalActivityView.class);
    GoalInteractor mockInteractor = mock(GoalInteractor.class);

    // Assert
    GoalPresenter presenter = new GoalPresenter(mockView, mockInteractor);
    presenter.saveGoal("Quit smoking", "How to quit smoking in a week?", null);

    // Act
    verify(mockView).onShowError();
  }
}