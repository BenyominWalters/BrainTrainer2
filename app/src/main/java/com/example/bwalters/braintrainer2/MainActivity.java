package com.example.bwalters.braintrainer2;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    TextView resultTextView;
    TextView sumTextView;
    TextView pointsTextView;
    TextView timerTextView;

    ArrayList<Integer> answers = new ArrayList<>();
    int correctLocation;
    int score = 0;
    int numberOfQuestions = 0;

    public void hideAnswerButtons() {

        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);

    }

    public void showAnswerButtons() {

        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

    }

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        showAnswerButtons();

        generateQuestion();

        new CountDownTimer(30100, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                hideAnswerButtons();

            }
        }.start();


    }

    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        answers.clear();

        correctLocation = rand.nextInt(4);

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

            if (i == correctLocation) {

                answers.add(a + b);

            }  else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    answers.add(rand.nextInt(41));

                }

                answers.add(incorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer (View view) {

       if (view.getTag().toString().equals(Integer.toString(correctLocation))) {

           score++;
           resultTextView.setText("Correct!");

       } else {

           resultTextView.setText("Wrong!");
       }

       numberOfQuestions++;
       pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
       generateQuestion();

    }

    public void start (View view) {

        startButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.correctTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

    }

}
