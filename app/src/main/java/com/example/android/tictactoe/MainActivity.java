package com.example.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int state[]={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int winning[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int active=0;
    int counter=0;
    boolean flag=false;
    public void display(String s){
        TextView tv=(TextView)findViewById(R.id.text);
        tv.setText(s);
        return;
    }
    public void game(View v){
        ImageView iv=(ImageView)v;
        int tg=Integer.parseInt(iv.getTag().toString())-1;
        if(active==1&&state[tg]==-1 && !flag){
            iv.setScaleX(0f);
            iv.setScaleY(0f);
            iv.setRotationY(0f);
            iv.setImageResource(R.drawable.zero);
            iv.animate().scaleX(1).scaleY(1).rotationY(360).setDuration(1000);
            active--;
            state[tg]=1;
            display("Player with cross next move");
        }
        else{
            if(active==0&&state[tg]==-1 &&!flag) {
                iv.setScaleX(0f);
                iv.setScaleY(0f);
                iv.setRotation(0f);
                iv.setImageResource(R.drawable.cross);
                iv.animate().scaleX(1).scaleY(1).rotation(360).setDuration(1000);
                active++;
                state[tg]=0;
                display("Player with zero next move");
            }
        }
        counter++;
        checkWin();

    }
    public void checkWin(){
        int i,j;
        TextView tv=(TextView)findViewById(R.id.text);
        for(i=0;i<8;i++){
            if(state[winning[i][0]]==state[winning[i][1]] && state[winning[i][1]]==state[winning[i][2]] && state[winning[i][0]]!=-1){
                if(state[winning[i][0]]==0){
                    tv.setText("Player with Cross Wins");
                    flag=true;
                    vis();
                }else{
                    if(state[winning[i][0]]==1){
                        tv.setText("Player with Zero Wins");
                        flag=true;
                        vis();
                    }
                }
                break;
            }
        }
        if(counter==9 && flag==false){
            tv.setText("Draw Match click on reset to reset match");
            vis();
            return;
        }
    }
    public void vis(){
        Button bt=(Button)findViewById(R.id.button);
        bt.setVisibility(View.VISIBLE);
    }
    public void reset(View v){
        counter=0;
        GridLayout gl=(GridLayout)findViewById(R.id.gl);
        Button bt=(Button)findViewById(R.id.button);
        bt.setVisibility(View.INVISIBLE);
        int i;
        for(i=0;i<state.length;i++){
            state[i]=-1;
        }
        for(i=0;i<state.length;i++){
            Log.i("State",Integer.toString(state[i]));
        }
        active=0;
        flag=false;
        for(i=0;i<gl.getChildCount();i++){
            ((ImageView)gl.getChildAt(i)).setImageResource(0);
        }
        display("Player with cross start the game");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display("Player with cross start the game");
        Button bt=(Button)findViewById(R.id.button);
        bt.setVisibility(View.GONE);
    }
}
