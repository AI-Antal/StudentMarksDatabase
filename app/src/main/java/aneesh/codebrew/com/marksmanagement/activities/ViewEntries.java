package aneesh.codebrew.com.marksmanagement.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import aneesh.codebrew.com.marksmanagement.R;
import aneesh.codebrew.com.marksmanagement.adapters.RollnoAdapter;
import aneesh.codebrew.com.marksmanagement.adapters.SubjectListAdapter;
import aneesh.codebrew.com.marksmanagement.database.DbHelper;

public class ViewEntries extends AppCompatActivity
{
    RecyclerView subjectEntries,rollNoEntries;
    DbHelper dbHelper;
    SubjectListAdapter subjectListAdapter;
    RollnoAdapter rollnoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);
        dbHelper = new DbHelper(this);

        subjectEntries = (RecyclerView) findViewById(R.id.subjectEntriesRecycler);
        viewEntries();
    }

    public void viewEntries()
    {
        subjectEntries.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                rollNoEntries.scrollBy(0,dy);
            }
        });/*
        rollNoEntries.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                subjectEntries.scrollBy(0,dy);
            }
        });*/
        subjectListAdapter = new SubjectListAdapter(this,dbHelper.getDbData());
        subjectEntries.setAdapter(subjectListAdapter);
        subjectEntries.setLayoutManager(new LinearLayoutManager(this));

        rollNoEntries = (RecyclerView) findViewById(R.id.rollNoEntries);
        rollnoAdapter = new RollnoAdapter(this,dbHelper.getDbData());
        rollNoEntries.setAdapter(rollnoAdapter);
        rollNoEntries.setLayoutManager(new LinearLayoutManager(this));

        rollNoEntries.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
    }
}
