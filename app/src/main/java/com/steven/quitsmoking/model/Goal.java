package com.steven.quitsmoking.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Goal extends RealmObject {

  @PrimaryKey private String id;
  private String name;
  private String description;
  private Date dueDate;
  private boolean isAchieved;
  private Date createdAt;
  private Date updatedAt;
  private RealmList<Action> actions;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public boolean isAchieved() {
    return isAchieved;
  }

  public void setIsAchieved(boolean isAchieved) {
    this.isAchieved = isAchieved;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public RealmList<Action> getActions() {
    return actions;
  }

  public void setActions(RealmList<Action> actions) {
    this.actions = actions;
  }
}