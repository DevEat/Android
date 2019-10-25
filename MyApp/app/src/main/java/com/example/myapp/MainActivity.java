package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_contacts ;
    ListView listview;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ListViewAdapter();

        listview = (ListView)findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        this.InitializeView();
        this.SetListener();
    }

    public void InitializeView() {
        btn_contacts = (Button)findViewById(R.id.btn_contacts);
    }


    public void SetListener() {
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_contacts:
                        Toast.makeText(MainActivity.this, "contacts button clicked", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        startActivityForResult(intent, 0);
                        break;
                }
            }
        };

        btn_contacts.setOnClickListener(Listener);
    }

    public void LoadContacts(){


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == 0) {
            String[] projection = new String[]{
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            };

            Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);
            if(cursor.moveToFirst()) {
                //이름획득
                String receiveName = cursor.getString(0);
                //전화번호 획득
                String receivePhone = cursor.getString(1);
                cursor.close();

                //리스트뷰에 아이템 추가
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_box_black_36dp),
                        receiveName, receivePhone);
                adapter.notifyDataSetChanged();

                //Toast.makeText(this, "이름 : " + receiveName + "\n전화번호 : " + receivePhone, Toast.LENGTH_LONG).show();
            }
        }
    }
}