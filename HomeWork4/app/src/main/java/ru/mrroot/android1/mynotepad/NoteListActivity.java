package ru.mrroot.android1.mynotepad;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    private RecyclerAdapter noteAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }
    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {

        final RecyclerView rnotes = (RecyclerView) findViewById(R.id.list_notes);
        final LinearLayoutManager noteslayout = new LinearLayoutManager(this);
        rnotes.setLayoutManager(noteslayout);

        List<NoteInfo> cnote = DataManager.getInstance().getNotes();
        noteAdapter = new RecyclerAdapter(this,cnote);
        rnotes.setAdapter(noteAdapter);
    }

}
