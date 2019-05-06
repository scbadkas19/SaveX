package com.example.aosproject1;

import android.app.Dialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.sql.Timestamp.valueOf;


public class nav_act extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CalendarView calendarView;
    TextView dateDisplay;
      static String date;
      static String dat;
      static String time;





    String  userid;
    FirebaseDatabase mydb;
    DatabaseReference mydbref;
    private ListView mListView;


    private static final String TAG = "nav_act";
    int t;
    boolean saved;


   // private List<Member> mUsername=new ArrayList<>();
   // ArrayAdapter<Member> arrayAdapter=new ArrayAdapter<Member>(this,android.R.layout.simple_list_item_1,mUsername);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userid=user.getUid();
        Toast.makeText(this, "UserID: " + userid, Toast.LENGTH_SHORT).show();


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        time = df.format(c.getTime());


        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateDisplay = (TextView) findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                date=i2 + " - " + i1 + " - " + i;
                dateDisplay.setText(date);
                dat=i2 + " - " + i1 + " - " + i;


            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (date!=null){
                    startActivity(new Intent(nav_act.this,database.class));
                }
                else{
                    Toast.makeText(nav_act.this,"Please select a date.",Toast.LENGTH_SHORT).show();
                }

            }
        });


        mListView=(ListView)findViewById(R.id.ListView);
        mydb=FirebaseDatabase.getInstance();
        mydbref=mydb.getReference("Users");

         FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (date!=null){
                            showData(dataSnapshot);
                        }
                        else
                        {
                            Toast.makeText(nav_act.this,"Please select a date to display its records!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(nav_act.this,"Error!",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void showData(DataSnapshot dataSnapshot){

        //mUsername.clear();
        List<String> dso=new ArrayList<>();
        DataSnapshot dspath = dataSnapshot.child(userid);
        Iterable<DataSnapshot> dsfinal1 = dspath.getChildren();
        DataSnapshot dspath1 = dataSnapshot.child("Entries");
        Iterable<DataSnapshot> dsfinal2 = dspath1.getChildren();
        DataSnapshot dspath2 = dataSnapshot.child(date);
        Iterable<DataSnapshot> dsfinal3 = dspath2.getChildren();
        for(DataSnapshot ds : dsfinal1) {
            dso.add(ds.getKey());
            for(DataSnapshot ds1 : dsfinal2){
                dso.add(ds1.getKey());
                for(DataSnapshot ds2 : dsfinal3){
                    dso.add(ds2.getKey());
                    Member member = ds2.getValue(Member.class);
                    Toast.makeText(this, "Data="+member.getDescription(), Toast.LENGTH_SHORT).show();

                    member.setDescription(ds.getValue(Member.class).getDescription());


                    ArrayList<String> array  = new ArrayList<>();
                    array.add(member.getDescription());
                    ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
                    mListView.setAdapter(adapter);


                }
            }
        }

        }



    public void openDialog(){
        InfoDialog infoDialog=new InfoDialog();
        infoDialog.show(getSupportFragmentManager(), "Information dialog");
    }
    public void opencontact(){
        contact cont=new contact();
        cont.show(getSupportFragmentManager(), "About dialog");
    }


    public static String getTime()
    {
        return time;
    }
    public static String getDate()
    {
        return date;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_act, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
            });



        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.license) {
            opencontact();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
