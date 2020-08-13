package com.example.quizapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quizapp.Model.Questions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    TextView question;
    FloatingActionButton fab;
    Button btnshare, btnnext;
    LinearLayout option_container;
    private int count = 0;
    Toolbar toolbar;
    List<Questions> questionsList;
    int position = 0;
    private int score=0;
    TextView noIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        toolbar = findViewById(R.id.toolbar_ques);
        setSupportActionBar(toolbar);
        question = findViewById(R.id.question_text);
        fab = findViewById(R.id.fab_bookmark);
        btnnext = findViewById(R.id.btnNext);
        btnshare = findViewById(R.id.btnshare);
        noIndicator=findViewById(R.id.txt_timer);
        option_container = findViewById(R.id.options_container);
        questionsList = new ArrayList<>();
        questionsList.add(new Questions("question1", "a,", "b", "c", "d", "a"));
        questionsList.add(new Questions("question2", "a,", "b", "c", "d", "b"));
        questionsList.add(new Questions("question3", "a,", "b", "c", "d", "c"));
        questionsList.add(new Questions("question4", "a,", "b", "c", "d", "d"));
        questionsList.add(new Questions("question5", "a,", "b", "c", "d", "a"));
        questionsList.add(new Questions("question6", "a,", "b", "c", "d", "b"));
        questionsList.add(new Questions("question7", "a,", "b", "c", "d", "c"));
        questionsList.add(new Questions("question8", "a,", "b", "c", "d", "d"));
        questionsList.add(new Questions("question9", "a,", "b", "c", "d", "a"));
        questionsList.add(new Questions("question10", "a,", "b", "c", "d", "b"));
        for (int i = 0; i < 4; i++) {

            option_container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);
                }
            });
        }

        playAnim(question,0,questionsList.get(position).getQuestion());
        btnnext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                btnnext.setEnabled(false);
                btnnext.setAlpha(0.7f);
                enableOption(true);
                if (position==questionsList.size())
                {
                    return;
                }
                position++;
                count = 0;
                playAnim(question, 0, questionsList.get(position).getQuestion());
            }
        });

    }

    private void playAnim(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    ((TextView) view).setText(data);
                    noIndicator.setText(position+1+"/"+questionsList.size());
                }catch (Exception e)
                {
                    ((Button) view).setText(data);

                }
                view.setTag(data);
                if (value == 0) {
                    playAnim(view, 1, data);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && count<4) {
                    String options = "";
                    if (count == 0) {
                        options = questionsList.get(position).getOptionA();
                    } else if (count == 1) {
                        options = questionsList.get(position).getOptionB();
                    } else if (count == 2) {
                        options = questionsList.get(position).getOptionC();
                    } else if (count == 3) {
                        options = questionsList.get(position).getOptionD();
                    }

                    playAnim(option_container.getChildAt(count), 0, options);
                    count++;
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOptions) {
        enableOption(false);
        btnnext.setEnabled(true);
        btnnext.setAlpha(1);
        if (selectedOptions.getText().toString().equals(questionsList.get(position).getCorrectAnswer())) {
            selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            score++;
        } else {
            selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
        }
        Button correctOption=(Button) option_container.findViewWithTag(questionsList.get(position).getCorrectAnswer());
//        correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {

            option_container.getChildAt(i).setEnabled(enable);
            if (enable)
            {
                option_container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C1BEBE")));
            }

        }
    }
}