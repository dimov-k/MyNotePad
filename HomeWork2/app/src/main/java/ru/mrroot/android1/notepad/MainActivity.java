package ru.mrroot.android1.notepad;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    private DrawerLayout lDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        if (savedInstanceState == null) {
            initNotesList();
        }

        lDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, lDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        lDrawer.addDrawerListener(actionBarDrawerToggle);
        NavigationView navigationView = findViewById(R.id.side_navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_about) {
                Snackbar.make(findViewById(R.id.drawer_layout), "About", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(findViewById(R.id.drawer_layout), "Settings", Snackbar.LENGTH_SHORT).show();
            }
            lDrawer.closeDrawer(GravityCompat.START);
            return true;
        });
        actionBarDrawerToggle.syncState();
    }


    private void initNotesList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NotesListFragment notesListFragment = new NotesListFragment();
        fragmentTransaction.add(R.id.note_list_container, notesListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem search = menu.findItem(R.id.toolbar_menu_search);
        SearchView searchView = (SearchView) search.getActionView();
        MenuItem sort = menu.findItem(R.id.toolbar_menu_sort);
        MenuItem addPhoto = menu.findItem(R.id.toolbar_menu_add_photo);
        MenuItem share = menu.findItem(R.id.toolbar_menu_share);

        share.setOnMenuItemClickListener(this);
        addPhoto.setOnMenuItemClickListener(this);
        sort.setOnMenuItemClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Snackbar.make(findViewById(R.id.drawer_layout), item.getTitle().toString(), Snackbar.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onBackPressed() {
        if (lDrawer.isDrawerOpen(GravityCompat.START)) {
            lDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }
}