package com.example.toeicexamapplication.reading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeicexamapplication.R;

import java.util.List;

public class ReadingAdapter extends RecyclerView.Adapter {
    private List mReading;
    private Context mContext;

    public ReadingAdapter(List reading, Context mContext) {
        this.mReading = reading;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Chọn layout
        View itemReadingView = inflater.inflate(R.layout.item_quiz, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemReadingView);

        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reading reading = (Reading) mReading.get(position);
        holder.id_Question.setText(reading.getID_Reading());
        holder.question.setText(reading.getQuestion());
        holder.a.setText(reading.getDa_A());
        holder.b.setText(reading.getDa_B());
        holder.c.setText(reading.getDa_C());
        holder.d.setText(reading.getDa_D());
    }

    public int getItemCount() {
        return mReading.size();
    }
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView id_Question, question;
        public RadioButton a, b, c, d;
        public Button cancel, confirm;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            id_Question = itemView.findViewById(R.id.tV_idQuestion);
            question = itemView.findViewById(R.id.tV_question);
            a = itemView.findViewById(R.id.op1);
            b = itemView.findViewById(R.id.op2);
            c = itemView.findViewById(R.id.op3);
            d = itemView.findViewById(R.id.op4);
            cancel = itemView.findViewById(R.id.btnCancel);
            confirm = itemView.findViewById(R.id.btnconfirm);

            //Xử lý khi nút confirm  được bấm
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }


    }
}
