package it.loadingthesis.gauge;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.SeekBar;


public class MainActivity extends Activity {

	//private ImageView imageView;
	private Gauge g;
	private float delta = 0;
	private SeekBar seek;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		g = (Gauge) findViewById(R.id.gauge);
		seek = (SeekBar) findViewById(R.id.seek);

		seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				g.setDegres(progress*2.25f);

			}
		});


		final Button inc = (Button)findViewById(R.id.increase);
		final Button dec = (Button)findViewById(R.id.decrese);
		inc.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_UP)
					Log.i("", "Long press!");

				pressDegree();
				return false;
			}
		});



	}

	private void pressDegree() {
		if(delta !=225){
			if (delta!=220)
				delta+=10;
			else delta+=5;
		}
		seek.setProgress((int) (delta/2.25));
		g.setDegres(delta);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
