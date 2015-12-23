package com.steven.quitsmoking.ui.view;

import com.steven.quitsmoking.model.Goal;

import java.util.List;

public interface MainActivityView {
  void onDataLoaded(List<Goal> goals);
}
