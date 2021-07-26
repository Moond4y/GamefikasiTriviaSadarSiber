  package com.example.gamefikasitrivia;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.List;

import info.hoang8f.widget.FButton;

public class   MainGameActivity extends AppCompatActivity {
    FButton buttonA, buttonB, buttonC, buttonD;
    TextView questionText, triviaQuizText, timeText, resultText, coinText;
    TriviaQuizHelper triviaQuizHelper;
    TriviaQuestion currentQuestion;
    List<TriviaQuestion> list;
    DatabaseReference databaseReference;
    int qid = 0;
    int timeValue = 20;
    int highscore = 0;
    CountDownTimer countDownTimer;
    Typeface tb, sb;
    Users users = Users.getInstance();
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
        questionText = (TextView) findViewById(R.id.triviaQuestion);
        buttonA = (FButton) findViewById(R.id.buttonA);
        buttonB = (FButton) findViewById(R.id.buttonB);
        buttonC = (FButton) findViewById(R.id.buttonC);
        buttonD = (FButton) findViewById(R.id.buttonD);
        triviaQuizText = (TextView) findViewById(R.id.triviaQuizText);
        timeText = (TextView) findViewById(R.id.timeText);
        resultText = (TextView) findViewById(R.id.resultText);
        coinText = (TextView) findViewById(R.id.coinText);

        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        triviaQuizText.setTypeface(sb);
        questionText.setTypeface(tb);
        buttonA.setTypeface(tb);
        buttonB.setTypeface(tb);
        buttonC.setTypeface(tb);
        buttonD.setTypeface(tb);
        timeText.setTypeface(tb);
        resultText.setTypeface(sb);
        coinText.setTypeface(tb);

        triviaQuizHelper = new TriviaQuizHelper(this);
        triviaQuizHelper.getWritableDatabase();

        if (triviaQuizHelper.getAllOfTheQuestions().size() == 0) {
            triviaQuizHelper.allQuestion();
        }

        list = triviaQuizHelper.getAllOfTheQuestions();
        Collections.shuffle(list);
        currentQuestion = list.get(qid);

        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeText.setText(String.valueOf(timeValue) + "\"");
                timeValue -= 1;
                if (timeValue == -1) {
                    resultText.setText(getString(R.string.timeup));
                    disableButton();
                }
            }

            public void onFinish() {
                timeUp();
            }
        }.start();

        updateQueAndOptions();


    }
    private void addPostEventListener(DatabaseReference mPostReference) {
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Highscore hs = dataSnapshot.getValue(Highscore.class);
                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        };
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]
    }

    public void updateQueAndOptions() {
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());

        timeValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();
        coinText.setText(String.valueOf(highscore));
        highscore= highscore+100;

    }

    public void buttonA(View view) {
        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
            buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    public void buttonB(View view) {
        if (currentQuestion.getOptB().equals(currentQuestion.getAnswer())) {
            buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    public void buttonC(View view) {
        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
            buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    public void buttonD(View view) {
        if (currentQuestion.getOptD().equals(currentQuestion.getAnswer())) {
            buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    public void gameWon() {
        Intent intent = new Intent(this, GameWon.class);
        databaseReference= FirebaseDatabase.getInstance().getReference("Highscore");
        final String username=users.getUsername();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists()) {
                    if (!username.isEmpty()) {
                        Highscore hs = dataSnapshot.child(username).getValue(Highscore.class);
                        if ((highscore-100)>hs.getHighscore()) {
                            databaseReference.child(username).child("highscore").setValue(highscore);
                            Toast.makeText(MainGameActivity.this,"HIGHSCORE BARUMU!!!",Toast.LENGTH_LONG).show();
                        } else {
                        }
                    } else {

                    }

                } else {
                    databaseReference.child(username).child("username").setValue(username);
                    databaseReference.child(username).child("highscore").setValue(highscore - 100);


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startActivity(intent);
        finish();
    }

    public void gameLostPlayAgain() {
        final Intent intent = new Intent(this, PlayAgain.class);
        databaseReference= FirebaseDatabase.getInstance().getReference("Highscore");
        final String username=users.getUsername();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists()) {
                    if (!username.isEmpty()) {
                        Highscore hs = dataSnapshot.child(username).getValue(Highscore.class);
                        if ((highscore-100)>hs.getHighscore()) {
                            databaseReference.child(username).child("highscore").setValue(highscore - 100);
                            Toast.makeText(MainGameActivity.this,"HIGHSCORE BARUMU!!!",Toast.LENGTH_LONG).show();
                        } else {
                        }
                    } else {

                    }

                } else {
                    databaseReference.child(username).child("username").setValue(username);
                    databaseReference.child(username).child("highscore").setValue(highscore - 100);


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        startActivity(intent);
        finish();
        }

    public void timeUp() {
        Intent intent = new Intent(this, Time_Up.class);
        databaseReference= FirebaseDatabase.getInstance().getReference("Highscore");
        final String username=users.getUsername();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists()) {
                    if (!username.isEmpty()) {
                        Highscore hs = dataSnapshot.child(username).getValue(Highscore.class);
                        if ((highscore-100)>hs.getHighscore()) {
                            databaseReference.child(username).child("highscore").setValue(highscore - 100);
                            Toast.makeText(MainGameActivity.this,"HIGHSCORE BARUMU!!!",Toast.LENGTH_LONG).show();
                        } else {
                        }
                    } else {

                    }

                } else {
                    databaseReference.child(username).child("username").setValue(username);
                    databaseReference.child(username).child("highscore").setValue(highscore - 100);


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startActivity(intent);
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa kalian ingin Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int  id) {
                        Intent intent = new Intent(MainGameActivity.this, HomeScreen.class);
                        startActivity(intent);
                        MainGameActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(MainGameActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        onPause();

        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext = (FButton) dialogCorrect.findViewById(R.id.dialogNext);

        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCorrect.dismiss();
                qid++;
                currentQuestion = list.get(qid);
                updateQueAndOptions();
                resetColor();
                enableButton();
            }
        });
    }

    public void resetColor() {
        buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
    }

    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }
}
