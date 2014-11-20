package it.loadingthesis.gauge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class Gauge extends View {

	private Paint paint;
	private ImageView imageView;
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

		imageView = (ImageView)findViewById(R.id.imageView);
		imageView.setDrawingCacheEnabled(true);
		paint = new Paint();
		paint.setColor(0x33ccff);//Color for background
		paint.setStyle(Style.FILL);

		path = new Path();

		setMinimumWidth(100);
		setMinimumHeight(100);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(path.isEmpty()){
			int w = canvas.getWidth();
			int h = canvas.getHeight();
			for (int i = 0; i < 100; i++) {
				float a = (float)(Math.PI*2*i/100);
				float x = (float)(Math.cos(a)*w/3+w/2);
				float y = (float)(Math.sin(2*a)*h/3+h/2);
				if(i==0) path.moveTo(x, y);
				else path.lineTo(x, y);
			}
			path.close();
		}
		canvas.drawPath(path, paint);
		imageView.draw(canvas);
	}

}
