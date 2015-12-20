package com.steven.quitsmoking.ui;

import com.steven.quitsmoking.model.Goal;

import java.util.List;

public interface MainView {
  void onGetData(List<Goal> goals);
}
