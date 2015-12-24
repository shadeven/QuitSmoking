package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.presenter.MainPresenter;
import com.steven.quitsmoking.ui.view.MainActivityView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

  @Test
  public void emptyListShouldCallOnGetData() {
    // Arrange
    MainActivityView mockView = mock(MainActivityView.class);
    GoalInteractor mockInteractor = mock(GoalInteractor.class);

    // Act
    MainPresenter mainPresenter = new MainPresenter(mockView, mockInteractor);
    mainPresenter.loadGoals();

    // Assert
    verify(mockView).onDataLoaded(Collections.<Goal>emptyList());
  }
}
