package com.example.kenix.playquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    TextView pointTextView;
    TextView avgTextView;
    TextView timeTimeView;
    TextView noofcorrectTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        pointTextView=(TextView)findViewById(R.id.textView_point);
        avgTextView=(TextView)findViewById(R.id.textView2_avg_q);
        timeTimeView=(TextView)findViewById(R.id.textView1_time);
        Intent intent=getIntent();
        int score= intent.getIntExtra("points",0);
        pointTextView.setText(Integer.toString(score));
        long time=intent.getLongExtra("time",0);
        timeTimeView.setText(Long.toString(time/1000));
        noofcorrectTextView=(TextView)findViewById(R.id.textView3_numberofcorrect);
        int correctAnswer=intent.getIntExtra("correct",0);
        noofcorrectTextView.setText(Integer.toString(correctAnswer));
        double avg=((double)time/(1000*correctAnswer));
        avgTextView.setText(Double.toString(avg));

    }
}
