package aneesh.codebrew.com.marksmanagement.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import aneesh.codebrew.com.marksmanagement.model.DataModel;
import aneesh.codebrew.com.marksmanagement.database.DbHelper;
import aneesh.codebrew.com.marksmanagement.adapters.EnterDataAdapter;
import aneesh.codebrew.com.marksmanagement.R;

public class EnterData extends AppCompatActivity implements View.OnClickListener
{
    Spinner spStream, spSubject;
    private TextView tvSubmit, tvClear, tvAdd;
    private EditText etName, etRollNo, etMarks;
    ArrayAdapter<String> adapter, adapter2;
    private List<String> streamItems = new ArrayList<>();
    private List<String> subjectItems = new ArrayList<>();
    private List<String> subjectList = new ArrayList<>();
    private List<String> marksList = new ArrayList<>();
    private List<String> totalItems = new ArrayList<>();
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    EnterDataAdapter mAdapter;
    private HashMap<String, Integer> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);
        initialiseViews();
        onClickListeners();
    }

    private void onClickListeners()
    {
        tvSubmit.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvClear.setOnClickListener(this);
    }

    public void initialiseViews()
    {
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        spStream = (Spinner) findViewById(R.id.spStream);
        spSubject = (Spinner) findViewById(R.id.spSubject);
        tvClear = (TextView) findViewById(R.id.tvClear);
        tvAdd = (TextView) findViewById(R.id.tvAdd);
        etName = (EditText) findViewById(R.id.etName);
        etRollNo = (EditText) findViewById(R.id.etRollNo);
        etMarks = (EditText) findViewById(R.id.etMarks);

        //setting static array to list.
        streamItems.addAll(Arrays.asList(getResources().getStringArray(R.array.streams)));
        subjectItems.addAll(Arrays.asList(getResources().getStringArray(R.array.subjects)));
        totalItems.addAll(Arrays.asList(getResources().getStringArray(R.array.subjects)));

        //adding list to spinner + custom layout.
        adapter = new ArrayAdapter<>(this, R.layout.spinner_item, streamItems);
        adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, subjectItems);

        //custom drop down layout for spinner.
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);

        //setting adapter data to spinner.
        spStream.setAdapter(adapter);
        spSubject.setAdapter(adapter2);

        mRecyclerView = (RecyclerView) findViewById(R.id.lvTable);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tvSubmit:
                //verifying Name editText
                if (etName.getText().toString().trim().isEmpty())
                {
                    etName.setError(getString(R.string.etname_error));
                }
                //verifying Rollno editText
                else if (etRollNo.getText().toString().trim().isEmpty())
                {
                    etRollNo.setError(getString(R.string.etrollno_error));
                }

                //verifying Stream editText
                else if (spStream.getSelectedItemPosition() == 0)
                {
                    TextView errorText = (TextView) spStream.getSelectedView();
                    errorText.setError("");
                }
                //verifying Marks editText
                else if (etMarks.getText().toString().trim().isEmpty())
                {
                    etMarks.setError(getString(R.string.etMarks_error));
                }
                //verifying all Subjects selected editText
                else if (!(subjectList.size() == totalItems.size() - 1))
                {
                    TextView errorText = (TextView) spSubject.getSelectedView();
                    errorText.setError("");
                    Toast.makeText(EnterData.this, R.string.subjectlist_error, Toast.LENGTH_SHORT).show();
                } else
                {
                    int rollno = Integer.parseInt(etRollNo.getText().toString());

                    DbHelper dbHelper = new DbHelper(getApplicationContext());
                    boolean checkRollno = dbHelper.checkRollno(rollno);

                    //if roll no does not exists
                    if (!checkRollno)
                    {
                        DataModel dataModel = new DataModel();
                        dataModel.mName = etName.getText().toString();
                        dataModel.mRollno = Integer.parseInt(etRollNo.getText().toString());
                        dataModel.mStream = spStream.getSelectedItem().toString();
                        dataModel.mMarks = map;
                        dbHelper.insertData(dataModel);
                        clearAll();
                    }
                    else
                    {
                        Toast.makeText(EnterData.this, R.string.rollno_exists, Toast.LENGTH_SHORT).show();
                        etRollNo.setError(String.valueOf(R.string.rollno_exists));
                    }
                }
                break;

            case R.id.tvClear:
                if (subjectList.size() < 1)
                {
                    clearFields();
                } else
                {
                    clearAll();
                }
                break;

            case R.id.tvAdd:

                //Checking is subject/marks are not selected.
                if (spSubject.getSelectedItemPosition() == 0 || etMarks.getText().toString().trim().isEmpty())
                {
                    if (spSubject.getSelectedItemPosition() == 0)
                    {
                        //checking if all subjects are selected.
                        if ((subjectList.size() == totalItems.size() - 1))
                        {
                            Toast.makeText(EnterData.this, R.string.subjectsSelected_toast, Toast.LENGTH_SHORT).show();
                        } else
                        {
                            TextView errorText = (TextView) spSubject.getSelectedView();
                            errorText.setError("");
                        }
                    } else
                    {
                        etMarks.setError(getString(R.string.no_marks_error));
                    }
                } else
                {
                    int marks = Integer.parseInt(etMarks.getText().toString());
                    if ((marks) <= 100)
                    {
                        subjectList.add(spSubject.getSelectedItem().toString());
                        marksList.add(etMarks.getText().toString());

                        mAdapter = new EnterDataAdapter(getApplication(), subjectList, marksList);
                        mRecyclerView.setAdapter(mAdapter);

                        map.put(spSubject.getSelectedItem().toString(), Integer.valueOf(etMarks.getText().toString()));

                        //Removing selected items from spinner.
                        subjectItems.remove(spSubject.getSelectedItemPosition());
                        adapter2.notifyDataSetChanged();
                    } else
                        etMarks.setError(getString(R.string.etMarks_invalid_input_error));
                }
                break;
        }
    }

    private void clearFields()
    {
        subjectList.clear();
        marksList.clear();
        etName.setText("");
        etRollNo.setText("");
        etMarks.setText("");
    }

    private void clearAll()
    {
        clearFields();
        map.clear();
        subjectItems.clear();
        subjectItems.addAll(Arrays.asList(getResources().getStringArray(R.array.subjects)));
        mAdapter.notifyDataSetChanged();
    }
}
