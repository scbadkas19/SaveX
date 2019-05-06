package com.example.aosproject1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.util.Linkify;
import android.widget.TextView;


public class about extends AppCompatActivity {
    static TextView  textView;
    static String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        textView = (TextView)findViewById(R.id.em);
        email=String.valueOf(textView.getText());
        Linkify.addLinks(textView, Linkify.EMAIL_ADDRESSES);
        textView.setLinksClickable(true);

    }
    public static TextView getemail(){
        return textView;
    }
}






