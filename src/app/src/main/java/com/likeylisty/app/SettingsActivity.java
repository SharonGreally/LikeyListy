package com.likeylisty.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.Toolbar;

import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Toolbar appActionbar;
    TextView settingsChangeBackgroundColor;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        appActionbar = findViewById(R.id.appActionBar);
        setActionBar(appActionbar);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> startActivity(new Intent(SettingsActivity.this, HomeActivity.class)));

        settingsChangeBackgroundColor = findViewById(R.id.settingsChangeBackgroundColor);
    }
}