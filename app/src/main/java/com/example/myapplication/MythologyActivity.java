package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MythologyActivity extends AppCompatActivity {

    TextView questionText;
    Button trueButton;
    Button falseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mythology);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Question question1 = new Question();
        question1.question = "Чи був Одін Скандинавським богом?";
        question1.answer = true;

        Question question2 = new Question();
        question2.question = "Чи був Посейдон Єгипетським богом?";
        question2.answer = false;

        List<Question> questionList = new ArrayList<>();
        questionList.add(question1);
        questionList.add(question2);

        Random random = new Random();
        Question randomQuestion = questionList.get(random.nextInt(questionList.size()));

        questionText = findViewById(R.id.questionText);
        questionText.setText(randomQuestion.question);

        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answer = true;
                HideButtons();
                CheckAnswer(answer, randomQuestion);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answer = false;
                HideButtons();
                CheckAnswer(answer, randomQuestion);
            }
        });

    }

    public void HideButtons()
    {
        trueButton.setVisibility(View.GONE);
        falseButton.setVisibility(View.GONE);
    }
    public void CheckAnswer(boolean answer, Question question)
    {
        if(question.answer == answer) questionText.setText("Молодець");
        else questionText.setText("Відповідь неправильна");
    }
}


