package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
    }
    public void openPlaylist1(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/spotify/playlist/37i9dQZF1DWSqmBTGDYngZ?si=D7BfmebvRN24JxaclSmteA"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist2(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/thewolfeman99/playlist/68mX4U9iqFeibp1t9X82gy?si=PrKa9hKFQ1Wp_cpOpSNk2Q"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist3(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/1225813554/playlist/0cUoffCqblGCNdQftuCpSj?si=IJofllCkQtqvW3dWebd-oQ"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist4(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/spotify/playlist/37i9dQZF1DX2MyUCsl25eb?si=h5YIo_OOTryizuJXBV586A"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist5(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/spotify/playlist/37i9dQZF1DX2MyUCsl25eb"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist6(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/intrinsiccafe/playlist/3ktL1Q2N0rsntPJpfYGuSB?si=kcefN64CRr26gTXXa2xudQ"));
        startActivity(spotifyIntent);
    }
}