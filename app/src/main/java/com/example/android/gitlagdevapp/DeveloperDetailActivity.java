package com.example.android.gitlagdevapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by tonero hp02 on 20-Aug-17.
 */

public class DeveloperDetailActivity extends AppCompatActivity {
    Button button;
    Intent shareIntent;
    String shareBody = "Check out this awesome developer @";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_detail);

        final TextView textView = (TextView)findViewById(R.id.username_text_view);
        textView.setText(getIntent().getExtras().getString("developerUsername"));

        final TextView website = (TextView)findViewById(R.id.url_text_view);
        website.setAutoLinkMask(Linkify.WEB_URLS);
        website.setText(getIntent().getExtras().getString("developerUrl"));

        ImageView imageView = (ImageView)findViewById(R.id.image_view);
        String getImageUrl = getIntent().getStringExtra("developerImage");
        Picasso.with(DeveloperDetailActivity.this).load(getImageUrl).into(imageView);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GitLagDevApp");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody + textView.getText() + ", " + website.getText().toString());
                startActivity(Intent.createChooser(shareIntent, "Share via: "));

            }
        });

    }

}

