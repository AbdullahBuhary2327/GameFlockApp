package com.example.gameflock;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int [][] gameBoard;
    private Button playAgainBtn;
    private Button homeBtn;
    private TextView playerTurn;
    private String [] playerNames = {"Player 1", "Player 2"};

    //row,col,type
    private int [] winType = {-1,-1,-1};
    //end

    private int player = 1;

    GameLogic(){
        gameBoard = new int[3][3];
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                gameBoard [r][c] = 0;
            }
        }


    }

    public boolean updateGameBoard(int row, int col){
        if (gameBoard[row-1][col-1] == 0){
            gameBoard[row-1][col-1] = player;

            if (player == 1){
                playerTurn.setText((playerNames[1]+ "'s Turn"));
            }else {
                playerTurn.setText((playerNames[0]+ "'s Turn"));
            }

            return true;
        }
        else {
            return false;
        }
    }

    public boolean winnerCheck(){
        boolean isWinner = false;
        for (int r=0; r<3; r++) {
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] &&
                    gameBoard[r][0] != 0) {
                //this
                winType = new int[] {r,0,1};
                //end
                isWinner = true;
            }
        }

            for (int c = 0; c < 3; c++) {
                if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[2][c] == gameBoard[0][c] &&
                        gameBoard[0][c] != 0) {
                    //this
                    winType = new int[] {0,c,2};
                    //end
                    isWinner = true;
                }
            }
            if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[2][2] == gameBoard[0][0] &&
                    gameBoard[0][0] != 0) {
                //this
                winType = new int[] {0,2,3};
                //end
                isWinner = true;
            }

            if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] &&
                    gameBoard[2][0] != 0) {
                //this
                winType = new int[] {2,2,4};
                //end
                isWinner = true;
            }

        int boardFilled = 0;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (gameBoard[r][c] != 0){
                    boardFilled+=1;
                }
            }
        }

        if (isWinner) {
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText((playerNames[player - 1] + " Won!!!"));
            return true;

        } else if (boardFilled == 9) {
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText(" Game Tied!!!");
            return true;

        }
        else {
            return false;
        }
    }




    public void resetGame(){
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                gameBoard [r][c] = 0;
            }
        }

        player = 1;
        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);
        playerTurn.setText((playerNames[0] +"'s Turn"));
        winType = new int[] {-1, -1, -1};

    }

    public void setPlayAgainBtn(Button playAgainBtn) {
        this.playAgainBtn = playAgainBtn;
    }

    public void setHomeBtn(Button homeBtn) {
        this.homeBtn = homeBtn;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    //getter for winType


    public int[] getWinType() {
        return winType;
    }
    //end

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }
}
