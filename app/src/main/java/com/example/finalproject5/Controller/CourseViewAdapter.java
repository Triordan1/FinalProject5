package com.example.finalproject5.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject5.Model.Assignment.Assignment;
import com.example.finalproject5.R;
import com.example.finalproject5.View.AssignmentDetails;

import java.util.List;

/** This class is the adapter used to display our assignments for each course. */

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.ViewHolder> {

    private List<Assignment> mCourseAssignments;
    private Context mContext;
    private String user;

    public CourseViewAdapter(Context context, List<Assignment> assignments, String user) {
        this.mContext = context;
        this.mCourseAssignments = assignments;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.item_assignment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Assignment assignment = mCourseAssignments.get(position);
        holder.tvCourseName.setText(assignment.getAssignmentName());
        holder.tvCategory.setText(assignment.getCategoryID());
        holder.tvGrade.setText(String.valueOf(assignment.getEarnedScore()));

        holder.tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AssignmentDetails.class);
                int id = assignment.getAssignmentID();
                intent.putExtra("Assignment", id);
                intent.putExtra("Course", assignment.getCourseID());
                intent.putExtra("LoggedInUser", user);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCourseAssignments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCourseName;
        private TextView tvCategory;
        private TextView tvGrade;
        private TextView tvDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCourseName = itemView.findViewById(R.id.tvAssignment);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvGrade = itemView.findViewById(R.id.tvGrade);
            tvDetails = itemView.findViewById(R.id.tvDetails);
        }
    }


}
