package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.bra_brasilia_capital, true),
            new Question(R.string.bra_brasilia_airplane, true),
            new Question(R.string.bra_largest_state, true),
            new Question(R.string.bra_corinthians, true),
            new Question(R.string.bra_gender_change, false),
            new Question(R.string.bra_voting, false),
            new Question(R.string.bra_tanning_beds, true),
            new Question(R.string.bra_world_cup_spendings, true),
            new Question(R.string.bra_japanese, true),
            new Question(R.string.bra_cariocas, true),
            new Question(R.string.bra_rio_de_janeiro_pronounciation, true),
            new Question(R.string.bra_portuguese, false),
            new Question(R.string.bra_population_size, false),
            new Question(R.string.bra_high_school, false),
            new Question(R.string.bra_nuts, false),
            new Question(R.string.bra_coffee, true),
            new Question(R.string.bra_nao_me_toque, true),
            new Question(R.string.bra_airports, true),
            new Question(R.string.bra_iphones, false),
            new Question(R.string.bra_rio_de_janeiro_capital, false),
            new Question(R.string.bra_touch_other_countries, true),
            new Question(R.string.bra_tallest_mountain, false),
            new Question(R.string.bra_largest_country, false),
            new Question(R.string.bra_catholic, true),
            new Question(R.string.bra_soccer_team, false),
            new Question(R.string.bra_longest_beach, true),
            new Question(R.string.bra_soccer_not_popular, false),
            new Question(R.string.bra_fifty_percent_spanish, false),
    };

//    private Question[] mQuestionBank = new Question[] {
//        new Question(R.string.question_australia, true),
//        new Question(R.string.question_oceans, true),
//        new Question(R.string.question_mideast, false),
//        new Question(R.string.question_africa, false),
//        new Question(R.string.question_americas, true),
//        new Question(R.string.question_asia, true)
//    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mBackButton = (Button) findViewById((R.id.back_button));
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0)
                    mCurrentIndex = mQuestionBank.length-1;
                else
                    mCurrentIndex = Math.abs(mCurrentIndex - 1) % mQuestionBank.length;

                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId;

        if (userPressedTrue == answerIsTrue)
            messageResId = R.string.correct_toast;
        else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
