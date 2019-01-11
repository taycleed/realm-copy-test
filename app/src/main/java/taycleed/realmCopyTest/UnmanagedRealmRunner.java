package taycleed.realmCopyTest;

import io.realm.Realm;
import taycleed.realmCopyTest.model.Constant;
import taycleed.realmCopyTest.model.MyBigRealmObject;
import taycleed.realmCopyTest.model.MyRealmObject;

import java.util.Objects;

import static taycleed.realmCopyTest.UtilKt.*;

/**
 * Run write-operations with un-managed RealmObject and copying only
 */
public class UnmanagedRealmRunner {
    private static final String LOG_TAG = Constant.LOG_TAG_PREFIX + UnmanagedRealmRunner.class.getSimpleName();

    private static class SingletonHolder {
        private static final UnmanagedRealmRunner INSTANCE = new UnmanagedRealmRunner();
    }

    public static UnmanagedRealmRunner getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private UnmanagedRealmRunner() {}

    /**
     * Get a RO, edit 1 column, write.
     */
    public long testRun01() {
        check();
        try(Realm realm = Realm.getDefaultInstance()) {
            for(int i =0; i < Constant.COUNT; i++) {
                final MyRealmObject ro = realm.copyFromRealm(
                        Objects.requireNonNull(realm.where(MyRealmObject.class).equalTo("id", i + 1).findFirst()));
                ro.setName(getCharAtoZ(ro.getName().charAt(0) + 1));
                realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(ro));
            }
        }
        return end();
    }

    /**
     * Get a RO, edit 10 column, write.
     */
    public long testRun02() {
        check();
        try(Realm realm = Realm.getDefaultInstance()) {
            for(int i =0; i < Constant.COUNT; i++) {
                final MyBigRealmObject ro = realm.copyFromRealm(
                        Objects.requireNonNull(realm.where(MyBigRealmObject.class).equalTo("id", i + 1).findFirst()));
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

                realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(ro));
            }
        }
        return end();
    }
}
