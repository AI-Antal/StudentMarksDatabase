package aneesh.codebrew.com.marksmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import aneesh.codebrew.com.marksmanagement.model.DataModel;
import aneesh.codebrew.com.marksmanagement.R;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.ViewHolder>
{
    Context context;
    List<DataModel> dbData;

    public SubjectListAdapter(Context context, List<DataModel> dbData)
    {
        this.context = context;
        this.dbData = dbData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        int english = (dbData.get(position).mMarks.get("English"));
        int maths = (dbData.get(position).mMarks.get("Maths"));
        int hindi = (dbData.get(position).mMarks.get("Hindi"));
        int physics = (dbData.get(position).mMarks.get("Physics"));
        int chemistry = (dbData.get(position).mMarks.get("Chemistry"));

        holder.rvName.setText(String.valueOf(dbData.get(position).mName));
        holder.rvStream.setText(String.valueOf(dbData.get(position).mStream));
        holder.rvEnglish.setText(String.valueOf(english));
        holder.rvMaths.setText(String.valueOf(maths));
        holder.rvHindi.setText(String.valueOf(hindi));
        holder.rvPhysics.setText(String.valueOf(physics));
        holder.rvChemistry.setText(String.valueOf(chemistry));
        holder.rvTotal.setText(String.valueOf(english + maths + hindi + physics + chemistry));
    }

    @Override
    public int getItemCount()
    {
        return dbData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView rvName, rvStream, rvEnglish, rvHindi, rvMaths, rvPhysics, rvChemistry, rvTotal;

        public ViewHolder(View itemView)
        {
            super(itemView);
            rvEnglish = (TextView) itemView.findViewById(R.id.rvEnglish);
            rvHindi = (TextView) itemView.findViewById(R.id.rvHindi);
            rvMaths = (TextView) itemView.findViewById(R.id.rvMaths);
            rvPhysics = (TextView) itemView.findViewById(R.id.rvPhysics);
            rvChemistry = (TextView) itemView.findViewById(R.id.rvChemistry);
            rvName = (TextView) itemView.findViewById(R.id.rvName);
            rvStream = (TextView) itemView.findViewById(R.id.rvStream);
            rvTotal = (TextView) itemView.findViewById(R.id.rvTotal);
        }
    }
}
