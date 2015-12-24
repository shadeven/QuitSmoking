package com.steven.quitsmoking.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.steven.quitsmoking.R;
import com.steven.quitsmoking.interactor.GoalInteractor;
import com.steven.quitsmoking.interactor.GoalInteractorImpl;
import com.steven.quitsmoking.ui.presenter.GoalPresenter;
import com.steven.quitsmoking.ui.view.GoalActivityView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

public class GoalActivity extends AppCompatActivity implements GoalActivityView,
        View.OnClickListener, DatePickerDialog.OnDateSetListener {

  private GoalPresenter presenter;
  private Realm realm;
  private GoalInteractor interactor;
  private EditText name;
  private EditText description;
  private EditText dueDate;
  private DatePickerDialog datePickerDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_goal);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    name = (EditText) findViewById(R.id.editText_name);
    description = (EditText) findViewById(R.id.editText_description);

    dueDate = (EditText) findViewById(R.id.editText_dueDate);
    dueDate.setOnClickListener(this);

    Calendar calendar = Calendar.getInstance();
    datePickerDialog = new DatePickerDialog(this, this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH));

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
    switch (item.getItemId()) {
      case R.id.action_refresh:
        presenter.saveGoal(name.getText().toString(), description.getText().toString());
        return true;

      case R.id.action_back:
        finish();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void onGoalSaved() {
    Toast.makeText(this, "Goal saved.", Toast.LENGTH_SHORT).show();
    finish();
  }

  @Override
  public void onClick(View view) {
    if (view == dueDate) {
      datePickerDialog.show();
    }
  }

  @Override
  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    Calendar newDate = Calendar.getInstance();
    newDate.set(year, monthOfYear, dayOfMonth);
    dueDate.setText(dateFormatter.format(newDate.getTime()));
  }
}