package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // State meanings:
    //   0 - X
    //   1 - O
    //   2 - Null
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            TextView status = findViewById(R.id.Status);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.cross_1);
                activePlayer = 1;
                status.setText("O's Turn - Tap to play");
            }
            else{
                img.setImageResource(R.drawable.circle_1);
                activePlayer = 0;
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //finding winner
        for(int[] winPos: winPositions){
            if(gameState[winPos[0]] == gameState[winPos[1]] &&
                    gameState[winPos[1]] == gameState[winPos[2]] &&
                    gameState[winPos[0]] != 2) {
                String winner;
                gameActive=false;
                if (gameState[winPos[0]] == 0)
                    winner = "X has won the match";
                else
                    winner = "O has won the match";
                TextView status = findViewById(R.id.Status);
                status.setText(winner);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}