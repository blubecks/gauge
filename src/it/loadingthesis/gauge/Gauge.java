package it.loadingthesis.gauge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Gauge extends View {

	private Paint paint;
	private ImageView imageView;
	private Path path;

	public Gauge(Context context) {
		this(context,null,0);
		Log.d("constr","1");
		//Calling constructor below
	}

	public Gauge(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		Log.d("constr","2");
		//Calling constructor below
	}

	public Gauge(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		Log.d("constr","3");
		paint = new Paint();
		paint.setColor(0xff33ccff);//Color for background
		paint.setStyle(Style.FILL);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		setMinimumHeight(300);
		setMinimumWidth(300);
		setMeasuredDimension(500, 500);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Integer w = canvas.getWidth();
		Integer h = canvas.getHeight();
		Log.d("heigth",h.toString());
		Log.d("width",w.toString());
		canvas.drawRect(0, 0, w, h, paint);
	}

}
