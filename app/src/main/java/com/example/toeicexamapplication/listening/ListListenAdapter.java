package com.example.toeicexamapplication.listening;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.toeicexamapplication.R;
import com.example.toeicexamapplication.reading.Reading;

import java.util.List;

public class ListListenAdapter extends ArrayAdapter {
    Context context;
    private static int resource;
    List<Listening> objects;
    public ListListenAdapter(@NonNull Context context, int resource, List<Listening> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, parent, false);
        ImageView image = view.findViewById(R.id.imgHinh);
        RadioButton op1 = view.findViewById(R.id.rdbA);
        RadioButton op2 = view.findViewById(R.id.rdbB);
        RadioButton op3 = view.findViewById(R.id.rdbC);
        RadioButton op4 = view.findViewById(R.id.rdbD);
        Listening topic = objects.get(position);
        image.setImageResource(R.drawable.lis_1);
        op1.setText(topic.getA());
        op2.setText(topic.getB());
        op3.setText(topic.getC());
        op4.setText(topic.getD());
        return view;
    }
}
