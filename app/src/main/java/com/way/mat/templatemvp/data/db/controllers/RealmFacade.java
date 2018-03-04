package com.way.mat.templatemvp.data.db.controllers;

import android.text.TextUtils;

import com.way.mat.templatemvp.data.entity.LocalUserEntity;

import java.util.List;

import io.realm.Realm;

public class RealmFacade {

    public List<LocalUserEntity> getUser(String email) {
        Realm realm = Realm.getDefaultInstance();
        List<LocalUserEntity> users = realm.copyFromRealm(
                realm.where(LocalUserEntity.class)
                        .equalTo("email", email)
                        .findAll());
        realm.close();
        return users;
    }

    public List<LocalUserEntity> getUsers() {
        Realm realm = Realm.getDefaultInstance();
        List<LocalUserEntity> alerts = realm.copyFromRealm(
                realm.where(LocalUserEntity.class)
                        .findAll());
        realm.close();
        return alerts;
    }

    public void addUser(LocalUserEntity user) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
        realm.close();
    }

    public void deleteUser(Realm realm, LocalUserEntity user) {
        if (user != null && !TextUtils.isEmpty(user.getEmail())) {
            realm.executeTransactionAsync(realm1 -> LocalUserEntity.delete(realm1, user.getEmail()));
        }
    }

    public void deleteAllUsers(Realm realm) {
        realm.executeTransaction(realm1 -> realm1.delete(LocalUserEntity.class));
    }

}
