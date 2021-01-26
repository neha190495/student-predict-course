package com.ibso.student_predict_course;

public class QuestionLibrary {

    private String mQuestions [] = {
            "Select a suitable figure from the four alternatives that would complete the figure matrix",
            "Select a suitable figure from the four alternatives that would complete the figure matrix",
            "Choose a right figure from the set of answer figures which would replace the question mark (?)"
    };

    private String mQuestionImage [] = {"question1","question2","question3"};

    private String mChoices [][] = {
            {"1", "2", "3", "4"},
            {"1", "2", "3", "4"},
            {"A", "B", "C", "D"}
    };

    private String mCorrectAnswers [] = {"3","4","C"};

    public String getQuestion(int a){
        String question = mQuestions[a];
        return question;
    }

    public String getQuestionImage(int a){
        String questionImage = mQuestionImage[a];
        return questionImage;
    }

    public String getChoice1(int a){
        String choice1 = mChoices[a][0];
        return choice1;
    }
    public String getChoice2(int a){
        String choice2 = mChoices[a][1];
        return choice2;
    }
    public String getChoice3(int a){
        String choice3 = mChoices[a][2];
        return choice3;
    }
    public String getChoice4(int a){
        String choice4 = mChoices[a][3];
        return choice4;
    }

    public String getCorrectAnswer(int a){
        String correctAnswer = mCorrectAnswers[a];
        return correctAnswer;
    }

    public int getTotalNumberOfQuestion(){
        int length =  mQuestions.length;
        return length;
    }

}
