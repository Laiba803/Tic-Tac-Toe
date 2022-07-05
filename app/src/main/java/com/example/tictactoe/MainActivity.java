package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter=1;
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
        if (!gameActive) {
            gameReset(view);
        }
        if(counter == 0) {
            counter=1;
        }
        else {
            if (gameState[tappedImage] == 2) {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);
                TextView status = findViewById(R.id.Status);
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.cross_1);
                    activePlayer = 1;
                    status.setText("O's Turn - Tap to play");
                } else {
                    img.setImageResource(R.drawable.circle_1);
                    activePlayer = 0;
                    status.setText("X's Turn - Tap to play");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
            //finding winner
            for (int[] winPos : winPositions) {
                if (gameState[winPos[0]] == gameState[winPos[1]] &&
                        gameState[winPos[1]] == gameState[winPos[2]] &&
                        gameState[winPos[0]] != 2) {
                    String winnerStr;
                    gameActive = false;
                    if (gameState[winPos[0]] == 0)
                        winnerStr = "X has won the match";
                    else
                        winnerStr = "O has won the match";
                    TextView status = findViewById(R.id.Status);
                    status.setText(winnerStr);
                    break;
                }
                else{
                    int draw=0;
                    for (int state: gameState){
                        if(state == 2)
                            draw++;
                    }
                    if(draw == 0){
                        String winnerStr;
                        winnerStr = "match draw";
                        TextView status = findViewById(R.id.Status);
                        status.setText(winnerStr);
                        gameActive = false;
                    }
                }
            }
        }
    }

    public void gameReset(View view) {
        gameActive=true;
        activePlayer=0;
        for(int i=0;i< gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.Status);
        status.setText("X's Turn - Tap to play");
        counter=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}