package com.example.jsontutorial.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jsontutorial.Model.DataUtil;
import com.example.jsontutorial.R;

import java.util.ArrayList;

/**
 * Created by Rushit on 1/17/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DataUtil> dataList;

    public RecyclerAdapter(Context context, ArrayList<DataUtil> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_layout,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.txtPath.setText(dataList.get(i).getPath().toString());
        viewHolder.txtCategory.setText(dataList.get(i).getCategory().toString());
        viewHolder.txtBody.setText(dataList.get(i).getBody().toString());
        viewHolder.txtType.setText(dataList.get(i).getType().toString());
        viewHolder.txtUrlTitle.setText(dataList.get(i).getUrl_title().toString());
        viewHolder.txtUrl.setText(dataList.get(i).getUrl().toString());
        viewHolder.txtUid.setText(dataList.get(i).getUid().toString());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView txtCategory, txtUid, txtBody , txtUrl, txtUrlTitle, txtType, txtPath;
        public ViewHolder(View itemView) {
            super(itemView);
            txtCategory = (TextView) itemView.findViewById(R.id.txtCategory);
            txtUid = (TextView) itemView.findViewById(R.id.txtUid);
            txtBody = (TextView) itemView.findViewById(R.id.txtBody);
            txtUrl = (TextView) itemView.findViewById(R.id.txtUrl);
            txtUrlTitle = (TextView) itemView.findViewById(R.id.txtUrlTitle);
            txtType = (TextView) itemView.findViewById(R.id.txtType);
            txtPath = (TextView) itemView.findViewById(R.id.txtPath);
        }
    }
}
