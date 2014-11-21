package it.loadingthesis.gauge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.graphics.Shader.TileMode;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class Gauge extends View {

	private Paint paint;
	private Path path;
	private float degres = 0;

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
		this.setLayerType(LAYER_TYPE_SOFTWARE, null);
		path = new Path();
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Style.FILL);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

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
			canvas.drawPath(path, paint);
		}
		path.rewind();
		paint.setShader(null);
		paint.setColor(Color.WHITE);
		if (path.isEmpty()){

			r=r*0.1f;

			//degree
			canvas.drawPath(path, paint);
			path.rewind();
			for (int i=0; i<=10; i++) {
				double d=-i*Math.PI/180*22.5;
				double c1=Math.cos(d);
				double s1=Math.sin(d);
				path.moveTo((float)(c1*r*10),(float)(s1*r*10));
				path.lineTo((float)(c1*r*0.9*10),(float)(s1*r*0.9*10));
			}
			paint.setStyle(Style.STROKE);
			paint.setShader(null);
			paint.setColor(0xffffffff);
			paint.setStrokeWidth(5);
			canvas.drawPath(path, paint);
			
			//needle
			path.rewind();
			paint.setStyle(Style.FILL);
			path.addCircle(0, 0, r, Direction.CCW);
			path.lineTo(0, -r*0.75f);
			path.lineTo(-w/5,w/5f);
			path.lineTo(0, r*0.75f);

			canvas.rotate(degres);
			paint.setShadowLayer(4, -4, 4, 0x80000000);
			canvas.drawPath(path, paint);
			path.reset();
			paint.setShadowLayer(0, 0, 0, 0);
		}

	}

	public void setDegres(float v){
		this.degres = v;
		invalidate();
	}
}
