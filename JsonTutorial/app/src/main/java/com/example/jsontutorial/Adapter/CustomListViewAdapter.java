package com.indies.kayoner.adapter;

/**
**
Adapter to display list view of tickets
**
**/

import java.util.ArrayList;

import com.indies.kayoner.R;
import com.indies.kayoner.support.Ticket;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListViewAdapter extends BaseAdapter 
{
	Context context;
	ArrayList<Ticket> tickets;
	@SuppressWarnings("unchecked")
	public CustomListViewAdapter(Context context, ArrayList<? extends Parcelable> tickets)
	{
		this.context = context;
		this.tickets = (ArrayList<Ticket>) tickets;
	}

	/* private view holder class */
	private class ViewHolder
	{
		TextView txtTitle;
		TextView txtId;
		TextView txtdate;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.list_items, null);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) convertView.findViewById(R.id.subj);
			holder.txtId = (TextView) convertView.findViewById(R.id.id);
			holder.txtdate = (TextView) convertView.findViewById(R.id.date);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txtTitle.setTextColor(Color.parseColor("#000000"));
		Typeface face = Typeface.createFromAsset(context.getAssets(),"fonts/Segoe UI Semibold.ttf");
		holder.txtTitle.setTypeface(face);
		holder.txtTitle.setText(tickets.get(position).getSubject());
		holder.txtId.setTextColor(Color.parseColor("#203d4d"));
		holder.txtId.setText(" "+ tickets.get(position).getTicket_id());
		holder.txtdate.setTextColor(Color.parseColor("#000000"));
		holder.txtdate.setText(tickets.get(position).getLastChange());
		return convertView;
	}

	@Override
	public int getCount() 
	{
		return tickets.size();
	}

	@Override
	public Object getItem(int arg0) 
	{
		return tickets.get(arg0);
	}

	@Override
	public long getItemId(int arg0) 
	{
		return 0;
	}
}
