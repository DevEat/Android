package com.example.a20190920a;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ImageButton btn_refresh, btn_menu;
    Button btn_phone, btn_phone_a, btn_schedule, btn_home, btn_search, btn_message, btn_mypage;

    ListView listview ;
    ListViewAdapter adapter;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    Cursor c;
    ArrayList<String> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.InitializeView();
        this.SetListener();
    }

    public void InitializeView() {
        btn_refresh = (ImageButton)findViewById(R.id.btn_refresh);
        btn_menu = (ImageButton)findViewById(R.id.btn_menu);

        btn_phone = (Button)findViewById(R.id.btn_phone);
        btn_phone_a = (Button)findViewById(R.id.btn_phone_a);
        btn_schedule = (Button)findViewById(R.id.btn_schedule);
        btn_home = (Button)findViewById(R.id.btn_home);
        btn_search = (Button)findViewById(R.id.btn_search);
        btn_message = (Button)findViewById(R.id.btn_message);
        btn_mypage = (Button)findViewById(R.id.btn_mypage);

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listView);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            showContacts();
            Log.d(TAG, "permissionCheck: " + permissionCheck);
        }else{
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS
            );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, contacts
        );

        listview.setAdapter(adapter);
    }

    public void SetListener() {
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_refresh:
                        // 전화번호부 동기화 코드 함수 호출
                        showContacts();
                        break;
                    case R.id.btn_schedule:
                        break;
                }
            }
        };

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                Drawable iconDrawable = item.getIcon() ;
                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;

                // TODO : use item data.
            }
        }) ;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showContacts();
            }else{

            }
        }
    }

    private void showContacts(){
        c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        contacts = new ArrayList<>();
        while(c.moveToNext()){
            String contactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String contactNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add("Name : " + contactName + "\nNumber : " + contactNumber);

            Log.d(TAG, "Contacts: " + contacts);
        }
        c.close();
    }
}
