package com.steven.quitsmoking.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.steven.quitsmoking.R;
import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.interactor.GoalInteractorImpl;
import com.steven.quitsmoking.ui.presenter.GoalPresenter;
import com.steven.quitsmoking.ui.view.GoalActivityView;

import io.realm.Realm;

public class GoalActivity extends AppCompatActivity implements GoalActivityView {

  private GoalPresenter presenter;
  private Realm realm;
  private GoalInteractor interactor;
  private EditText name;
  private EditText description;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_goal);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    name = (EditText) findViewById(R.id.editText_name);
    description = (EditText) findViewById(R.id.editText_description);

    realm = Realm.getDefaultInstance();
    if (interactor == null) {
      interactor = new GoalInteractorImpl(realm);
    }

    if (presenter == null) {
      presenter = new GoalPresenter(this, interactor);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_goal, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_refresh) {
      presenter.saveGoal(name.getText().toString(), description.getText().toString());
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onGoalSaved() {
    Toast.makeText(this, "Goal saved.", Toast.LENGTH_SHORT).show();
    finish();
  }
}