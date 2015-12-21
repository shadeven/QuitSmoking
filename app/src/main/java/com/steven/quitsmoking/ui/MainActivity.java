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
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.presenter.MainPresenter;
import com.steven.quitsmoking.presenter.MainPresenterImpl;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainActivityView {

  private MainPresenter presenter;
  private Realm realm;
  private ArrayAdapter<Goal> adapter;
  private ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    listView = (ListView) findViewById(R.id.listView_goal);
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    listView.setAdapter(adapter);

    realm = Realm.getDefaultInstance();
    if (presenter == null) {
      presenter = new MainPresenterImpl(realm, this);
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
        Intent intent = new Intent(this, AddGoalActivity.class);
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