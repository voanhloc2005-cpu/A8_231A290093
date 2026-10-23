package vn.edu.vhu.ltdd.a8fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            showFragment(new HomeFragment(), false);
            bottomNavigation.setSelectedItemId(R.id.nav_home);
        }

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment fragment;

            if (id == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (id == R.id.nav_category) {
                fragment = new NewsListFragment();
            } else if (id == R.id.nav_saved) {
                fragment = new SavedFragment();
            } else {
                return false;
            }

            showFragment(fragment, false);
            return true;
        });
    }

    private void showFragment(Fragment fragment, boolean addToBackStack) {
        androidx.fragment.app.FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
