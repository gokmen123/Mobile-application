package com.example.week5_tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String PLAYER_1 = "X";
    static final String PLAYER_2 = "O";
    boolean player1Turn = true;
    byte[][] board = new byte[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout tableLayout=findViewById(R.id.board);
        for(int i=0;i<3;i++){
            TableRow row=(TableRow) tableLayout.getChildAt(i);
            for (int j=0;j<3;j++){
                Button btn=(Button) row.getChildAt(j);
                btn.setOnClickListener(new CellListener(i,j));
            }
        }


    }
    class CellListener implements View.OnClickListener{
        int row,col;

        public CellListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            if (player1Turn){
                if(isValidMove(row,col)){
                    ((Button)v).setText(PLAYER_1);
                    board[row][col]=1;
                    if(gameEnded(row,col)==1){
                        Toast.makeText(MainActivity.this,"Player1Won!",Toast.LENGTH_LONG).show();
                        finish();

                    }
                    if(gameEnded(row,col)==2){
                        Toast.makeText(MainActivity.this,"Player2Won!",Toast.LENGTH_LONG).show();
                        finish();

                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Cell is already occupied!",Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else{

                if(isValidMove(row,col)){
                    ((Button)v).setText(PLAYER_2);
                    board[row][col]=2;
                    if(gameEnded(row,col)==1){
                        Toast.makeText(MainActivity.this,"Player1Won!",Toast.LENGTH_LONG).show();
                        finish();

                    }
                    if(gameEnded(row,col)==2){
                        Toast.makeText(MainActivity.this,"Player2Won!",Toast.LENGTH_LONG).show();
                        finish();

                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Cell is already occupied!",Toast.LENGTH_LONG).show();
                    return;
                }
            }
            if(gameEnded(row,col)==-1){
                player1Turn=!player1Turn;
            }

        }

    }
    public boolean isValidMove(int row,int col){
        if(board[row][col]==2 || board[row][col]==1){
            return false;
        }
        else{
            return true;
        }

    }
    public int gameEnded(int row,int col){
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == 1 &&  board[i][1]==1 &&  board[i][2]==1){
                return 1; //for rows
            }
            if (board[i][0] == 2 &&  board[i][1]==2 &&  board[i][2]==2){
                return 2; //for rows
            }
            if(board[0][i]==1 && board[1][i]==1 && board[2][i]==1){
                return 1;
            }
            if(board[0][i]==2 && board[1][i]==2 && board[2][i]==2){
                return 2;
            }
            else{
                return -1;
            }
        }
        return -1;



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("player1Turn",player1Turn);
        byte [] boardSingle=new byte[9];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                boardSingle[3*i+j]=board[i][j];
            }
        }
        outState.putByteArray("board",boardSingle);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        player1Turn=savedInstanceState.getBoolean("player1Turn");

        byte [] boardSingle= savedInstanceState.getByteArray("board");
        for(int i=0;i<9;i++){
            board[i/3][1%3]=boardSingle[i];
        }
        TableLayout tableLayout=findViewById(R.id.board);
        for(int i=0;i<3;i++){
            TableRow row=(TableRow) tableLayout.getChildAt(i);
            for (int j=0;j<3;j++){
                Button btn=(Button) row.getChildAt(j);
                if(board[i][j]==1){
                    btn.setText("X");
                }
                else if(board[i][j]==2){
                    btn.setText("O");
                }

            }
        }

    }
}