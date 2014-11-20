package it.loadingthesis.gauge;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class Gauge extends View {

	private Paint paint;
	private Path path;

	public Gauge(Context context) {
		super(context,null, 0);
		//Calling constructor below
	}

	public Gauge(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		//Calling constructor below
	}

	public Gauge(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

}
