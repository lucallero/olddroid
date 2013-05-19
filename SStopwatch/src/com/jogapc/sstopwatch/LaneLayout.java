package com.jogapc.sstopwatch;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LaneLayout extends LinearLayout {

	private TextView textViewChronometer;
	private TextView textViewLane;
	private Button buttonLap;
	private ToggleButton toggleButton;

	public LaneLayout(Context context) {
		super(context);
	}

	public LaneLayout(Context context, int id, int backgroundColor) {
		super(context);
		this.setId(id);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setBackgroundColor(backgroundColor);
		this.textViewChronometer = new TextView(context);
		this.textViewLane = new TextView(context);
		this.buttonLap = new Button(context);
		this.toggleButton = new ToggleButton(context);
		this.textViewChronometer.setText("0:00:00:000");
		this.textViewChronometer.setTextColor(Color.BLUE);
		this.textViewLane.setText("new Lane");
		this.buttonLap.setText("Lap");
		this.toggleButton.setText("Start");
		this.toggleButton.setTextOff("Start");
		this.toggleButton.setTextOn("Stop");
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT, 2);
		this.addView(textViewChronometer, params);
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