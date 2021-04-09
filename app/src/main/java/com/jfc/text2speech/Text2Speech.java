package com.jfc.text2speech;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
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

        final EditText et = (EditText) findViewById(R.id.theText);

        Button b;
        b = (Button) findViewById(R.id.speak_button);
        if (b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String t = et.getText().toString();
                    if ((t == null) || t.equals(""))
                        t = et.getHint().toString();
                    if ((t != null) && !t.equals("")) {

                        final String finalText = t;
                        mTts = new TextToSpeech(mActivity, new TextToSpeech.OnInitListener() {

                            @Override
                            public void onInit(int status) {
                                // TODO Auto-generated method stub
                                if(status == TextToSpeech.SUCCESS){
                                    int result=mTts.setLanguage(Locale.US);
                                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                                        Log.e(TAG, "This Language is not supported");
                                    }
                                    else{
                                        ConvertTextToSpeech(finalText);
                                    }
                                }
                                else
                                    Log.e("error", "Initilization Failed!");
                            }
                        });

                    }
                }
            });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void ConvertTextToSpeech(String text) {
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else {
	    mTts.setSpeechRate(SPEACH_RATE);
	    mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
    }
}
