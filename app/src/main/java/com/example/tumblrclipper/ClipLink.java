package com.example.tumblrclipper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tumblrclipper.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ClipLink extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();


        if (Intent.ACTION_SEND.equals(action) && type != null){
            if ("text/plain".equals(type)){
                handleSentText(intent);
            }
        }
    }
    void handleSentText(Intent intent)
    {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) // handle the link here
        {
            int linkStart = sharedText.indexOf("http");
            int linkEnd = sharedText.lastIndexOf(' ', linkStart);
            String link = sharedText.substring(linkStart,linkEnd);
            link = link.replace("at.", "");
            String sendText = link.trim();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, sendText);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent,"Share Tumblr link via:");
            startActivity(shareIntent);

        }
    }
}
