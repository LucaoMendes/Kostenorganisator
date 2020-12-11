package br.com.mendes.kostenorganisator.realm;

import android.app.Application;

import br.com.mendes.kostenorganisator.realm.models.AtividadeModelR;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class KostenorganisatorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("kostenorganisator.realm")
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
