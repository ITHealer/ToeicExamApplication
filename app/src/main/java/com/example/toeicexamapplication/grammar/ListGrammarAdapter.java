package com.example.toeicexamapplication.grammar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.toeicexamapplication.R;

import java.util.List;

public class ListGrammarAdapter extends ArrayAdapter {
    Context context;
    private static int resource;
    List<Tense> objects;
    public ListGrammarAdapter(@NonNull Context context, int resource, List<Tense> objects) {
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
        TextView tvTense = view.findViewById(R.id.tvTense);
        Tense tense = objects.get(position);
        tvTense.setText(tense.getTense_Name());
        return view;
    }
}
