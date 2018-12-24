package com.benko.crystalize;

import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

//@TargetApi(21)
public class Questions extends AppCompatActivity {

    private boolean done;
    private int questionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        String[] questions = getResources().getStringArray(R.array.Questions);
        TextView t = (TextView) findViewById(R.id.question);
        t.setText(questions[questionNo]);
        findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
        findViewById(R.id.correctOrNot).setVisibility(View.INVISIBLE);
        findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);

    }

    public void onAnswerClick(View view) {
        if(!done) {
            String answer = ((EditText)findViewById(R.id.answer)).getText().toString();
            String[] answers = getResources().getStringArray(R.array.Answers);
            answer = answer.toLowerCase();
            String correctAnswer = answers[questionNo];

            if(answer.equals(correctAnswer)) {
                TextView t = (TextView) findViewById(R.id.correctOrNot);
                t.setText("Correct!");
                //MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.right);
                //mp.start(); For music playing
                ImageView i = (ImageView) findViewById(R.id.tickcross);
                i.setImageDrawable(getDrawable(R.drawable.weirdtick));
                answerSubmitted();
            } else {
                TextView t = (TextView) findViewById(R.id.correctOrNot);
                t.setText("Correct Answer: " + correctAnswer);
                //MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                //mp.start(); For music playing
                ImageView i = (ImageView) findViewById(R.id.tickcross);
                i.setImageDrawable(getDrawable(R.drawable.weirdcross));
                answerSubmitted();
            }
            done = true;
        }
    }
    public void onHintClick(View view) {
        String[] hints = getResources().getStringArray(R.array.Hints);
        Toast toasty = Toast.makeText(getApplicationContext(), hints[questionNo], Toast.LENGTH_SHORT);
        toasty.show();
    }
    public void answerSubmitted() {
        findViewById(R.id.tickcross).setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 2000, 0);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                findViewById(R.id.correctOrNot).setVisibility(View.INVISIBLE);
                findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.correctOrNot).setVisibility(View.VISIBLE);
                findViewById(R.id.nextButton).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        findViewById(R.id.tickcross).startAnimation(animation);
    }
    public void onNextClick(View view) {
        if(done) {
            String[] questions = getResources().getStringArray(R.array.Questions);
            if(questionNo < questions.length - 1) {
                questionNo++;
                TextView t = (TextView) findViewById(R.id.question);
                t.setText(questions[questionNo]);

                findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
                findViewById(R.id.correctOrNot).setVisibility(View.INVISIBLE);
                findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);
                EditText et = (EditText) findViewById(R.id.answer);
                et.setText("");

                done = false;
            }
        }
    }
}
