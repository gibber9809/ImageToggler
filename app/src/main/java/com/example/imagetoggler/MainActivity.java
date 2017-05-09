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
    private int mTimesClicked = 0;
    private boolean mDisplayImage = false;
    private TextView mClickCounterView = null;
    private ImageView mImage = null;

    /**Override to load main activity and saved instance information */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            mTimesClicked = savedInstanceState.getInt(TIMES_CLICKED_KEY);
            mDisplayImage = savedInstanceState.getBoolean(DISPLAY_IMAGE_KEY);

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
        outState.putInt(TIMES_CLICKED_KEY, mTimesClicked);
        outState.putBoolean(DISPLAY_IMAGE_KEY, mDisplayImage);

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
        mTimesClicked += 1;
        setClickCountText();
    }

    /**Called when toggle image button clicked*/
    public void toggleImage(View view) {
        mDisplayImage = !mDisplayImage;//or maybe I can do something with the view in which I display the image
        setToggleImageVisibility();
    }

    /**Getter method for the view containing the image */
    public ImageView getImageView() {
        if (mImage == null)
            mImage = (ImageView) findViewById(R.id.imageView);
        return mImage;
    }

    /**Getter method for the view containing the click counting text */
    public TextView getClickCounterView() {
        if (mClickCounterView == null)
            mClickCounterView =(TextView) findViewById(R.id.textView);
        return mClickCounterView;
    }

    /**Called when reset button clicked */
    private void reset() {
        mTimesClicked = 0;
        mDisplayImage = false;
        setClickCountText();
        setToggleImageVisibility();
    }

    /**Method to set value of click counting text */
    private void setClickCountText() {
        TextView clickCounterView = getClickCounterView();
            if (mTimesClicked == 0) {
                clickCounterView.setText(R.string.counter_message);
            } else {
                clickCounterView.setText(String.format(getString(R.string.counter_text_format), mTimesClicked));
        }
    }

    /**Method to set visibiliy of togglable image */
    private void setToggleImageVisibility() {
        ImageView image = getImageView();
        if (mDisplayImage)
            image.setVisibility(View.VISIBLE);
        else
            image.setVisibility(View.INVISIBLE);
    }

}
