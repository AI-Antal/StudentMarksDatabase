package aneesh.codebrew.com.marksmanagement.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import aneesh.codebrew.com.marksmanagement.R;
import aneesh.codebrew.com.marksmanagement.database.DbHelper;
import aneesh.codebrew.com.marksmanagement.model.DataModel;

public class SearchStudent extends AppCompatActivity implements View.OnClickListener
{

    private EditText etSearchRollno;
    private TextView tvSearch, tvSubject, tvName, tvStream, tvMarks, tvTotal;
    DbHelper dbHelper;
    int rollno;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);
        initializeViews();

        dbHelper = new DbHelper(getApplicationContext());
        tvSearch.setOnClickListener(this);
    }

    private void initializeViews()
    {
        etSearchRollno = (EditText) findViewById(R.id.etSearchRollno);
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        tvSubject = (TextView) findViewById(R.id.tvSubject);

        tvName= (TextView) findViewById(R.id.tvName);
        tvStream= (TextView) findViewById(R.id.tvStream);
        tvMarks= (TextView) findViewById(R.id.tvMarks);
        tvTotal= (TextView) findViewById(R.id.tvTotal);

        tvSubject.append(getString(R.string.english)+"\n\n");
        tvSubject.append(getString(R.string.maths)+"\n\n");
        tvSubject.append(getString(R.string.hindi)+"\n\n");
        tvSubject.append(getString(R.string.chemistry)+"\n\n");
        tvSubject.append(getString(R.string.physics)+"\n");
    }

    @Override
    public void onClick(View v)
    {
        rollno = Integer.parseInt(etSearchRollno.getText().toString());

        boolean checkRollno = dbHelper.checkRollno(rollno);

        if (checkRollno)
        {
            tvMarks.setText("");
            rollno = Integer.parseInt(etSearchRollno.getText().toString());
            List<DataModel> searchData = dbHelper.getRollnoData(rollno);

            tvName.setText(String.valueOf(searchData.get(0).mName));
            tvStream.setText(String.valueOf(searchData.get(0).mStream));
            tvMarks.append(String.valueOf(searchData.get(0).mMarks.get(getString(R.string.english))) + "\n\n");
            tvMarks.append(String.valueOf(searchData.get(0).mMarks.get((getString(R.string.maths))) + "\n\n"));
            tvMarks.append(String.valueOf(searchData.get(0).mMarks.get(getString(R.string.hindi))) + "\n\n");
            tvMarks.append(String.valueOf(searchData.get(0).mMarks.get(getString(R.string.chemistry))) + "\n\n");
            tvMarks.append(String.valueOf(searchData.get(0).mMarks.get(getString(R.string.physics))) + "\n");

            tvTotal.setText(String.valueOf((searchData.get(0).mMarks.get(getString(R.string.english)))+
                    searchData.get(0).mMarks.get((getString(R.string.maths)))+
                    searchData.get(0).mMarks.get(getString(R.string.hindi)))+
                    searchData.get(0).mMarks.get(getString(R.string.chemistry))+
                    searchData.get(0).mMarks.get(getString(R.string.physics)));
        }
        else
            Toast.makeText(SearchStudent.this, R.string.rollno_exists_toast, Toast.LENGTH_SHORT).show();
    }
}
