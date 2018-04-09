package com.example.asus.cobarealmdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asus.cobarealmdb.model.Person;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    // widget
    private EditText etName, etEmail;
    private Button btView, btAdd, btDelete;
    private TextView tvPerson;

    // realm
    private Realm realm;
    private String TAG_DB = "dbRealm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instansiasi Realm instance untuk thread
        realm = Realm.getDefaultInstance(); // opens "default.realm"

//        try {
//            // ... Do something ...
//        } finally { /*untuk android, akhiri thread di onDestroy*/
//            realm.close();
//        }


        //widget
        // widget
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        btView = findViewById(R.id.bt_view);
        btAdd = findViewById(R.id.bt_add);
        btDelete = findViewById(R.id.bt_delete);
        tvPerson = findViewById(R.id.tv_person);

        // click button add
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ambil nilai dari widget
                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();
                /**
                 * panggil method insertPersonData
                 * membawa param name, email
                 */
                insertPersonData(name, email);
            }
        });

        // click button delete
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ambil nilai dari widget
                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();
                /**
                 * panggil method removePersonData
                 * membawa parameter name
                 */
                removePersonData(name);
            }
        });

        // click button view
        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // panggil method refreshPersonData
                refreshPersonData();
            }
        });

    }

    // refresh all data person
    private void refreshPersonData() {
        RealmResults<Person> result = realm.where(Person.class)
                .findAllAsync();

        result.load();
        String output = "";
        for (Person person : result) {
            output += person.toString();
            Log.d(TAG_DB, "person data : " + output);
        }
        tvPerson.setText(output);
    }

    /**
     * method untuk save data ke database
     *
     * @param name  untuk nama
     * @param email untuk alamat email
     */
    private void insertPersonData(final String name, final String email) {
        // write transaction
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Person person = bgRealm.createObject(Person.class);
                person.setName(name);
                person.setEmail(email);
                //  person.setName("John"); // data statis
                // person.setEmail("john@corporation.com"); // data statis

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d(TAG_DB, "berhasil insert data!");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d(TAG_DB, "gagal insert data. Penyebab: " + error.getMessage());
            }
        });
    }

    /**
     * method untuk remove 1 data person
     *
     * @param name untuk nama
     */
    private void removePersonData(String name) {
        // delete result
        final RealmResults<Person> personRealmResults = realm.where(Person.class)
                .equalTo("name", name)
                .findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                personRealmResults.get(0).deleteFromRealm(); // Delete and remove object directly
                Log.d(TAG_DB, "berhasil delete data!");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //akhiri thread
        realm.close();
    }


    private List<Person> getAllPerson(){
        List<Person> personList = new ArrayList<Person>();

        //realm
//        RealmResults<Person> result = realm.where(Person.class)
//                .findAllAsync();
//
//        result.load();
//        String output = "";
//        for (Person person : result) {
//            output += person.toString();
//            Log.d(TAG_DB, "person data : " + output);
//        }

        return null;
    }

}
