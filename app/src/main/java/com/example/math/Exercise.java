package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Exercise extends AppCompatActivity {
    private TextView pl, pr, action, ans;
    private TextView answers[];
    private int level, questionNumber;
    private double correctAnswer;
    private String actions[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Bundle extras = getIntent().getExtras();
        level = extras.getInt(getString(R.string.level), 1);

        questionNumber = 0;
        actions = new String[] { "+", "-", "*", "/"};
        answers = new TextView[4];
        pl = findViewById(R.id.phraseL);
        pr = findViewById(R.id.phraseR);
        action = findViewById(R.id.action);
        ans = findViewById(R.id.ans);
        answers[0] = findViewById(R.id.ans1TV);
        answers[1] = findViewById(R.id.ans2TV);
        answers[2] = findViewById(R.id.ans3TV);
        answers[3] = findViewById(R.id.ans4TV);
        loadBoard();

    }

    private void loadBoard(){
        Random rand = new Random();
        int actionRandIndex = rand.nextInt(level);
        double plVal = rand.nextInt(level+2);
        double prVal = rand.nextInt(level+2);

        if((plVal < prVal) && (actionRandIndex == 1 || actionRandIndex == 3) ){
            plVal = plVal + prVal;
            prVal = plVal - prVal;
            plVal = plVal - prVal;
        }
        correctAnswer = getCorrctAnswer(plVal, prVal, actions[actionRandIndex]);
        int indexCorrectAnswer = rand.nextInt(4);
        pl.setText(String.valueOf((int)plVal));
        action.setText(actions[actionRandIndex]);
        pr.setText(String.valueOf((int)prVal));

        setAnswers(indexCorrectAnswer);

    }

    private void setAnswers(int indexCorrectAnswer){
        Set<Double> answersSet = new HashSet();
        for (int i = 0; i < answers.length; i++) {
            Random rand = new Random();
            double val = i == indexCorrectAnswer ? correctAnswer : rand.nextInt(level+7);
            if(answersSet.contains(val)){
                i--;
                continue;
            }
            answersSet.add(val);
            answers[i].setText(String.valueOf( (int) val));
        }
    }

    private double getCorrctAnswer(double plVal, double prVal, String action) {
        switch(action){
            case "+":
                return plVal + prVal;
            case "-":
                return plVal - prVal;
            case "*":
                return plVal * prVal;
            case "/":
                return plVal + prVal;
            default:
                return plVal + prVal;
        }
    }

    boolean isCorrect(double selectedAnswer){
        return selectedAnswer == correctAnswer;
    }
}