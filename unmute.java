package com.unmuteapp4gmail.unmute;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Arithmatic extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.learn.MESSAGE";
    private ArithmaticQuestionLibrary mQuestionLibrary = new ArithmaticQuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    String userName, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmatic);

        Calendar calendar = Calendar.getInstance();

        int thisYear = calendar.get(Calendar.YEAR);
        year = Integer.toString(thisYear);

        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);

        int thisMonth = calendar.get(Calendar.MONTH);

        if(thisMonth == 0){

            month = "January";
        }

        else if(thisMonth == 1){

            month = "February";
        }

        else if(thisMonth == 2){

            month = "March";
        }

        else if(thisMonth == 3){

            month = "April";
        }

        else if(thisMonth == 4){

            month = "May";
        }

        else if(thisMonth == 5){

            month = "June";
        }

        else if(thisMonth == 6){

            month = "July";
        }

        else if(thisMonth == 7){

            month = "August";
        }

        else if(thisMonth == 8){

            month = "September";
        }

        else if(thisMonth == 9){

            month = "October";
        }

        else if(thisMonth == 10){

            month = "November";
        }

        else if(thisMonth == 11){

            month = "December";
        }


        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);

        updateQuestion();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here
                if(mQuestionNumber>10){
                    Intent intent = new Intent(Arithmatic.this, EndArithmatic.class);
                    String message = "Your final Score = "+Integer.toString(mScore)+"/"+Integer.toString(mQuestionNumber-1);
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);

                    auth = FirebaseAuth.getInstance();
                    userName = auth.getCurrentUser().getEmail();
                    String userName1 = userName.replaceAll("@unmute.com","");

                    Students obj = new Students();
                    obj.setArithmetic(Integer.toString(mScore));

                    databaseReference.child(userName1).child(year).child(month).child("arithmetic").setValue(Integer.toString(mScore));

                }

                else {

                    if (mButtonChoice1.getText() == mAnswer){
                        mScore = mScore + 1;
                        updateScore(mScore);


                        updateQuestion();


                        //This line of code is optiona
                        //Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                    }else {
                        // Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();

                        updateQuestion();

                    }}
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if(mQuestionNumber>10){
                    Intent intent = new Intent(Arithmatic.this, EndArithmatic.class);
                    String message = "Your final Score = "+Integer.toString(mScore)+"/"+Integer.toString(mQuestionNumber-1);
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);

                    auth = FirebaseAuth.getInstance();
                    userName = auth.getCurrentUser().getEmail();
                    String userName1 = userName.replaceAll("@unmute.com","");

                    Students obj = new Students();
                    obj.setArithmetic(Integer.toString(mScore));

                    databaseReference.child(userName1).child(year).child(month).child("arithmetic").setValue(Integer.toString(mScore));



                }

                else {

                    if (mButtonChoice2.getText() == mAnswer){
                        mScore = mScore + 1;
                        updateScore(mScore);



                        updateQuestion();




                        //This line of code is optiona
                        // Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                    }else {
                        //  Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();

                        updateQuestion();

                    }}


            }
        });

        //End of Button Listener for Button2

//Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if(mQuestionNumber>10){
                    Intent intent = new Intent(Arithmatic.this, EndArithmatic.class);
                    String message = "Your final Score = "+Integer.toString(mScore)+"/"+Integer.toString(mQuestionNumber-1);
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);

                    auth = FirebaseAuth.getInstance();
                    userName = auth.getCurrentUser().getEmail();
                    String userName1 = userName.replaceAll("@unmute.com","");

                    Students obj = new Students();
                    obj.setArithmetic(Integer.toString(mScore));

                    databaseReference.child(userName1).child(year).child(month).child("arithmetic").setValue(Integer.toString(mScore));




                }

                else {

                    if (mButtonChoice3.getText() == mAnswer){
                        mScore = mScore + 1;
                        updateScore(mScore);



                        updateQuestion();


                        //This line of code is optiona
                        //Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                    }else {
                        //Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();

                        updateQuestion();

                    }}
            }
        });

        //End of Button Listener for Button3

    }

    private void updateQuestion(){

        if(mQuestionNumber<10) {
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        }
        mQuestionNumber++;



    }


    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }

    public void exitTest(View view){
        Intent intent = new Intent(this,Tests.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Arithmatic.this,Tests.class);
        startActivity(intent);

    }

}

Arithmetic Question Library
package com.unmuteapp4gmail.unmute;


public class ArithmaticQuestionLibrary {

    private String mQuestions [] = {
            "(1) 2.75 + .003 + .158 = ?",
            "(2) 7.86 × 4.6 = ?",
            "(3) 7/20 = ?",
            "(4) Which of the following is the least?",
            "(5) All of the following are ways to write 25 percent of N EXCEPT",
            "(6) Which of the following is closest to 27.8 × 9.6?",
            "(7) A soccer team played 160 games and won 65 percent of them. How many games did it win?",
            "(8) Three people who work full-time are to work together on a project, but their total time on the project is to be equivalent to that of only one person working full-time. If one of the people is budgeted for one-half of his time to the project and a second person for one-third of her time, what part of the third worker’s time should be budgeted to this project?",
            "(9) 32 is 40 percent of what number?",
            "(10) 313 – 225 = ?"


    };


    private String mChoices [][] = {
            {"4.36", "2.911", "0.436"},
            {"36.156", "36.216", "351.56"},
            {"0.035", "0.858", "0.35"},
            {"0.105", "0.501", "0.015"},
            {"0.25N", "1/4N", "25N"},

            {"280", "300", "2800"},
            {"94", "104", "114"},
            {"13.3%", "35.2%", "16.7%"},
            {"12.8", "128", "80"},
            {"112", "115", "88"}
    };



    private String mCorrectAnswers[] = {"2.911", "36.156", "0.35", "0.015", "25N", "280", "104", "16.7%", "80", "88"};




    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}

Arithmetic XML code
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:orientation="vertical"
    tools:context="com.unmuteapp4gmail.unmute.Arithmatic">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="8dp"
        android:layout_marginBottom="0dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Score"
            android:textSize="15sp"
            android:layout_alignParentLeft="true"
            android:id="@+id/score_text"/>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/score"
            android:layout_alignParentRight="true"
            android:text="0"
            android:textSize="15sp"/>

    </RelativeLayout>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="85dp"
        android:text=""
        android:textSize="15sp"
        android:padding="8dp"
        android:layout_marginBottom="0dp"
        android:id="@+id/question"
        android:layout_weight="0.2" />


    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text=""
        android:background="#0091EA"
        android:textColor="#fff"
        android:padding="8dp"
        android:layout_marginBottom="8dp"
        android:id="@+id/choice1"/>

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text=""
        android:background="#0091EA"
        android:textColor="#fff"
        android:padding="8dp"
        android:layout_marginBottom="8dp"
        android:id="@+id/choice2"/>

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text=""
        android:background="#0091EA"
        android:textColor="#fff"
        android:padding="8dp"
        android:layout_marginBottom="24dp"
        android:id="@+id/choice3"/>

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Quit"
        android:background="#B71C1C"
        android:textColor="#fff"
        android:padding="8dp"
        android:layout_marginBottom="8dp"
        android:id="@+id/quit"
        android:onClick="exitTest"
        />


</LinearLayout>

Check Attendance Java code
package com.unmuteapp4gmail.unmute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckAttendance extends AppCompatActivity {

    EditText name, umonth, uyear;
    Button getAttendance;
    private FirebaseAuth auth;
    TextView name1, month1, year1, nwd, ndp, nda, ap;
    String userName, userMonth, userYear, curruser;
    int pre, abs, total;
    float perc, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_attendance);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReferenceFromUrl("https://unmute-8d203.firebaseio.com/Attendance");

        name = (EditText) findViewById(R.id.editText);
        umonth = (EditText) findViewById(R.id.editText2);
        uyear = (EditText) findViewById(R.id.editTextYear);

        getAttendance = (Button) findViewById(R.id.buttonFind);

        name1 = (TextView) findViewById(R.id.textViewName);
        month1 = (TextView) findViewById(R.id.textViewMonth);
        year1 = (TextView) findViewById(R.id.textViewYear);
        nwd = (TextView) findViewById(R.id.textView4);
        ndp = (TextView) findViewById(R.id.textView6);
        nda = (TextView) findViewById(R.id.textView10);
        ap = (TextView) findViewById(R.id.textView12);


        getAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = name.getText().toString().trim();
                userMonth = umonth.getText().toString().trim();
                userYear = uyear.getText().toString().trim();


                auth = FirebaseAuth.getInstance();
                curruser = auth.getCurrentUser().getEmail();
                final String curruser1 = curruser.replaceAll("@unmute.com","");

                char firstchar;
                firstchar = curruser1.charAt(0);

                if(firstchar == 's') {

                    if(TextUtils.isEmpty(userName)){

                        Toast.makeText(CheckAttendance.this, "Please Enter the ID!", Toast.LENGTH_LONG).show();
                        name1.setText("");
                        month1.setText("");
                        year1.setText("");
                        nwd.setText("");
                        ndp.setText("");
                        nda.setText("");
                        ap.setText("");

                        return;

                    }

                    else {

                        if (!userName.equals(curruser1)) {

                            userName = curruser1;
                            Toast.makeText(CheckAttendance.this, "You can check only your own attendance", Toast.LENGTH_LONG).show();
                        }
                    }


                }

                if(firstchar == 'v') {

                    if(TextUtils.isEmpty(userName)){

                        Toast.makeText(CheckAttendance.this, "Please Enter the ID!", Toast.LENGTH_LONG).show();
                        name1.setText("");
                        month1.setText("");
                        year1.setText("");
                        nwd.setText("");
                        ndp.setText("");
                        nda.setText("");
                        ap.setText("");

                        return;

                    }

                    else {

                        char firstchar1;
                        firstchar1 = userName.charAt(0);

                        if (firstchar1 == 'v') {

                            if (!userName.equals(curruser1)) {

                                userName = curruser1;
                                Toast.makeText(CheckAttendance.this, "You cannot check attendance of other volunteers", Toast.LENGTH_LONG).show();

                            }

                        }
                    }

                }

                if(TextUtils.isEmpty(userName)){

                    Toast.makeText(CheckAttendance.this, "Please Enter the ID!", Toast.LENGTH_LONG).show();
                    name1.setText("");
                    month1.setText("");
                    year1.setText("");
                    nwd.setText("");
                    ndp.setText("");
                    nda.setText("");
                    ap.setText("");

                    return;

                }

                if(TextUtils.isEmpty(userMonth)){

                    Toast.makeText(CheckAttendance.this, "Please Enter the Month!", Toast.LENGTH_LONG).show();
                    name1.setText("");
                    month1.setText("");
                    year1.setText("");
                    nwd.setText("");
                    ndp.setText("");
                    nda.setText("");
                    ap.setText("");

                    return;

                }

                if (TextUtils.isEmpty(userYear)) {

                    Toast.makeText(CheckAttendance.this, "Please Enter the Year!", Toast.LENGTH_LONG).show();
                    name1.setText("");
                    month1.setText("");
                    year1.setText("");
                    nwd.setText("");
                    ndp.setText("");
                    nda.setText("");
                    ap.setText("");

                    return;
                }


                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if(dataSnapshot.hasChild(userName)) {

                                if(dataSnapshot.child(userName).hasChild(userYear)){

                                    if(dataSnapshot.child(userName).child(userYear).hasChild(userMonth)){

                                        ref.child(userName).child(userYear).child(userMonth).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                StuAttendance obj = dataSnapshot.getValue(StuAttendance.class);

                                                if(obj.getPresent().equals("") && !obj.getAbsent().equals("")) {

                                                    pre = 0;
                                                    abs = Integer.parseInt(obj.getAbsent());
                                                    total = pre + abs;
                                                    perc = (pre / total) * 100;
                                                }

                                                if(obj.getAbsent().equals("") && !obj.getPresent().equals("")) {

                                                    abs = 0;
                                                    pre = Integer.parseInt(obj.getPresent());
                                                    total = pre + abs;
                                                    perc = (pre / total) * 100;
                                                }

                                                if(!obj.getPresent().equals("") && !obj.getAbsent().equals("")) {
                                                    pre = Integer.parseInt(obj.getPresent());
                                                    abs = Integer.parseInt(obj.getAbsent());
                                                    total = pre + abs;
                                                    div = (float)pre/total;
                                                    perc = div * 100;
                                                }

                                                if(obj.getPresent().equals("") && obj.getAbsent().equals("")){

                                                    pre = 0;
                                                    abs = 0;
                                                    total = 0;
                                                    perc = 0;
                                                }

                                                name1.setText(userName);
                                                month1.setText(userMonth);
                                                year1.setText(userYear);
                                                nwd.setText(Integer.toString(total));
                                                ndp.setText(Integer.toString(pre));
                                                nda.setText(Integer.toString(abs));
                                                ap.setText(String.valueOf(perc));

                                                Toast.makeText(CheckAttendance.this, "Data Found!", Toast.LENGTH_SHORT).show();

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });



                                    }

                                    else{

                                        Toast.makeText(CheckAttendance.this, "Invalid Month", Toast.LENGTH_SHORT).show();
                                        name1.setText("");
                                        month1.setText("");
                                        year1.setText("");
                                        nwd.setText("");
                                        ndp.setText("");
                                        nda.setText("");
                                        ap.setText("");
                                    }

                                }

                                else{

                                    Toast.makeText(CheckAttendance.this, "Invalid Year", Toast.LENGTH_SHORT).show();
                                    name1.setText("");
                                    month1.setText("");
                                    year1.setText("");
                                    nwd.setText("");
                                    ndp.setText("");
                                    nda.setText("");
                                    ap.setText("");
                                }


                            }

                            else{

                                Toast.makeText(CheckAttendance.this, "Please Enter Valid ID!", Toast.LENGTH_SHORT).show();
                                name1.setText("");
                                month1.setText("");
                                year1.setText("");
                                nwd.setText("");
                                ndp.setText("");
                                nda.setText("");
                                ap.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



            }
        });






    }
}

Check Attendance XML code
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_check_attendance"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#1f1f21"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.unmuteapp4gmail.unmute.CheckAttendance">

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textPersonName"
        android:hint="Enter ID"
        android:ems="10"
        android:layout_alignParentTop="true"
        android:theme="@style/MyEditText"
        android:textColorHint="#ffffff"
        android:textColor="#ffffff"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:id="@+id/editText" />

    <View
        android:layout_width="match_parent"
        android:layout_height="2dp"
        android:layout_marginTop="200dp"
        android:background="#ffffff"
        android:id="@+id/ViewTop"
        />

    <View
        android:layout_width="match_parent"
        android:layout_height="2dp"
        android:layout_marginTop="255dp"
        android:background="#ffffff"
        />

    <View
        android:layout_width="match_parent"
        android:layout_height="2dp"
        android:layout_marginTop="150dp"
        android:background="#ffffff"
        />

    <Button
        android:text=" Get Attendance "
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#5bcaff"
        android:textColor="#ffffff"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="90dp"
        android:id="@+id/buttonFind" />


    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_alignTop="@+id/textView2"
        android:layout_alignParentRight="true"
        android:id="@+id/textView4" />

    <TextView
        android:text="No. of Days Present"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/textView2"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_marginTop="10dp"
        android:id="@+id/textView5" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@+id/textView5"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_alignRight="@+id/textView4"
        android:layout_alignEnd="@+id/textView4"
        android:id="@+id/textView6" />

    <TextView
        android:text="No. of Days Absent"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/textView5"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_marginTop="10dp"
        android:id="@+id/textView7" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@+id/textView7"
        android:layout_alignRight="@+id/textView6"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_alignEnd="@+id/textView6"
        android:id="@+id/textView10" />

    <TextView
        android:text="Attendance %"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/textView7"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_marginTop="10dp"
        android:id="@+id/textView11" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@+id/textView11"
        android:layout_alignRight="@+id/textView10"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_alignEnd="@+id/textView10"
        android:id="@+id/textView12" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/textView11"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_marginTop="5dp"
        android:id="@+id/textView13" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@+id/textView13"
        android:layout_alignParentRight="true"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:id="@+id/textView14" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@+id/textView15"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_alignParentRight="true"
        android:id="@+id/textView16" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/buttonFind"
        android:layout_centerHorizontal="true"
        android:textColor="#ffffff"
        android:textSize="19dp"
        android:layout_marginTop="20dp"
        android:id="@+id/textView"
        android:textAlignment="center" />

    <TextView
        android:text="No. of working days"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/textView2"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_marginTop="95dp"
        android:layout_below="@+id/textView"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

    <TextView
        android:layout_width="160dp"
        android:layout_height="wrap_content"
        android:id="@+id/textView15"
        android:textColor="#ffffff"
        android:textSize="20dp"
        android:layout_alignParentBottom="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

    <EditText
        android:layout_width="160dp"
        android:layout_height="wrap_content"
        android:inputType="textPersonName"
        android:hint="Month(ex.January)"
        android:ems="10"
        android:textColorHint="#ffffff"
        android:theme="@style/MyEditText"
        android:textColor="#ffffff"
        android:layout_below="@+id/editText"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:id="@+id/editText2" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAlignment="center"
        android:textColor="#ffffff"
        android:layout_alignBottom="@+id/ViewTop"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="18dp"
        android:id="@+id/textViewName" />

    <EditText
        android:layout_width="150dp"
        android:layout_height="wrap_content"
        android:inputType="textPersonName"
        android:hint="Year(ex.2017)"
        android:textColorHint="#ffffff"
        android:theme="@style/MyEditText"
        android:textColor="#ffffff"
        android:ems="10"
        android:layout_below="@+id/editText"
        android:layout_alignParentRight="true"
        android:id="@+id/editTextYear" />

    <TextView
        android:layout_width="170dp"
        android:layout_height="wrap_content"
        android:textAlignment="center"
        android:textColor="#ffffff"
        android:layout_marginTop="18dp"
        android:id="@+id/textViewMonth"
        android:layout_below="@+id/ViewTop"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

    <TextView
        android:layout_width="140dp"
        android:layout_height="wrap_content"
        android:layout_alignBottom="@+id/textViewMonth"
        android:layout_alignParentRight="true"
        android:textColor="#ffffff"
        android:textAlignment="center"
        android:layout_alignParentEnd="true"
        android:id="@+id/textViewYear" />

</RelativeLayout>


package unmute;

public class unmute {

}
