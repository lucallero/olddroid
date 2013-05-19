package com.jogapc.sstopwatch;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SStopwatchActivity extends Activity implements OnClickListener {
	private Logger logger = Logger.getLogger(getClass().getName());
	private LaneLayout laneLayout1;
	private LaneLayout laneLayout2;
	private LaneLayout laneLayout3;
	private StopwatchThread labelUpdater1;
	private StopwatchThread labelUpdater2;
	private StopwatchThread labelUpdater3;

	private ArrayList<LaneLayout> laneLayoutList = new ArrayList<LaneLayout>(10);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sstopwatch_activity);
		LinearLayout layoutMain = (LinearLayout) findViewById(R.id.mainLayout);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT, 1);
		LaneLayout tmpLayout = null;
		for (int i = 0; i < 10; i++) {
			tmpLayout = new LaneLayout(this, i, Color.GRAY);
			tmpLayout.getToggleButton().setOnClickListener(this);
			tmpLayout.getButtonLap().setOnClickListener(this);
			tmpLayout.getButtonLap().setId(100 + i);
			tmpLayout.getToggleButton().setId(200 + i);
			tmpLayout.getTextViewChronometer().setId(300 + i);
			tmpLayout.getTextViewLane().setId(400 + i);
			laneLayoutList.add(tmpLayout);
		}
		laneLayout1 = new LaneLayout(this, 1, Color.GRAY);

		laneLayout2 = new LaneLayout(this, 2, Color.WHITE);
		laneLayout2.getToggleButton().setOnClickListener(this);
		laneLayout2.getButtonLap().setOnClickListener(this);
		laneLayout2.getButtonLap().setId(102);
		laneLayout2.getToggleButton().setId(202);
		laneLayout2.getTextViewChronometer().setId(302);
		laneLayout2.getTextViewLane().setId(402);

		laneLayout3 = new LaneLayout(this, 3, Color.GRAY);
		laneLayout3.getToggleButton().setOnClickListener(this);
		laneLayout3.getButtonLap().setOnClickListener(this);
		laneLayout3.getButtonLap().setId(103);
		laneLayout3.getToggleButton().setId(203);
		laneLayout3.getTextViewChronometer().setId(303);
		laneLayout3.getTextViewLane().setId(403);

		layoutMain.addView(laneLayout1, params);
		layoutMain.addView(laneLayout2, params);
		layoutMain.addView(laneLayout3, params);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sstopwatch_activity, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 101:
			System.out.println("1: button Lap clicked");
			break;
		case 102:
			System.out.println("2: button Lap clicked");
			break;
		case 103:
			System.out.println("3: button Lap clicked");
			break;
		case 201:
			logger.log(Level.INFO, "labelUpdater1:Toggle button clicked");
			logger.log(Level.INFO, "Toggle button state:"
					+ laneLayout1.getToggleButton().isChecked());
			if (laneLayout1.getToggleButton().isChecked()) {
				labelUpdater1 = new StopwatchThread(
						laneLayout1.getTextViewChronometer(),
						laneLayout1.getId(), this);
				labelUpdater1.start();
			} else {
				labelUpdater1.stopWork();
			}
			break;
		case 202:
			logger.log(Level.INFO, "labelUpdater2:Toggle button clicked");
			logger.log(Level.INFO, "Toggle button state:"
					+ laneLayout2.getToggleButton().isChecked());
			if (laneLayout2.getToggleButton().isChecked()) {
				labelUpdater2 = new StopwatchThread(
						laneLayout2.getTextViewChronometer(),
						laneLayout2.getId(), this);
				labelUpdater2.start();
			} else {
				labelUpdater2.stopWork();
			}
			break;
		case 203:
			logger.log(Level.INFO, "labelUpdater3:Toggle button clicked");
			logger.log(Level.INFO, "Toggle button state:"
					+ laneLayout3.getToggleButton().isChecked());
			if (laneLayout3.getToggleButton().isChecked()) {
				labelUpdater3 = new StopwatchThread(
						laneLayout3.getTextViewChronometer(),
						laneLayout3.getId(), this);
				labelUpdater3.start();
			} else {
				labelUpdater3.stopWork();
			}
			break;
		default:
			break;
		}
	}
}
