package aneesh.codebrew.com.marksmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import aneesh.codebrew.com.marksmanagement.model.DataModel;
import aneesh.codebrew.com.marksmanagement.R;

public class RollnoAdapter extends RecyclerView.Adapter<RollnoAdapter.ViewHolder>
{
    Context context;
    List<DataModel> dbData;
    public RollnoAdapter(Context context, List<DataModel> dbData)
    {
        this.context = context;
        this.dbData = dbData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.rollno_recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.tvRollno.setText(String.valueOf(dbData.get(position).mRollno));
    }

    @Override
    public int getItemCount()
    {
        return dbData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvRollno;
        public ViewHolder(View itemView)
        {
            super(itemView);
            tvRollno = (TextView) itemView.findViewById(R.id.tvRollno);
        }
    }
}
