package com.example.aosproject1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.TextView;

public class contact extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("SaveX")
                .setMessage("Welcome to SaveX!" +"\n"+ "\nSupport"+"\nFor submitting bug reports,suggestions,feedback and queries,please mail the developer at badkas.saurabh@savex.com "+"\n"+"\nLicense"+"\nThis app is made as a group project by Students of BTech Data Science(SEM-IV).The students included in the group project are Manish Rane[J057],Saurabh Badkas[J059],Jenil Gathani[J061].")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

}
