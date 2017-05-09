package com.example.imagetoggler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TIMES_CLICKED_KEY = "TIMES_CLICKED_KEY";
    private static final String DISPLAY_IMAGE_KEY = "DISPLAY_IMAGE_KEY";
    private int timesClicked;
    private boolean displayImage;

    /**Override to load main activity and saved instance information */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            timesClicked = 0;
            displayImage = false;
        } else {
            timesClicked =(int) savedInstanceState.getSerializable(TIMES_CLICKED_KEY);
            displayImage =(boolean) savedInstanceState.getSerializable(DISPLAY_IMAGE_KEY);

            setClickCountText();
            setToggleImageVisibility();
        }

    }

    /**Override to add reset button to menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reset_menu, menu);

        return(super.onCreateOptionsMenu(menu));
    }

    /**Override to save private variables  */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(TIMES_CLICKED_KEY, timesClicked);
        outState.putSerializable(DISPLAY_IMAGE_KEY, displayImage);

        super.onSaveInstanceState(outState);
    }

    /**Override to handle clicks on reset button */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.reset:
                reset();
                return(true);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**Counts clicks made on Register a click button */
    public void countClick(View view) {
        timesClicked += 1;
        setClickCountText();
    }

    /**Called when toggle image button clicked*/
    public void toggleImage(View view) {
        displayImage = !displayImage;//or maybe I can do something with the view in which I display the image
        setToggleImageVisibility();
    }

    /**Called when reset button clicked */
    public void reset() {
        timesClicked = 0;
        displayImage = false;
        setClickCountText();
        setToggleImageVisibility();
    }

    /**Method to set value of click counting text */
    private void setClickCountText() {
        TextView clickCounterView = (TextView) findViewById(R.id.textView);
        if (timesClicked == 0) {
            clickCounterView.setText(getResources().getString(R.string.counter_message));
        } else {
            clickCounterView.setText("Clicked " + String.valueOf(timesClicked) + " times.");
        }
    }

    /**Method to set visibiliy of togglable image */
    private void setToggleImageVisibility() {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        if (displayImage)
            image.setVisibility(View.VISIBLE);
        else
            image.setVisibility(View.INVISIBLE);
    }

}
