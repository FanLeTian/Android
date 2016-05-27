package com.example.catchcat;

import java.util.HashMap;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class Playground extends SurfaceView implements OnTouchListener {

	int k = 1;
	private static int WIDTH = 40;
	private static final int COL = 10;
	private static final int ROW = 10;
	private static final int BLOCKS = 10;

	private Dot matrix [][];
	private Dot cat;
	
	public Playground(Context context) {
		super(context);
		getHolder().addCallback(callback);
		matrix = new Dot[ROW][COL];
		for(int i = 0; i < ROW; i++)
		{
			for(int j = 0; j < COL; j++)
			{
				matrix[i][j] = new Dot(j,i);
			}
		}
		setOnTouchListener(this);
		initGame();
		// TODO Auto-generated constructor stub
	}
	
	private Dot getDot(int x, int y)
	{
		return matrix[y][x];
	}
	
	private boolean isAtEdge(Dot d)
	{
		if(d.getX()*d.getY() == 0 || d.getX()+1 == COL || d.getY()+1 == ROW)
			return true;
			
		return false;
	}
	
	private Dot getNrighbour(Dot d, int dir)
	{
		switch (dir) {
		case 1:
			return getDot(d.getX()-1, d.getY());
		case 2:
			if(d.getY()%2 == 0)
			{
				return getDot(d.getX()-1, d.getY()-1);
			}else{
				return getDot(d.getX(), d.getY()-1);
			}
		case 3:
			if(d.getY()%2 == 0)
			{
				return getDot(d.getX(), d.getY()-1);
			}else{
				return getDot(d.getX()+1, d.getY()-1);
			}
		case 4:
			return getDot(d.getX()+1, d.getY());
		case 5:
			if(d.getY()%2 == 0)
			{
				return getDot(d.getX(), d.getY()+1);
			}else{
				return getDot(d.getX()+1, d.getY()+1);
			}
		case 6:
			if(d.getY()%2 == 0)
			{
				return getDot(d.getX()-1, d.getY()+1);
			}else{
				return getDot(d.getX(), d.getY()+1);
			}

		default:
			break;
		}
		return null;
		
	}
	
	private int getDistanse(Dot d, int dir)
	{
		int distanse = 0;
		Dot ori = d,next;
		if(isAtEdge(d)) {
			return 1;
		}
		while(true)
		{
			next = getNrighbour(ori, dir);
			if(next.getStatus() == Dot.STATUS_ON)
			{
				return distanse*-1;
			}
			if(isAtEdge(next))
			{
				distanse++;
				return distanse;
			}
			distanse++;
			ori = next;
			
		}
		
	}
	
	private void moveTo(Dot d)
	{
		d.setStatus(Dot.STATUS_IN);
		getDot(cat.getX(), cat.getY()).setStatus(Dot.STATUS_OFF);
		cat.setXY(d.getX(), d.getY());
	}
	
	private void move()
	{
		if(isAtEdge(cat)){
			lose();
			return;
		}
		Vector<Dot> avaliable = new Vector<Dot>();
		Vector<Dot> positive = new Vector<Dot>();
		HashMap<Dot, Integer> al = new HashMap<Dot, Integer>();
		for(int i=1 ; i < 7 ; i++)
		{
			Dot n = getNrighbour(cat, i);
			if(n.getStatus() == Dot.STATUS_OFF)
			{
				avaliable.add(n);
				al.put(n, i);
				if(getDistanse(n, i) > 0)
				{
					positive.add(n);

				}
			}
		}
		if(avaliable.size() == 0)
		{
			win();
		}else if(avaliable.size() == 1)
		{
			moveTo(avaliable.get(0));
		}else {
			Dot best = null;
			if(positive.size() != 0) {
				int min = 999;
				
				for(int i=0 ; i < positive.size() ; i++)
				{
					int a =getDistanse(positive.get(i), al.get(positive.get(i)));
					if(a < min)
					{
						min = a;
						best = positive.get(i);
					}
				}
				
			}else {
				int max = 0;
				for(int i = 0; i < avaliable.size() ;i++)
				{
					int k = getDistanse(avaliable.get(i), al.get(avaliable.get(i)));
					if(k < max)
					{
						max = k;
						best = avaliable.get(i);
					}
				}
			}
			moveTo(best);
		}
	}
	
	private void lose()
	{
		Toast.makeText(getContext(), "you lose!", Toast.LENGTH_SHORT).show();
	}
	
	private void win()
	{
		Toast.makeText(getContext(), "you win!", Toast.LENGTH_SHORT).show();

	}
	
	private void redraw()
	{
		Canvas c = getHolder().lockCanvas();
		c.drawColor(Color.LTGRAY);
		Paint paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		for(int i = 0; i < ROW; i++)
		{
			int offset = 0;
			if(i % 2 != 0)
			{
				offset = WIDTH/2;
			}
			for(int j = 0; j < COL; j++)
			{
				Dot one = getDot(j, i);
				switch (one.getStatus()) {
				case Dot.STATUS_OFF:
					paint.setColor(0xFFEEEEEE);
					break;
				case Dot.STATUS_ON:
					paint.setColor(0xFFFFAA00);
					break;
				case Dot.STATUS_IN:
					paint.setColor(0xFFFF0000);
					break;
					

				default:
					break;
				}
				c.drawOval(new RectF(one.getX()*WIDTH+offset, one.getY()*WIDTH, 
						(one.getX()+1)*WIDTH+offset, (one.getY()+1)*WIDTH), paint);
			}
		}
		getHolder().unlockCanvasAndPost(c);
	}
	
	Callback callback = new Callback() {
		
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			redraw();
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			WIDTH = width/(COL+1);
			redraw();
		}
	};
	
	private void initGame()
	{
		for(int i = 0; i < ROW; i++)
		{
			for(int j = 0; j < COL; j++)
			{
				matrix[i][j].setStatus(Dot.STATUS_OFF);
			}
		}
		cat = new Dot(4, 5);
		getDot(4, 5).setStatus(Dot.STATUS_IN);
		for(int i = 0; i<BLOCKS;)
		{
			int x = (int) ((Math.random()*1000)%COL);
			int y = (int) ((Math.random()*1000)%ROW);

			if(getDot(x, y).getStatus() == Dot.STATUS_OFF)
			{
				getDot(x, y).setStatus(Dot.STATUS_ON);
				i++;
				System.out.println("BLOCK="+i);
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
//			Toast.makeText(getContext(), event.getX()+" "+event.getY(), Toast.LENGTH_SHORT).show();
			int x,y;
			y = (int)(event.getY()/WIDTH);
			if(y % 2 == 0)
			{
				x = (int) (event.getX()/WIDTH);
			}else{
				x = (int) ((event.getX()-WIDTH/2)/WIDTH);

			}
			if(x+1 > COL || y+1 >ROW)
			{
				initGame();
//				for (int i = 1; i < 7; i++) {
//					System.out.println(i+"@"+getDistanse(cat, i));
//				}
//				getNrighbour(cat, k).setStatus(Dot.STATUS_IN);
//				k++;
//				redraw();
			}else if(getDot(x, y).getStatus() == Dot.STATUS_OFF)
			{
				getDot(x, y).setStatus(Dot.STATUS_ON);
				move();
			}
			redraw();
		}
		return true;
	}
	
	

}
