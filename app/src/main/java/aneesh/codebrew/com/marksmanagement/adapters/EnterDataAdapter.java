package aneesh.codebrew.com.marksmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import aneesh.codebrew.com.marksmanagement.R;

public class EnterDataAdapter extends RecyclerView.Adapter<EnterDataAdapter.ViewHolder>
{
    List<String> subjectList;
    List<String> marksList;
    Context context;

    public EnterDataAdapter(Context context, List<String> subjectList, List<String> marksList)
    {
        this.context = context;
        this.subjectList = subjectList;
        this.marksList = marksList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.enterdata_recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.tvSubject.setText(subjectList.get(position));
        holder.tvMarks.setText(marksList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return subjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvSubject,tvMarks;
        public ViewHolder(View itemView)
        {
            super(itemView);
            tvSubject = (TextView) itemView.findViewById(R.id.tvSubject);
            tvMarks = (TextView) itemView.findViewById(R.id.tvMarks);
        }
    }
}
