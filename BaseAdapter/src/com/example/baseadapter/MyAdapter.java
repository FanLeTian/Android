package com.example.baseadapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{

	private List<ItemBean> list;
	private LayoutInflater mInflater;
	private long sumTime;
	public MyAdapter(Context context,List<ItemBean> list) {
		// TODO Auto-generated constructor stub
		this.mInflater=LayoutInflater.from(context);
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		View view =mInflater.inflate(R.layout.item,null);
//		ImageView image=(ImageView) view.findViewById(R.id.iv_image);
//		TextView titleText=(TextView) view.findViewById(R.id.tv_title);
//		TextView contentText=(TextView) view.findViewById(R.id.tv_content);
//		ItemBean bean=list.get(position);
//		image.setImageResource(bean.ItemimageResId);
//		titleText.setText(bean.Itemtitle);
//		contentText.setText(bean.ItemContent);
//		return view;
		
//		if(convertView==null)
//		{
//			convertView=mInflater.inflate(R.layout.item,null);
//		}
//		ImageView image=(ImageView) convertView.findViewById(R.id.iv_image);
//		TextView titleText=(TextView) convertView.findViewById(R.id.tv_title);
//		TextView contentText=(TextView) convertView.findViewById(R.id.tv_content);
//		ItemBean bean=list.get(position);
//		image.setImageResource(bean.ItemimageResId);
//		titleText.setText(bean.Itemtitle);
//		contentText.setText(bean.ItemContent);
//		return convertView;
		
		long start=System.nanoTime();
		ViewHolder viewHolder;
		if(convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView=mInflater.inflate(R.layout.item,null);
			viewHolder.iamgeView=(ImageView) convertView.findViewById(R.id.iv_image);
			viewHolder.title=(TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.content=(TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		ItemBean bean=list.get(position);
		viewHolder.iamgeView.setImageResource(bean.ItemimageResId);
		viewHolder.title.setText(bean.Itemtitle);
		viewHolder.content.setText(bean.ItemContent);
		long end=System.nanoTime();
		long dValue=end-start;
		sumTime+=dValue;
		Log.d("Main",String.valueOf(sumTime));
		return convertView;
		
		
		
	}
	class ViewHolder
	{
		public ImageView iamgeView;
		public TextView title;
		public TextView content;
	}

}

