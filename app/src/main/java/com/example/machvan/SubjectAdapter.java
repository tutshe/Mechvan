package com.example.machvan;
/* Developed by Roee Weisbert (C) March, 9 2024
   You are eligible to use this app on your responsibility
   You are permitted to distribute and use this code
   for any learning\teaching purposes you see fit
   Share the credit - don't take it to yourself!
*/
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectAdapter extends ArrayAdapter<Subject> {

    List<Subject> subjectsList;
    Context con;
    String[] resources = new String[]{"","","","","","","six","seven","","nine","ten"};

    public SubjectAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Subject> objects) {
        super(context, resource, textViewResourceId, objects);
        this.subjectsList = objects;
        this.con = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) con).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.one_subject_item, parent, false);
        ImageView picture = view.findViewById(R.id.cubicaltypeimage);
        TextView name = view.findViewById(R.id.subjectname);
        TextView isAdvanced = view.findViewById(R.id.isadvanced);
        Subject currentSubject = subjectsList.get(position);
        int resourceId = con.getResources().getIdentifier(getCubicalNum(currentSubject.getName()), "drawable", con.getPackageName());
        picture.setImageResource(resourceId);
        name.setText(currentSubject.getName().substring(currentSubject.getName().indexOf("-")+1));
        isAdvanced.setText(currentSubject.isAdvanced() ? "נושא מתקדם" : "נושא רגיל");
        return view;
    }

    public String getCubicalNum(String subject) {
        return resources[Integer.valueOf(subject.split("-")[0])];
    }
}
