package com.example.finalproject5;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class uaAdapter extends RecyclerView.Adapter<uaAdapter.MyViewHolder> {

    //Private Var for list of items
    private List<ItemModel> listItems;
    private Context context;

    //Var for sending extras
    String sentCourseTitle;

    //Constructor for item list
    public uaAdapter (List<ItemModel> itemList, Context context) {
        this.listItems = itemList;
        this.context = context;
    }

    //Creates new Views
    @NonNull
    @Override
    public uaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create a view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    //After ViewHolder is created, binds data to View
    @Override
    public void onBindViewHolder(@NonNull uaAdapter.MyViewHolder holder, int position) {
        //Current
        ItemModel currentList = listItems.get(position);
        final int pos = holder.getAdapterPosition();

        //Set Text to Views
        holder.tvClassName.setText(currentList.getClassName());
        holder.tvClassInstructor.setText(currentList.getClassInstructor());

        //Set on click listener for button clicks
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Make intent for moving screens
                final Intent intent;

                //Switch Case Testing
                switch (pos) {
                    default:
                        //Create temp item and assign the var course title
                        ItemModel currentItem = listItems.get(pos);
                        sentCourseTitle = currentItem.getClassName();

                        //Change to Cass's Course Page
                        intent = new Intent(context, TestCourseAct.class);

                        //Give Extras
                        intent.putExtra("sentCourse", sentCourseTitle);
                        break;
                }

                //Switches screen
                context.startActivity(intent);
            }
        });
    }

    //Get amount of elements in list
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    //Custom ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        //Two Fields in item
        public TextView tvClassName;
        public TextView tvClassInstructor;
        public LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            //Connect textfields within items themselves
            tvClassName = (TextView) itemView.findViewById(R.id.textViewClass);
            tvClassInstructor = (TextView) itemView.findViewById(R.id.textViewInstructor);

            //Connect LinearLayout for button presses
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutID);
        }
    }
}
