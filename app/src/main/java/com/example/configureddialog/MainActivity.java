package com.example.configureddialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The type Main activity.
 *
 * @author Dean David <dd0167@bs.amalnet.k12.il>
 * @version 1.6
 * @since 28 /11/2020
 */

public class MainActivity extends AppCompatActivity {

    LinearLayout linear;
    AlertDialog.Builder adb;
    final String[] colors={"Red","Green","Blue"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear=(LinearLayout) findViewById(R.id.linear);
    }

    /**
     * Click 1 - A message to change the background to one of the three primary colors and a button to cancel the message
     * @param view the view
     */
    public void click1(View view) {
        int []color=new int[]{0,0,0};
        adb=new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Choose a color");
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                color[which]=255;
                linear.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
            }
        });
        adb.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Click 2 - Background replacement message for any combination of the three primary colors when you press the OK button and the button to cancel the message
     * @param view the view
     */
    public void click2(View view) {
        int []color=new int[]{0,0,0};
        adb=new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Choose a combination of colors");
        adb.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked)
                {
                    color[which]=255;
                }
                else if (color[which]==255)
                {
                    color[which]=0;
                }
            }
        });
        adb.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                linear.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Click 3 - Returns the screen background to white
     * @param view the view
     */
    public void click3(View view) {
        adb=new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Click to reset the background");
        adb.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                linear.setBackgroundColor(Color.WHITE);
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Click 4 - A dialog box where text will be typed. When you click OK, the text content will appear in the toast. Clicking Cancel will close the panel
     * @param view the view
     */
    public void click4(View view) {
        adb=new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Enter text");
        EditText et=new EditText(this);
        et.setHint("Click here to type");
        adb.setView(et);
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s=et.getText().toString();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Create Options menu
     * @param menu the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add("Credits");
        return true;
    }

    /**
     * Click in Options menu
     * @param item the item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st=item.getTitle().toString();
        if (st.equals("Credits"))
        {
            Intent in=new Intent(this,CreditsActivity.class);
            startActivity(in);
        }
        return true;
    }
}