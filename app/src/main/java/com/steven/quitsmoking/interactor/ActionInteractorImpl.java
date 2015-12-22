package com.steven.quitsmoking.interactor;

import com.steven.quitsmoking.model.Action;

import io.realm.Realm;

public class ActionInteractorImpl implements ActionInteractor {

  private Realm realm;

  public ActionInteractorImpl(Realm realm) {
    this.realm = realm;
  }

  @Override
  public void saveAction(final Action action) {
    realm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        realm.copyToRealm(action);
      }
    });
  }
}
