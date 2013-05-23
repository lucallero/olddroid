package com.jogapc.sstopwatch;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SStopwatchActivity extends Activity implements OnClickListener {
    private Logger logger = Logger.getLogger(getClass().getName());
    private ArrayList<LaneLayout> laneLayoutList = new ArrayList<LaneLayout>(10);
    private SparseArray<StopwatchThread> stopwatchThreadList = new SparseArray<StopwatchThread>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.sstopwatch_activity);
	LinearLayout layoutMain = (LinearLayout) findViewById(R.id.mainLayout);
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
	LaneLayout tmpLayout = null;
	for (int i = 0; i < 10; i++) {
	    if (i == 0 || i % 2 == 0) {
		tmpLayout = new LaneLayout(this, i, Color.GRAY);
	    } else {
		tmpLayout = new LaneLayout(this, i, Color.WHITE);
	    }
	    tmpLayout.getToggleButton().setOnClickListener(this);
	    tmpLayout.getButtonLap().setOnClickListener(this);
	    tmpLayout.getButtonLap().setId(100 + i);
	    tmpLayout.getToggleButton().setId(200 + i);
	    tmpLayout.getTextChronometer().setId(300 + i);
	    tmpLayout.getTextLane().setId(400 + i);
	    laneLayoutList.add(tmpLayout);
	    // layoutMain.addView(tmpLayout, params);
	}
	layoutMain.addView(laneLayoutList.get(0), params);
	layoutMain.addView(laneLayoutList.get(1), params);
	layoutMain.addView(laneLayoutList.get(2), params);
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
	case 100:
	    System.out.println("3: button Lap clicked");
	    if (stopwatchThreadList.get(v.getId() - 100).isRunning()) {
		TextView splitTime = new TextView(this);
		stopwatchThreadList.get(v.getId() - 100).splitTime(splitTime);
	    }
	    break;
	case 101:
	    System.out.println("2: button Lap clicked");
	    if (stopwatchThreadList.get(v.getId() - 100).isRunning()) {
		TextView splitTime = new TextView(this);
		stopwatchThreadList.get(v.getId() - 100).splitTime(splitTime);
	    }
	    break;
	case 102:
	    System.out.println("3: button Lap clicked");
	    if (stopwatchThreadList.get(v.getId() - 100).isRunning()) {
		TextView splitTime = new TextView(this);
		stopwatchThreadList.get(v.getId() - 100).splitTime(splitTime);
	    }
	    break;
	case 200:
	    logger.log(Level.INFO, "labelUpdater1:Toggle button clicked");
	    logger.log(Level.INFO, "Toggle button state:"
		    + laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked());
	    if (laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked()) {
		    stopwatchThreadList.append(v.getId() - 200,
				new StopwatchThread(
					// @formatter off
					laneLayoutList.get(v.getId() - 200).getTextChronometer(),
					laneLayoutList.get(v.getId() - 200).getTextBestLap(),
					laneLayoutList.get(v.getId() - 200).getTextWorstLap(),
					laneLayoutList.get(v.getId() - 200).getTextAverage(),
					laneLayoutList.get(v.getId() - 200).getScrollFrame(),
					laneLayoutList.get(v.getId() - 200).getId(), this));
			// @formatter on
			stopwatchThreadList.get(v.getId() - 200).start();
	    } else {
		if ("Reset".equals(laneLayoutList.get(v.getId() - 200).getToggleButton().getText())) {
		    laneLayoutList.get(v.getId() - 200).getScrollFrame().removeAllViews();
		    laneLayoutList.get(v.getId() - 200).getTextChronometer().setText("0:00:00:000");
		    laneLayoutList.get(v.getId() - 200).getTextAverage().setText("Average: 0:00:00:000");
		    laneLayoutList.get(v.getId() - 200).getTextBestLap().setText("Best lap: 0:00:00:000");
		    laneLayoutList.get(v.getId() - 200).getTextWorstLap().setText("Worst lap: 0:00:00:000");
		    laneLayoutList.get(v.getId() - 200).getToggleButton().setText("Start");
		    laneLayoutList.get(v.getId() - 200).getToggleButton().setChecked(false);
		    System.out.println("Limpou!");
		}
		else{
		    TextView splitTime = new TextView(this);
		    stopwatchThreadList.get(v.getId() - 200).stopWork(splitTime);
		    laneLayoutList.get(v.getId() - 200).getToggleButton().setText("Reset");
		}
	    }
	    break;
	case 201:
	    logger.log(Level.INFO, "labelUpdater2:Toggle button clicked");
	    logger.log(Level.INFO, "Toggle button state:"
		    + laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked());
	    if (laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked()) {
		laneLayoutList.get(v.getId() - 200).getScrollFrame().removeAllViews();
		stopwatchThreadList.append(v.getId() - 200,
			new StopwatchThread(
				// @formatter off
				laneLayoutList.get(v.getId() - 200).getTextChronometer(),
				laneLayoutList.get(v.getId() - 200).getTextBestLap(),
				laneLayoutList.get(v.getId() - 200).getTextWorstLap(),
				laneLayoutList.get(v.getId() - 200).getTextAverage(),
				laneLayoutList.get(v.getId() - 200).getScrollFrame(),
				laneLayoutList.get(v.getId() - 200).getId(), this));
		// @formatter on
		stopwatchThreadList.get(v.getId() - 200).start();
	    } else {
		TextView splitTime = new TextView(this);
		stopwatchThreadList.get(v.getId() - 200).stopWork(splitTime);
	    }
	    break;
	case 202:
	    logger.log(Level.INFO, "labelUpdater3:Toggle button clicked");
	    logger.log(Level.INFO, "Toggle button state:"
		    + laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked());
	    if (laneLayoutList.get(v.getId() - 200).getToggleButton().isChecked()) {
		laneLayoutList.get(v.getId() - 200).getScrollFrame().removeAllViews();
		stopwatchThreadList.append(v.getId() - 200,
			new StopwatchThread(
				// @formatter off
				laneLayoutList.get(v.getId() - 200).getTextChronometer(),
				laneLayoutList.get(v.getId() - 200).getTextBestLap(),
				laneLayoutList.get(v.getId() - 200).getTextWorstLap(),
				laneLayoutList.get(v.getId() - 200).getTextAverage(),
				laneLayoutList.get(v.getId() - 200).getScrollFrame(),
				laneLayoutList.get(v.getId() - 200).getId(), this));
		// @formatter on
		stopwatchThreadList.get(v.getId() - 200).start();
	    } else {
		TextView splitTime = new TextView(this);
		stopwatchThreadList.get(v.getId() - 200).stopWork(splitTime);
	    }
	    break;
	case 401:
	    if (stopwatchThreadList.get(v.getId() - 400).isRunning()) {
		TextView splitTime = new TextView(this);
		stopwatchThreadList.get(v.getId() - 400).splitTime(splitTime);
	    }
	    break;
	default:
	    break;
	}
    }
}
