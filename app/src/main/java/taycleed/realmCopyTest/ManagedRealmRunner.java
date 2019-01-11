package taycleed.realmCopyTest;

import android.util.Log;
import io.realm.Realm;
import taycleed.realmCopyTest.model.Constant;
import taycleed.realmCopyTest.model.MyBigRealmObject;
import taycleed.realmCopyTest.model.MyRealmObject;

import java.util.Locale;
import java.util.Objects;

import static taycleed.realmCopyTest.UtilKt.*;


/**
 * Run write-operations with managed RealmObject only
 */
public class ManagedRealmRunner {
    private static final String LOG_TAG = Constant.LOG_TAG_PREFIX + ManagedRealmRunner.class.getSimpleName();

    private static class SingletonHolder {
        private static final ManagedRealmRunner INSTANCE = new ManagedRealmRunner();
    }

    public static ManagedRealmRunner getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private ManagedRealmRunner() {}

    public void init() {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(realm1 -> {
                realm1.deleteAll();

                for (int i = 0; i < Constant.COUNT; i++) {
                    MyRealmObject obj = realm1.createObject(MyRealmObject.class, i+1);
                    obj.setName(getCharAtoZ(i));

                    MyBigRealmObject bigObj = realm1.createObject(MyBigRealmObject.class, i+1);
                    bigObj.setArg1(getCharAtoZ(i));
                    bigObj.setArg2(getCharAtoZ(i+1));
                    bigObj.setArg3(getCharAtoZ(i+2));
                    bigObj.setArg4(getCharAtoZ(i+3));
                    bigObj.setArg5(getCharAtoZ(i+4));
                    bigObj.setArg6(getCharAtoZ(i+5));
                    bigObj.setArg7(getCharAtoZ(i+6));
                    bigObj.setArg8(getCharAtoZ(i+7));
                    bigObj.setArg9(getCharAtoZ(i+8));
                    bigObj.setArg10(getCharAtoZ(i+9));
                }
            });
        }
    }

    public void peek() {
        try (Realm realm = Realm.getDefaultInstance()) {
            String a = Objects.requireNonNull(realm.where(MyRealmObject.class).equalTo("id", 1).findFirst()).getName();
            String b = Objects.requireNonNull(realm.where(MyRealmObject.class).equalTo("id", 2).findFirst()).getName();
            String c = Objects.requireNonNull(realm.where(MyRealmObject.class).equalTo("id", 3).findFirst()).getName();
            Log.d(LOG_TAG, "Peek-MyRealmObject: " + a + " / " + b + " / " + c);
        }
    }

    public void peekBig() {
        try (Realm realm = Realm.getDefaultInstance()) {
            MyBigRealmObject obj1 = realm.where(MyBigRealmObject.class).equalTo("id", 1).findFirst();
            MyBigRealmObject obj2 = realm.where(MyBigRealmObject.class).equalTo("id", 2).findFirst();
            MyBigRealmObject obj3 = realm.where(MyBigRealmObject.class).equalTo("id", 3).findFirst();
            assert obj1 != null;
            assert obj2 != null;
            assert obj3 != null;
            Log.d(LOG_TAG, String.format(Locale.US, "Peek-MyBigRealmObject: %s%s%s%s%s%s%s%s%s%s / %s%s%s%s%s%s%s%s%s%s / %s%s%s%s%s%s%s%s%s%s",
                    obj1.getArg1(), obj1.getArg2(), obj1.getArg3(), obj1.getArg4(), obj1.getArg5(), obj1.getArg6(), obj1.getArg7(), obj1.getArg8(), obj1.getArg9(), obj1.getArg10(),
                    obj2.getArg1(), obj2.getArg2(), obj2.getArg3(), obj2.getArg4(), obj2.getArg5(), obj2.getArg6(), obj2.getArg7(), obj2.getArg8(), obj2.getArg9(), obj2.getArg10(),
                    obj3.getArg1(), obj3.getArg2(), obj3.getArg3(), obj3.getArg4(), obj3.getArg5(), obj3.getArg6(), obj3.getArg7(), obj3.getArg8(), obj3.getArg9(), obj3.getArg10()) );
        }
    }


    /**
     * Get a RO, edit 1 column, write.
     */
    public long testRun01() {
        check();
        try (Realm realm = Realm.getDefaultInstance()) {
            for(int i =0; i < Constant.COUNT; i++) {
                MyRealmObject ro = realm.where(MyRealmObject.class).equalTo("id", i+1).findFirst();
                realm.executeTransaction(realm1 -> ro.setName(getCharAtoZ(ro.getName().charAt(0) + 1)));
            }
        }
        return end();
    }

    /**
     * Get a RO, edit 10 column, write.
     */
    public long testRun02() {
        check();
        try (Realm realm = Realm.getDefaultInstance()) {
            for(int i =0; i < Constant.COUNT; i++) {
                MyBigRealmObject ro = realm.where(MyBigRealmObject.class).equalTo("id", i+1).findFirst();
                realm.executeTransaction(realm1 -> {
                    ro.setArg1(getCharAtoZ(ro.getArg1().charAt(0) + 1));
                    ro.setArg2(getCharAtoZ(ro.getArg2().charAt(0) + 1));
                    ro.setArg3(getCharAtoZ(ro.getArg3().charAt(0) + 1));
                    ro.setArg4(getCharAtoZ(ro.getArg4().charAt(0) + 1));
                    ro.setArg5(getCharAtoZ(ro.getArg5().charAt(0) + 1));
                    ro.setArg6(getCharAtoZ(ro.getArg6().charAt(0) + 1));
                    ro.setArg7(getCharAtoZ(ro.getArg7().charAt(0) + 1));
                    ro.setArg8(getCharAtoZ(ro.getArg8().charAt(0) + 1));
                    ro.setArg9(getCharAtoZ(ro.getArg9().charAt(0) + 1));
                    ro.setArg10(getCharAtoZ(ro.getArg10().charAt(0) + 1));
                });
            }
        }
        return end();
    }
}
