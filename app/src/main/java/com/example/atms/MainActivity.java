package com.example.atms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity  {

    private MeowBottomNavigation meowBottomNavigation;
    final static int ID_REQUESTS=1;
    final static int ID_DEPARTMENTS=2;
    final static int ID_EMPLOYEES=3;
    final static int ID_NOTIFICATIONS=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        meowBottomNavigation = findViewById(R.id.bottomNav);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(ID_REQUESTS,R.drawable.requests));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(ID_DEPARTMENTS,R.drawable.departments));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(ID_EMPLOYEES,R.drawable.employees));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATIONS,R.drawable.notification));

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new notifications()).commit();


        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {


            }
        });

        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {

                    case ID_REQUESTS:
                        fragment = new requests();
                        break;

                    case ID_EMPLOYEES:
                        fragment = new employees();
                        break;

                    case ID_NOTIFICATIONS:
                        fragment = new notifications();
                        break;

                    case ID_DEPARTMENTS:
                        fragment = new departments();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
              //  Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}