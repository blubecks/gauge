package it.loadingthesis.gauge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
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
		path = new Path();
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(0xff33ccff);//Color for background
		paint.setStyle(Style.FILL);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		//		setMinimumHeight(300);
		//		setMinimumWidth(300);
		//		setMeasuredDimension(500, 500);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int w = this.getWidth();
		int h = this.getHeight();
		canvas.translate(w/2,h/2);
		float r = Math.min(w, h)*0.48f;
		paint.setShader(new LinearGradient(0, r, 0, -r, Color.GRAY, Color.LTGRAY, TileMode.CLAMP));
		canvas.drawCircle(0, 0, r, paint);
		paint.setShader(new LinearGradient(0, r, 0, -r, Color.LTGRAY, Color.GRAY, TileMode.CLAMP));
		canvas.drawCircle(0, 0, r*0.95f, paint);
		paint.setShader(new LinearGradient(0, r, 0, -r, Color.BLACK, Color.GRAY, TileMode.CLAMP));
		canvas.drawCircle(0, 0, r*0.93f, paint);
		
		paint.setShader(new LinearGradient(-r,r,r,0,				
				new int[]{Color.GREEN,Color.YELLOW,Color.RED}, null, TileMode.CLAMP));
		
		Log.d("x 0",(float) Math.cos(4/3*Math.PI)*r+"");
		Log.d("y 1",(float) Math.sin(2/4*Math.PI)*(-r)+"");
		Log.d("x 1",(float) Math.cos(2/4*Math.PI)*(r)+"");
		Log.d("y 0",(float) Math.sin(4/3*Math.PI)*(-r)+"");
		r = r*0.85f;
		if(path.isEmpty()){
			for (int i = 0; i <= 124; i++) {
				float a = (float)(Math.PI*i/100);
				float x = (float)(Math.cos(a)*r);
				float y = (float)(Math.sin(a)*(-r));
				if(i==0) path.moveTo(x, y);
				else path.lineTo(x, y);
			}
			
			r = r*0.90f;
			for (int i = 124; i >= 0; i--) {
				float a = (float)(Math.PI*i/100);
				float x = (float)(Math.cos(a)*r);
				float y = (float)(Math.sin(a)*(-r));
				if(i==123) path.lineTo(x, y);
				else path.lineTo(x, y);
			}
			path.close();
		}


		canvas.drawPath(path, paint);




	}
}
