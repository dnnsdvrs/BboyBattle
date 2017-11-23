package com.example.android.counter2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Global variables used to score and display the scores of judges and rounds.

    int judgeOneScore;
    int judgeTwoScore;
    int judgeThreeScore;
    int roundEnd;
    int roundsWonLeft;
    int roundsWonRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Judges scores, which sets the variables to either -1 or 1, depending on which side is pressed. Also displays a short Toast message for visual feedback

    public void judgeOneLeft(View v) {
        judgeOneScore = -1;
        Toast.makeText(MainActivity.this,"Judge 1 has spoken",Toast.LENGTH_SHORT).show();
    }

    public void judgeOneRight(View v) {
        judgeOneScore = 1;
        Toast.makeText(MainActivity.this,"Judge 1 has spoken",Toast.LENGTH_SHORT).show();
    }

    public void judgeTwoLeft(View v) {
        judgeTwoScore = -1;
        Toast.makeText(MainActivity.this,"Judge 2 has spoken",Toast.LENGTH_SHORT).show();
    }

    public void judgeTwoRight(View v) {
        judgeTwoScore = 1;
        Toast.makeText(MainActivity.this,"Judge 2 has spoken",Toast.LENGTH_SHORT).show();
    }

    public void judgeThreeLeft(View v) {
        judgeThreeScore = -1;
        Toast.makeText(MainActivity.this,"Judge 3 has spoken",Toast.LENGTH_SHORT).show();
    }

    public void judgeThreeRight(View v) {
        judgeThreeScore = 1;
        Toast.makeText(MainActivity.this,"Judge 3 has spoken",Toast.LENGTH_SHORT).show();
    }

    //End of the round: after all judges scored, add all scores and determine roundwinner.

    public void endRoundButton(View v) {
        int roundEnd = judgeOneScore + judgeTwoScore + judgeThreeScore;
        displayRoundScore(roundEnd);
        endScore(roundEnd);
    }

    //Update the scorecounter of the rounds

    public void displayRoundScore(int roundEnd) {
        if (roundEnd < 0) {
            roundsWonLeft = roundsWonLeft + 1;
            TextView roundViewLeft = (TextView) findViewById(R.id.textNumberLeft);
            roundViewLeft.setText(String.valueOf(roundsWonLeft));

        } else if (roundEnd > 0) {
            roundsWonRight = roundsWonRight + 1;
            TextView roundViewRight = (TextView) findViewById(R.id.textNumberRight);
            roundViewRight.setText(String.valueOf(roundsWonRight));

        }
    }

    //If one side wins three rounds, the winner is declared by displaying text 'winner' or 'smoked' on the appropiate side.

    public void endScore(int roundEnd) {
        if (roundsWonLeft == 3) {
            TextView scoreViewLeft = (TextView) findViewById(R.id.textResultLeft);
            TextView scoreViewRight = (TextView) findViewById(R.id.textResultRight);
            scoreViewLeft.setText(R.string.winner);
            scoreViewRight.setText(R.string.loser);
        } else if (roundsWonRight == 3) {
            TextView scoreViewLeft = (TextView) findViewById(R.id.textResultLeft);
            TextView scoreViewRight = (TextView) findViewById(R.id.textResultRight);
            scoreViewLeft.setText(R.string.loser);
            scoreViewRight.setText(R.string.winner);
        }
    }

    //New Battle button is pressed. Resets all variables to 0, deletes 'winner/loser' text and sets round counter to 0

    public void newBattle(View v) {
        roundEnd = 0;
        judgeThreeScore = 0;
        judgeTwoScore = 0;
        judgeOneScore = 0;
        roundsWonRight = 0;
        roundsWonLeft = 0;
        TextView scoreViewLeft = (TextView) findViewById(R.id.textResultLeft);
        TextView scoreViewRight = (TextView) findViewById(R.id.textResultRight);
        TextView roundViewLeft = (TextView) findViewById(R.id.textNumberLeft);
        TextView roundViewRight = (TextView) findViewById(R.id.textNumberRight);
        roundViewLeft.setText(String.valueOf(roundsWonLeft));
        roundViewRight.setText(String.valueOf(roundsWonRight));
        scoreViewLeft.setText(R.string.empty);
        scoreViewRight.setText(R.string.empty);
    }

    //Save the state of the variables and text after the screen orientation changes.

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("judgeOneScoreS", judgeOneScore);
        savedInstanceState.putInt("judgeTwoScoreS", judgeTwoScore);
        savedInstanceState.putInt("judgeThreeScoreS", judgeThreeScore);
        savedInstanceState.putInt("roundEndS", roundEnd);
        savedInstanceState.putInt("roundsWonLeftS", roundsWonLeft);
        savedInstanceState.putInt("roundsWonRightS", roundsWonRight);
        super.onSaveInstanceState(savedInstanceState);


    }

    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

            if (saveInstanceState != null) {
                judgeOneScore = saveInstanceState.getInt("judgeOneScoreS");
                judgeTwoScore = saveInstanceState.getInt("judgeTwoScoreS");
                judgeThreeScore = saveInstanceState.getInt("judgeThreeScoreS");
                roundsWonLeft = saveInstanceState.getInt("roundsWonLeftS");
                roundsWonRight = saveInstanceState.getInt("roundsWonRightS");
                endScore(roundEnd);
            }

        TextView roundViewLeft = (TextView) findViewById(R.id.textNumberLeft);
        TextView roundViewRight = (TextView) findViewById(R.id.textNumberRight);
        roundViewLeft.setText(String.valueOf(roundsWonLeft));
        roundViewRight.setText(String.valueOf(roundsWonRight));
        }


    //End Battle button is pressed. A text of 'winner' and 'loser' is displayed, depending on who won most rounds. This is automated now, after someone wins three rounds.

    /*public void endBattleButton (View v) {
        if (roundsWonLeft > roundsWonRight) {
            TextView scoreViewLeft = (TextView) findViewById(R.id.textResultLeft);
            TextView scoreViewRight = (TextView) findViewById(R.id.textResultRight);
            scoreViewLeft.setText(R.string.winner);
            scoreViewRight.setText(R.string.loser);
        } else if (roundsWonRight > roundsWonLeft) {
            TextView scoreViewLeft = (TextView) findViewById(R.id.textResultLeft);
            TextView scoreViewRight = (TextView) findViewById(R.id.textResultRight);
            scoreViewLeft.setText(R.string.loser);
            scoreViewRight.setText(R.string.winner);
        } else {
            TextView scoreViewLeft = (TextView) findViewById(R.id.textResultLeft);
            TextView scoreViewRight = (TextView) findViewById(R.id.textResultRight);
            scoreViewLeft.setText(R.string.tie);
            scoreViewRight.setText(R.string.tie);
        }
    }*/

    /*This would be the code used to change the color of the judges buttons after being pressed. However, I don't know how to change them back.


        public void judgeOneLeft(View v) {
        judgeOneScore = -1;
        ImageButton layout =(ImageButton) findViewById(R.id.judgeOneLeftButton);
        layout.setBackgroundResource(R.drawable.rounded_corners_pressed);
    }
     */


}
