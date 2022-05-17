package com.example.sql_lite_app.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_lite_app.R;
import com.example.sql_lite_app.UpdateActivity;
import com.example.sql_lite_app.sqlLite.MyDataHelper;
import com.example.sql_lite_app.sqlLite.models.Notes;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private ArrayList<Notes> notes;
    private Context context;
    private Activity activity;
    private MyDataHelper dbHelper;

    public NotesAdapter(Context context, Activity activity, MyDataHelper dbHelper) {
        this.notes = new ArrayList<>();
        this.context = context;
        this.activity = activity;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.tagView.setText(notes.get(position).getTag());
        holder.titleView.setText(notes.get(position).getTitle());
        holder.dateView.setText(notes.get(position).getCreationDate().split("GMT")[0]);
        holder.idView.setText(String.valueOf(notes.get(position).get_id()));

        holder.listLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(notes.get(holder.getAdapterPosition()).get_id()));
                intent.putExtra("title", String.valueOf(notes.get(holder.getAdapterPosition()).getTitle()));
                intent.putExtra("tag", String.valueOf(notes.get(holder.getAdapterPosition()).getTag()));
                intent.putExtra("description", String.valueOf(notes.get(holder.getAdapterPosition()).getDescription()));
                activity.startActivityForResult(intent, 1);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteNote(notes.get(holder.getAdapterPosition()).get_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public ArrayList<Notes> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Notes> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //ui elements variables
        private TextView tagView;
        private TextView titleView;
        private TextView dateView;
        private TextView idView;
        private ImageButton deleteButton;
        private LinearLayout listLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialize ui elements
            this.tagView = itemView.findViewById(R.id.tagF);
            this.titleView = itemView.findViewById(R.id.titleF);
            this.dateView = itemView.findViewById(R.id.dateF);
            this.idView = itemView.findViewById(R.id.numF);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
            this.listLinearLayout = itemView.findViewById(R.id.listLayout);
        }
    }
}
