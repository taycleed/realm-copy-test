package taycleed.realmCopyTest

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Realm
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}
