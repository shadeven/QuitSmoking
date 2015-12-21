package com.steven.quitsmoking.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.steven.quitsmoking.R;
import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.interactor.GoalInteractorImpl;
import com.steven.quitsmoking.model.Goal;
import com.steven.quitsmoking.presenter.GoalPresenterImpl;
import com.steven.quitsmoking.presenter.GoalPresenter;

import java.util.List;

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
    setContentView(R.layout.activity_new_goal);

    name = (EditText) findViewById(R.id.editText_name);
    description = (EditText) findViewById(R.id.editText_description);

    realm = Realm.getDefaultInstance();
    if (interactor == null) {
      interactor = new GoalInteractorImpl(realm);
    }

    if (presenter == null) {
      presenter = new GoalPresenterImpl(this, interactor);
    }
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
      presenter.saveAndLoadData(name.getText().toString(), description.getText().toString());
      finish();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onSaveData(List<Goal> goals) {
    // TODO:
  }
}