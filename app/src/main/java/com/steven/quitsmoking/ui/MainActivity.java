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

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

  private MainPresenter presenter;
  private Realm realm;
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ListView goalListView = (ListView) findViewById(R.id.goal_listView);
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    goalListView.setAdapter(adapter);

    realm = Realm.getDefaultInstance();
    if (presenter == null) {
      presenter = new MainPresenter(realm);
    }
    presenter.onTakeView(this);
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
        Intent intent = new Intent(this, NewGoalActivity.class);
        startActivity(intent);
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.onTakeView(null);
    if (isFinishing()) {
      presenter = null;
    }

    realm.close();
  }

  public void onItemNext(List<String> results) {
    adapter.clear();
    adapter.addAll(results);
  }
}