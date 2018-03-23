package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MusicActivity extends AppCompatActivity {

    Button playlist1;
    //adapterobject for list view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        playlist1 =(Button)findViewById(R.id.playlist1);


        playlist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/album/54OXaFd0PtGtAgauTmARkD"));
                startActivity(spotifyIntent);
            }
        });

    }

}