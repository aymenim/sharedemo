package com.aymenim.universalshare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity
{
    private static final String TAG = "ShareActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);


        TextView textView = (TextView) findViewById(R.id.textview);
        ImageView imageView = (ImageView) findViewById(R.id.imageview);


        Intent receivedIntent = getIntent();
        String receivedAction = receivedIntent.getAction();

        Log.i(TAG, receivedAction);

        String receivedType = receivedIntent.getType();

        if(receivedAction.equals(Intent.ACTION_SEND))
        {
            Log.i(TAG, "content being shared.");

            Bundle bundle = receivedIntent.getExtras();
            String list = "";
            if (bundle != null)
            {
                for (String key : bundle.keySet())
                {
                    Object value = bundle.get(key);
                    Log.d(TAG, String.format("%s %s (%s)", key,
                            value.toString(), value.getClass().getName()));
                    list = list +String.format("%s %s (%s)", key,
                            value.toString(), value.getClass().getName())+ '\n';
                }
            }
            Uri receivedUri = (Uri)receivedIntent.getParcelableExtra("share_screenshot_as_stream");


            if(receivedUri != null)
            {
                Log.i(TAG, "uri: " + receivedUri.toString());
                imageView.setImageURI(receivedUri);
            }

            String receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
            if(receivedText != null)
            {
                Log.i(TAG, "txt: " + receivedText);
            }

            textView.setText(list);
        }
        else if(receivedAction.equals(Intent.ACTION_MAIN))
        {
            Log.i(TAG, "main action intent");
            textView.setText("> Nothing to see :(");
        }
    }
}
