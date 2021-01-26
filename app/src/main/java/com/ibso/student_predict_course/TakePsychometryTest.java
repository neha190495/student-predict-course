package com.ibso.student_predict_course;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TakePsychometryTest extends Fragment {

    private onTakeTestFragmentButtonSelected listener;

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
    private TextView mScoreView;
    private TextView mQuestionView;
    private ImageView mImageView;
    private RadioButton mRadioButton1,mRadioButton2,mRadioButton3,mRadioButton4;
    private Button mConfirmButton;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private int mtotalNumberOfQuestions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_takepsychometrytest, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScoreView = view.findViewById(R.id.text_view_question_count);
        mQuestionView = view.findViewById(R.id.text_view_question);
        mImageView = view.findViewById(R.id.image_view_question);
        mRadioButton1 = view.findViewById(R.id.radio_button1);
        mRadioButton2 = view.findViewById(R.id.radio_button2);
        mRadioButton3 = view.findViewById(R.id.radio_button3);
        mRadioButton4 = view.findViewById(R.id.radio_button4);
        mtotalNumberOfQuestions = mQuestionLibrary.getTotalNumberOfQuestion();
        mScoreView.setText((mQuestionNumber+1)+"/"+mtotalNumberOfQuestions);

        Button confirmButton = view.findViewById(R.id.button_confirm_next);
        //confirmButton.setOnClickListener({onConfirmButtonClick(view)});
        updateQuestion();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PsychometryTestFragment.onFragmentButtonSelected){
            listener = (onTakeTestFragmentButtonSelected) context;
        }else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    public interface onTakeTestFragmentButtonSelected{
        public void onTestConfirmButtonClick();
    }

    private void updateQuestion(){

        mScoreView.setText("Question: " + (mQuestionNumber+1) +"/"+mtotalNumberOfQuestions);
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        String questionImage = mQuestionLibrary.getQuestionImage(mQuestionNumber);
        int drawableId = this.getResources().getIdentifier(questionImage, "drawable", getContext().getPackageName());
        mImageView.setImageResource(drawableId);
        mRadioButton1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mRadioButton2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mRadioButton3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
        mRadioButton4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;

    }

    private void updateScore(){

    }

    public void onConfirmButtonClick(View view){

    }
}
