package com.jfc.text2speech;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class Text2Speech extends Activity {
    static final String TAG = "jfc.Margaritaville";

    private static final float SPEACH_RATE = 0.75f;
    
    private Activity mActivity = this;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margaritaville);

        Button b1 = findViewById(R.id.speak_button1);
        b1.setOnClickListener(v -> buttonClickImp(findViewById(R.id.theText1)));

        Button b2 = findViewById(R.id.speak_button2);
        b2.setOnClickListener(v -> buttonClickImp(findViewById(R.id.theText2)));

        Button b3 = findViewById(R.id.speak_button3);
        b3.setOnClickListener(v -> buttonClickImp(findViewById(R.id.theText3)));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void buttonClickImp(final EditText et) {
        String etStr = et.getText().toString();
        if (etStr.equals(""))
            etStr = et.getHint().toString();
        if (!etStr.equals("")) {
            final String txt = etStr;
            mTts = new TextToSpeech(mActivity, status -> {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                        Log.e(TAG, "This Language is not supported");
                    else {
                        if("".equals(txt))
                            mTts.speak("Content not available", TextToSpeech.QUEUE_FLUSH, null);
                        else {
                            mTts.setSpeechRate(SPEACH_RATE);
                            mTts.speak(txt, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                } else
                    Log.e("error", "Initilization Failed!");
            });
        }
    }
}
