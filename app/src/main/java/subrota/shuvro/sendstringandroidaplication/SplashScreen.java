package subrota.shuvro.sendstringandroidaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    private ProgressDialog pd;
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        pd = new ProgressDialog(SplashScreen.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            String message =(String) bundle.get("msg");
            progressShow(message);
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void progressShow(String message){
        pd.setMessage(message);
        pd.show();
    }
}