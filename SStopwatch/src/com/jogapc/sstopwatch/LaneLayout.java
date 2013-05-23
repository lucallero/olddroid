package com.jogapc.sstopwatch;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LaneLayout extends LinearLayout {

    private TextView textChronometer;
    private TextView textLane;
    private TextView textBestLap;
    private TextView textWorstLap;
    private TextView textAverage;
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
	this.textBestLap = new TextView(context);
	this.textBestLap.setText("Best lap:");
	this.textBestLap.setTypeface(null, Typeface.BOLD);
	this.textWorstLap = new TextView(context);
	this.textWorstLap.setText("Worst lap:");
	this.textWorstLap.setTypeface(null, Typeface.BOLD);
	this.textAverage = new TextView(context);
	this.textAverage.setText("Average:");
	this.textAverage.setTypeface(null, Typeface.BOLD);
	this.textChronometer = new TextView(context);
	this.textChronometer.setText("0:00:00:000");
	this.textChronometer.setTextColor(Color.BLUE);
	this.textChronometer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
	this.textChronometer.setTypeface(null, Typeface.BOLD);
	// this.textChronometer.setTextAlignment(TEXT_ALIGNMENT_CENTER);
	this.textLane = new TextView(context);
	this.textLane.setText("new Lane");
	this.toggleButton = new ToggleButton(context);
	this.toggleButton.setText("Start");
	this.toggleButton.setTextOff("Reset");
	this.toggleButton.setTextOn("Stop");
	this.scrollSplit = new ScrollView(context);
	this.scrollSplit.setVerticalScrollBarEnabled(true);
	this.scrollFrame = new LinearLayout(context);
	this.scrollFrame.setOrientation(LinearLayout.VERTICAL);
	this.addView(textChronometer);
	this.addView(scrollSplit, scrollLayoutParams);
	this.scrollSplit.addView(scrollFrame);
	this.addView(textAverage);
	this.addView(textBestLap);
	this.addView(textWorstLap);
	this.addView(buttonLap);
	this.addView(toggleButton);
	this.addView(textLane);
    }

    public Button getButtonLap() {
	return buttonLap;
    }

    public ToggleButton getToggleButton() {
	return toggleButton;
    }

    public LinearLayout getScrollFrame() {
	return scrollFrame;
    }

    public TextView getTextChronometer() {
	return textChronometer;
    }

    public TextView getTextLane() {
	return textLane;
    }

    public TextView getTextBestLap() {
	return textBestLap;
    }

    public TextView getTextWorstLap() {
	return textWorstLap;
    }

    public TextView getTextAverage() {
	return textAverage;
    }

}