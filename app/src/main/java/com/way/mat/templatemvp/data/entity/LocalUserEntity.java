package com.way.mat.templatemvp.data.entity;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class LocalUserEntity extends RealmObject {

    @PrimaryKey
    private String email;

    private String firstName;

    private String lastName;

    public LocalUserEntity() {
    }

    public LocalUserEntity(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static void delete(Realm realm, String email) {
        LocalUserEntity item = realm.where(LocalUserEntity.class).equalTo("email", email).findFirst();
        // Otherwise it has been deleted already.
        if (item != null) {
            item.deleteFromRealm();
        }
    }
}
