package com.steven.quitsmoking.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.steven.quitsmoking.R;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.presenter.AddGoalPresenterImpl;
import com.steven.quitsmoking.presenter.AddGoalPrsenter;

import java.util.List;

import io.realm.Realm;

public class AddGoalActivity extends AppCompatActivity implements AddGoalActivityView {

  private AddGoalPrsenter presenter;
  private Realm realm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_goal);

    realm = Realm.getDefaultInstance();
    presenter = new AddGoalPresenterImpl(realm, this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_new_goal, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_save) {
      presenter.saveAndLoadData();
      Intent intent = new Intent(this, AddGoalActivity.class);
      startActivity(intent);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onSaveData(List<Goal> goals) {

  }
}
