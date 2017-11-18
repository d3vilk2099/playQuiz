package com.example.kenix.playquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    TextView textView;
    TextView textView1;
    TextView textView2;
    ImageView imageView;
    int position = 0;
    int score=0;
    String[] questions;
    String[] options;
    String[] answers;
    String[] images;


    Date startTime;
    Date endTime;
    long time=0;
    int correctAnswer=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button_A);
        button2 = (Button) findViewById(R.id.button2_B);
        button3 = (Button) findViewById(R.id.button3_C);
        button4 = (Button) findViewById(R.id.button4_D);
        button5 = (Button) findViewById(R.id.button5_help);
        textView = (TextView) findViewById(R.id.textView_numquestion);
        textView1 = (TextView) findViewById(R.id.textView2_question);
        textView2 = (TextView) findViewById(R.id.textView3_correctanswer);
        imageView = (ImageView) findViewById(R.id.imageView_angel);

        Resources res = getResources();


        questions = res.getStringArray(R.array.question);
        options = res.getStringArray(R.array.option);
        answers = res.getStringArray(R.array.answer);
        images = res.getStringArray(R.array.imagename);
        int resId = getResources().getIdentifier(images[0], "drawable", getPackageName());
        imageView.setImageResource(resId);

        showNewQuestion();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyAnswer(button.getText().toString());




            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyAnswer(button2.getText().toString());



            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyAnswer(button3.getText().toString());



            }
        });

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                verifyAnswer(button4.getText().toString());

            }
            });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }


        });


    }

    public void verifyAnswer(String answer) {

        if (answer.equals(answers[position])){
            endTime=new Date();
            correctAnswer++;

            long difference=endTime.getTime()-startTime.getTime();
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.cheer);
            mediaPlayer.start();
            time=time+difference;
            if (difference>5000){
                score++;
            }
            else{
                int bonus=6-(int) (difference/1000);

                score=score+ bonus;
            }
            textView2.setText("Answer is correct");



        } else {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
            mediaPlayer.start();

            textView2.setText("Answer is wrong");


        }
        if(position<questions.length-1) {
            position++;
            showNewQuestion();

        }
            else{
                Intent intent=new Intent(MainActivity.this,SummaryActivity.class);
                intent.putExtra("points",score);
                intent.putExtra("time",time);
                intent.putExtra("correct",correctAnswer);
            startActivity(intent);
                /*Toast.makeText(getApplicationContext(),"The end ! Your score is "+score,Toast.LENGTH_LONG).show();
            position=0;
            score=0;
            showNewQuestion();*/
            }
        }




    public void showNewQuestion() {


        startTime= new Date();
        int resId1 = getResources().getIdentifier(images[position], "drawable", getPackageName());
        imageView.setImageResource(resId1);
        textView1.setText(questions[position]);
        button.setText(options[4*position]);
        button2.setText(options[4*position + 1]);
        button3.setText(options[4*position + 2]);
        button4.setText(options[4*position + 3]);
    }
}