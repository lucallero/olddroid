package com.jogapc.sstopwatch;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LaneLayout extends LinearLayout {

    private TextView textViewChronometer;
    private TextView textViewLane;
    private Button buttonLap;
    private ToggleButton toggleButton;
    private ScrollView scrollSplit;
    private LinearLayout scrollFrame;

    public LaneLayout(Context context) {
	super(context);
    }

    public LaneLayout(Context context, int id, int backgroundColor) {
	super(context);
	LinearLayout.LayoutParams scrollLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
	this.setId(id);
	this.setOrientation(LinearLayout.VERTICAL);
	this.setBackgroundColor(backgroundColor);
	this.buttonLap = new Button(context);
	this.buttonLap.setText("Lap");
	this.textViewChronometer = new TextView(context);
	this.textViewChronometer.setText("0:00:00:000");
	this.textViewChronometer.setTextColor(Color.BLUE);
	this.scrollSplit = new ScrollView(context);
	this.textViewLane = new TextView(context);
	this.textViewLane.setText("new Lane");
	this.toggleButton = new ToggleButton(context);
	this.toggleButton.setText("Start");
	this.toggleButton.setTextOff("Start");
	this.toggleButton.setTextOn("Stop");
	this.scrollSplit = new ScrollView(context);
	this.scrollSplit.setVerticalScrollBarEnabled(true);
	this.scrollFrame = new LinearLayout(context);
	this.scrollFrame.setOrientation(LinearLayout.VERTICAL);
	this.addView(textViewChronometer);
	this.addView(scrollSplit, scrollLayoutParams);
	this.scrollSplit.addView(scrollFrame);
	for (int i = 0; i < 30; i++) {
	    TextView lapText = new TextView(context);
	    lapText.setText("lap " + i);
	    scrollFrame.addView(lapText);
	}
	this.addView(buttonLap);
	this.addView(toggleButton);
	this.addView(textViewLane);
    }

    public TextView getTextViewChronometer() {
	return textViewChronometer;
    }

    public TextView getTextViewLane() {
	return textViewLane;
    }

    public Button getButtonLap() {
	return buttonLap;
    }

    public ToggleButton getToggleButton() {
	return toggleButton;
    }

}