package com.steven.quitsmoking.presenter;

import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.ui.MainView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

  @Test
  public void testLoadGoals_onGetData() {
    // Arrange
    MainView mockView = mock(MainView.class);

    // Act
    MainPresenterImpl mainPresenter = new MainPresenterImpl(mockView);
    mainPresenter.loadGoals();

    // Assert
    verify(mockView).onGetData(Collections.<Goal>emptyList());
  }
}
