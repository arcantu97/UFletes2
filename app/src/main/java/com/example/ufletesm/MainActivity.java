package com.example.ufletesm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ufletesm.fragments.fleterosFragment;
import com.example.ufletesm.fragments.homeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomNav;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNav = findViewById(R.id.btnNav_inicio_fletero);

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home_fletero:

                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, new homeFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;

                    case R.id.menu_pedidos_fletero:

                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, new fleterosFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                }
                return false;
            }
        });


    }

}
