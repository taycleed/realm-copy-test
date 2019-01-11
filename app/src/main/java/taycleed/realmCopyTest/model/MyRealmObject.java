package taycleed.realmCopyTest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MyRealmObject extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;

    public MyRealmObject(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}