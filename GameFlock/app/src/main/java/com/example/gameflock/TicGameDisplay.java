package com.example.gameflock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TicGameDisplay extends AppCompatActivity {

    private TicTacToeBoard ticTacToeBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_game_display);
        Button playAgain = findViewById(R.id.playAgainBtn);
        Button homeBtn = findViewById(R.id.homeBtn);
        TextView playerTurn = findViewById(R.id.playerTurn);

        playAgain.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);


        String[] playerNames = getIntent().getStringArrayExtra("playerNames");

        if (playerNames != null){
            playerTurn.setText((playerNames[0] + "'s Turn"));
        }

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        ticTacToeBoard.setUpGame(playAgain, homeBtn, playerTurn, playerNames);
    }

    public void homeButtonClick(View view){
        Intent intent = new Intent(this, TicTacToeHomeActivity.class);
        startActivity(intent);
    }



    public void RestartGame(View view){
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }
}





