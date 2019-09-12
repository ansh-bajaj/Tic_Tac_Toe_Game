package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.R;

public class MainActivity extends AppCompatActivity {


        int flag=0;
    boolean gameActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public void dropIn(View view) {

        // 0:O  1:X  2:empty
        ImageView counter = (ImageView) view;
        Log.i("Tag:", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter]==2 && gameActive==true) {
            gameState[tappedCounter] = flag;
            counter.setTranslationY(-1500);
            if (flag == 0) {
                counter.setImageResource(R.drawable.x);
                flag = 1;
            } else if (flag == 1) {
                counter.setImageResource(R.drawable.o);
                flag = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    gameActive = false;
                    if (flag == 1)
                        winner = "X";
                    else if (flag == 0)
                        winner = "O";

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    winnerTextView.setText(winner + " has WON!!!");


                }
            }
        }
    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility((View.INVISIBLE));
        GridLayout gridLayout= (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount();i++)
        {
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        flag=0;
        gameActive = true;
for(int i=0;i<gameState.length;i++)
        gameState[i] = 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}