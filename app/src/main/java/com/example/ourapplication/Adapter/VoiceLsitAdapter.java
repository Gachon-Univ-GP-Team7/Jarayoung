package com.example.ourapplication.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication.Data.Test.TestList;
import com.example.ourapplication.MainActivity;
import com.example.ourapplication.R;
import com.example.ourapplication.ResultActivity;

import java.util.List;

public class VoiceLsitAdapter extends RecyclerView.Adapter<VoiceLsitAdapter.ViewHolder> {

    private List<TestList> dataSet;
    private MainActivity context;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private Button button1;
        private Button button2;
        private Button button3;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            button1 = itemView.findViewById(R.id.voiceElement1);
            button2 = itemView.findViewById(R.id.voiceElement2);
            button3 = itemView.findViewById(R.id.voiceElement3);
        }

        public Button getButton1(){
            return button1;
        }
        public Button getButton2(){
            return button2;
        }
        public Button getButton3(){
            return button3;
        }



    }

    public VoiceLsitAdapter(MainActivity context, List<TestList> dataSet){
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public VoiceLsitAdapter.ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.voice_recycler, parent, false);

        VoiceLsitAdapter.ViewHolder viewHolder = new VoiceLsitAdapter.ViewHolder(view);

        viewHolder.button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                int position = viewHolder.getAdapterPosition() * 3;

                int testIdx = dataSet.get(position).getTestIdx();

                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("testMode", "VOICE");
                intent.putExtra("testIdx", testIdx);
                Log.d("Recycler pos", Integer.toString(position));
                Log.d("Recycler idx", Integer.toString(testIdx));
                context.startActivity(intent);

            }
        });

        viewHolder.button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                int position = viewHolder.getAdapterPosition() * 3 + 1;

                int testIdx = dataSet.get(position).getTestIdx();

                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("testMode", "VOICE");
                intent.putExtra("testIdx", testIdx);
                Log.d("Recycler pos", Integer.toString(position));
                Log.d("Recycler idx", Integer.toString(testIdx));
                context.startActivity(intent);

            }
        });

        viewHolder.button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                int position = viewHolder.getAdapterPosition() * 3 + 2;

                int testIdx = dataSet.get(position).getTestIdx();

                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("testMode", "VOICE");
                intent.putExtra("testIdx", testIdx);
                Log.d("Recycler pos", Integer.toString(position));
                Log.d("Recycler idx", Integer.toString(testIdx));
                context.startActivity(intent);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VoiceLsitAdapter.ViewHolder holder, int position) {
        String text = dataSet.get(position * 3).getTestDate().split(" ")[0];
        holder.button1.setText(text);

        if(position + 1 < dataSet.size()) {
            text = dataSet.get(position * 3 + 1).getTestDate().split(" ")[0];
            Log.d("Recycler", text);
            holder.button2.setText(text);
        }
        else
            holder.button2.setVisibility(View.GONE);

        if(position + 2 < dataSet.size()) {
            text = dataSet.get(position * 3 + 2).getTestDate().split(" ")[0];
            holder.button3.setText(text);
        }
        else
            holder.button3.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return dataSet.size() / 3 + 1;
    }




}
