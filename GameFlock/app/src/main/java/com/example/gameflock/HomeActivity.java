package com.example.gameflock;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameflock.service.NetworkBroadcast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    ImageView imageView;
    FirebaseAuth mAuth;
    TextView textview, emailview;
    FirebaseUser user;
    ImageView button;
    ImageView flappingBird;
    private BroadcastReceiver broadcastReceiver;
    ImageView brickBreaker;

    ImageView tictactoe;
    TextView txtSG, txtBB, txtTTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        broadcastReceiver = new NetworkBroadcast();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        imageView = findViewById(R.id.imageView);
        mAuth = FirebaseAuth.getInstance();
        textview = findViewById(R.id.logoutTxt);
        emailview = findViewById(R.id.emailView);
        user= mAuth.getCurrentUser();
        button = findViewById(R.id.buttonprofile);
        tictactoe = findViewById(R.id.tictactoe);
        brickBreaker = findViewById(R.id.brickBreaker);
        flappingBird = findViewById(R.id.snake);
        txtSG = findViewById(R.id.txtSG);
        txtBB = findViewById(R.id.txtBB);
        txtTTT = findViewById(R.id.txtTTT);

        txtSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SnakeHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BrickBreakerHome.class);
                startActivity(intent);
                finish();
            }
        });

        txtTTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TicTacToeHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        brickBreaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BrickBreakerHome.class);
                startActivity(intent);
                finish();
            }
        });

        flappingBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SnakeHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tictactoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TicTacToeHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        else {
            emailview.setText(user.getEmail());
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}