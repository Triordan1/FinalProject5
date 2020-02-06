package com.example.finalproject5.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject5.Model.Assignment.AssignmentDao;
import com.example.finalproject5.R;
import com.example.finalproject5.View.CourseView;

import java.util.List;

/** This class is the adapter used to display our assignments for each course. */

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.ViewHolder> {

    private Context mContext;
    private List<AssignmentDao> mCourseAssignments;
    private CourseView mCourseView;

    public CourseViewAdapter(Context context, List<AssignmentDao> assignments,
                             CourseView courseView) {
        this.mContext = context;
        this.mCourseAssignments = assignments;
        this.mCourseView = courseView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.
                from(mContext).inflate(R.layout.item_assignment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mCourseAssignments.get(position));
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

            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvGrade = itemView.findViewById(R.id.tvGrade);
            tvDetails = itemView.findViewById(R.id.tvDetails);
        }

        public void bind(AssignmentDao a) {

        }


    }

}
