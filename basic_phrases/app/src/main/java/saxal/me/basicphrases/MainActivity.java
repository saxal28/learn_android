package saxal.me.basicphrases;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playSound(View view) {
        String tag = view.getTag().toString();

        // if playing, stop
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }

        // load audio file url and create audio-player
        mediaPlayer = MediaPlayer.create(this, getSoundFile(tag));
        // play sound
        mediaPlayer.start();

    }

    public int getSoundFile(String tag) {
        switch (tag) {
            case "sound1":
                return R.raw.doyouspeakenglish;
            case "sound2":
                return R.raw.goodevening;
            case "sound3":
                return R.raw.hello;
            case "sound4":
                return R.raw.howareyou;
            case "sound5":
                return R.raw.ilivein;
            case "sound6":
                return R.raw.mynameis;
            case "sound7":
                return R.raw.please;
            case "sound8":
                return R.raw.welcome;
            default:
                return R.raw.doyouspeakenglish;

        }

    }

}
