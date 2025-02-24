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
    Button resetButton;
    Question randomQuestion;
    int score = 0;
    List<Question> questionList = new ArrayList<>();

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

        questionText = findViewById(R.id.questionText);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        resetButton = findViewById(R.id.resetButton);

        Question question1 = new Question();
        question1.question = "Чи був Одін Скандинавським богом?";
        question1.answer = true;

        Question question2 = new Question();
        question2.question = "Чи був Посейдон Єгипетським богом?";
        question2.answer = false;

        Question question3 = new Question();
        question3.question = "Чи був Ра Грецьким богом?";
        question3.answer = false;

        Question question4 = new Question();
        question4.question = "Чи правда, що за скандинавськими міфами, перша людина вийшла з дерева?";
        question4.answer = true;

        Question question5 = new Question();
        question5.question = "Чи правда, що Прометей віддав людям вогонь, за що був прикований до гори?";
        question5.answer = true;



        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        questionList.add(question4);
        questionList.add(question5);

        randomQuestion = RandomQuestionChoose(questionList);

        ShowQuestion(randomQuestion);




        trueButton.setOnClickListener(v -> {
            boolean answer = true;
            HideButtons();
            CheckAnswer(answer);
        });

        falseButton.setOnClickListener(v -> {
            boolean answer = false;
            HideButtons();
            CheckAnswer(answer);
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionList.remove(randomQuestion);
                randomQuestion = RandomQuestionChoose(questionList);

                ShowQuestion(randomQuestion);

                if(!questionList.isEmpty()) {

                    resetButton.setVisibility(View.GONE);

                    trueButton.setVisibility(View.VISIBLE);
                    falseButton.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public Question RandomQuestionChoose(List<Question> questionList)
    {
        if(questionList.isEmpty())
        {
            questionText.setText("Ваші бали: "+ score);
            trueButton.setVisibility(View.GONE);
            falseButton.setVisibility(View.GONE);
            resetButton.setVisibility(View.GONE);
            return null;
        }

        Random random = new Random();
        randomQuestion = questionList.get(random.nextInt(questionList.size()));
        return randomQuestion;

    }
    public void ShowQuestion(Question question)
    {

        if (question != null)
        {
            questionText.setText(question.question);
        }



    }
    public void HideButtons()
    {
        trueButton.setVisibility(View.GONE);
        falseButton.setVisibility(View.GONE);
        resetButton.setVisibility(View.VISIBLE);
    }
    public void CheckAnswer(boolean answer)
    {
        if(randomQuestion.answer == answer) { questionText.setText("Молодець"); score++;}
        else questionText.setText("Відповідь неправильна");
    }

}