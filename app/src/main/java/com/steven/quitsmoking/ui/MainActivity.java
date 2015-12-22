package com.steven.quitsmoking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.steven.quitsmoking.R;
import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.interactor.GoalInteractorImpl;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.presenter.MainPresenter;
import com.steven.quitsmoking.presenter.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainActivityView {

  private MainPresenter presenter;
  private Realm realm;
  private GoalArrayAdapter adapter;
  private List<Goal> goals = new ArrayList<>();
  private ListView listView;
  private GoalInteractor interactor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    listView = (ListView) findViewById(R.id.listView_goal);
    adapter = new GoalArrayAdapter(this, android.R.layout.simple_list_item_1, goals);
    listView.setAdapter(adapter);

    realm = Realm.getDefaultInstance();
    if (interactor == null) {
      interactor = new GoalInteractorImpl(realm);
    }

    if (presenter == null) {
      presenter = new MainPresenterImpl(this, interactor);
    }
    presenter.loadGoals();
  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.loadGoals();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.add_goal:
        Intent intent = new Intent(this, GoalActivity.class);
        startActivity(intent);
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (isFinishing()) {
      presenter = null;
    }
    realm.close();
  }

  @Override
  public void onGetData(List<Goal> goals) {
    adapter.clear();
    adapter.addAll(goals);
  }
}