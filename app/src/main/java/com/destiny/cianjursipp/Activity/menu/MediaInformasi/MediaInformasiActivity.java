package com.destiny.cianjursipp.Activity.menu.MediaInformasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.destiny.cianjursipp.Activity.menu.MediaInformasi.EducationNews.EducationNewsActivity;
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.EducationPodcast.EducationPodcastActivity;
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.EducationTalkshow.EducationTalkshowActivity;
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.Giveaway.GiveawayActivity;
import com.destiny.cianjursipp.R;

public class MediaInformasiActivity extends AppCompatActivity {
    CardView News,Talkshow,Podcast,Giveaway;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_informasi);
        News = findViewById(R.id.cardEducationNews);
        Talkshow = findViewById(R.id.cardEducationTalkshow);
        Podcast = findViewById(R.id.cardEducationPodcast);
        Giveaway = findViewById(R.id.cardGiveaway);

        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaInformasiActivity.this, EducationNewsActivity.class);
                startActivity(intent);
            }
        });
        Talkshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaInformasiActivity.this, EducationTalkshowActivity.class);
                startActivity(intent);
            }
        });
        Podcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaInformasiActivity.this, EducationPodcastActivity.class);
                startActivity(intent);
            }
        });
        Giveaway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaInformasiActivity.this, GiveawayActivity.class);
                startActivity(intent);
            }
        });
    }
}