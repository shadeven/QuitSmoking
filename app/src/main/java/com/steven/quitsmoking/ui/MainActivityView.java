package com.steven.quitsmoking.ui;

import com.steven.quitsmoking.model.Goal;

import java.util.List;

public interface MainActivityView {
  void onDataLoaded(List<Goal> goals);
}
