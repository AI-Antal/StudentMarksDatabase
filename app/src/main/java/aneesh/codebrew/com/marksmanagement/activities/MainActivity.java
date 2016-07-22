package aneesh.codebrew.com.marksmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import aneesh.codebrew.com.marksmanagement.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private ImageView ivBackground;
    private TextView tvEnterData, tvStudentDetails, tvSearchStudent;
    static int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        onClickListeners();
    }

    private void onClickListeners()
    {
        tvEnterData.setOnClickListener(this);
        tvStudentDetails.setOnClickListener(this);
        tvSearchStudent.setOnClickListener(this);
        ivBackground.setOnClickListener(this);
    }

    private void initializeViews()
    {
        tvEnterData = (TextView) findViewById(R.id.tvEnterData);
        tvStudentDetails = (TextView) findViewById(R.id.tvStudentDetails);
        tvSearchStudent = (TextView) findViewById(R.id.tvSearchStudent);
        ivBackground = (ImageView) findViewById(R.id.ivbackground);
        Glide.with(getApplicationContext()).load(R.drawable.photodune).into(ivBackground);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tvEnterData:
                Intent enterData = new Intent(this, EnterData.class);
                startActivity(enterData);
                break;

            case R.id.tvStudentDetails:
                Intent viewEntries = new Intent(this, ViewEntries.class);
                startActivity(viewEntries);
                break;

            case R.id.tvSearchStudent:
                Intent searchStudent = new Intent(this, SearchStudent.class);
                startActivity(searchStudent);
                break;

            case R.id.ivbackground:

                switch (count)
                {
                    case 0:
                    {
                        Glide.with(getApplicationContext()).load(R.drawable.photodune).into(ivBackground);
                        count++;
                        break;
                    }
                    case 1:
                    {
                        Glide.with(getApplicationContext()).load(R.drawable.nopememeface).into(ivBackground);
                        count++;
                        break;
                    }
                    case 2:
                    {
                        Glide.with(getApplicationContext()).load(R.drawable.guy).into(ivBackground);
                        count = 0;
                        break;
                    }
                }
                break;
        }
    }
}
