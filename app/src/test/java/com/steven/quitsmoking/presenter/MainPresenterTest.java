package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.MainActivityView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

  @Test
  public void emptyListShouldInvokeOnGetData() {
    // Arrange
    MainActivityView mockView = mock(MainActivityView.class);
    GoalInteractor mockInteractor = mock(GoalInteractor.class);

    // Act
    MainPresenterImpl mainPresenter = new MainPresenterImpl(mockView, mockInteractor);
    mainPresenter.loadGoals();

    // Assert
    verify(mockView).onGetData(Collections.<Goal>emptyList());
  }
}
