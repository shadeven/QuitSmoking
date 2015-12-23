package com.steven.quitsmoking.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.steven.quitsmoking.R;
import com.steven.quitsmoking.model.Goal;

import java.util.List;

public class GoalArrayAdapter extends ArrayAdapter<Goal> {

  private final Context context;
  private final int resource;
  private final List<Goal> goals;

  public GoalArrayAdapter(Context context, int resource, List<Goal> goals) {
    super(context, resource, goals);
    this.context = context;
    this.resource = resource;
    this.goals = goals;
  }

  @Override
  public int getCount() {
    return goals.size();
  }

  @Override
  public Goal getItem(int position) {
    return goals.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View rowView = inflater.inflate(R.layout.row_item_goal, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.label);
    textView.setText(goals.get(position).getName());
    return rowView;
  }
}
