package com.example.machvan;
/* Developed by Roee Weisbert (C) March, 9 2024
   You are eligible to use this app on your responsibility
   You are permitted to distribute and use this code
   for any learning\teaching purposes you see fit
   Share the credit - don't take it to yourself!
*/
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView lv;
    SubjectAdapter myAdapter;
    List subjects;
    Spinner spinner;
    HashMap<RecommendationItem,Boolean> criteria;
    int saif6,saif7,saif9,saif10;
    boolean saif7IsRemote;
    TextView recommendation;
    Button clearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillInCriteriaLogic();
        lv = findViewById(R.id.sbjctlistview);
        recommendation=findViewById(R.id.recom);
        initializeAll();
        spinner=findViewById(R.id.myspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner_values, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        clearAll=findViewById(R.id.clear);
        clearAll.setOnClickListener(clicked -> {initializeAll();});
    }

    private void initializeAll() {
        subjects = new ArrayList<Subject>();
        myAdapter = new SubjectAdapter(this,0,0,subjects);
        recommendation.setBackgroundColor(Color.parseColor("#FFEB3B"));
        recommendation.setText("יש לבחור נושא מסעיף 6"+"\n"+"יש לבחור נושא מסעיף 7");
        saif6=0;
        saif7=0;
        saif9=0;
        saif10=0;
        saif7IsRemote=false;
        lv.setAdapter(myAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Subject toConsider = new Subject(parent.getItemAtPosition(position).toString());
        if (selectedItem.equals("+ הוספת נושא") || subjects.contains(toConsider)) {
            spinner.setSelection(0);
        } else {
            subjects.add(toConsider);
            trackTypeCount(toConsider);
            RecommendationItem recommendationItemToCheck = new RecommendationItem(saif6,saif9,saif10,saif7IsRemote);
            String message="";
            message= saif6==0 ? "יש לבחור נושא מסעיף 6" : "";
            message+= saif7==0 ? "\n"+"יש לבחור נושא מסעיף 7" : "";
            if (message.equals(""))
                message="מחכה לבחירה נוספת\nלא עומד בקריטריון";
            boolean answer = isCriteriaMet(recommendationItemToCheck,saif7);
            if (answer) {
                message = "עומד בקריטריון!";
                recommendation.setBackgroundColor(Color.parseColor("#00FF00"));
            }
            recommendation.setText(message);
            lv.setAdapter(myAdapter);
            spinner.setSelection(0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void fillInCriteriaLogic(){
        criteria = new HashMap<>();
        criteria.put(new RecommendationItem(0,-1,-1,false),false);
        criteria.put(new RecommendationItem(0,-1,-1,true),false);
        criteria.put(new RecommendationItem(1,0,-1,false),false);
        criteria.put(new RecommendationItem(1,1,1,false),false);
        criteria.put(new RecommendationItem(1,1,2,false),true);
        criteria.put(new RecommendationItem(1,2,-1,false),true);
        criteria.put(new RecommendationItem(1,0,0,true),false);
        criteria.put(new RecommendationItem(1,0,1,true),false);
        criteria.put(new RecommendationItem(1,-1,2,true),true);
        criteria.put(new RecommendationItem(1,1,-1,true),true);
        criteria.put(new RecommendationItem(2,0,-1,false),false);
        criteria.put(new RecommendationItem(2,1,-1,false),true);
        criteria.put(new RecommendationItem(2,2,-1,false),true);
        criteria.put(new RecommendationItem(2,-1,-1,true),true);
    }

    private boolean isCriteriaMet(RecommendationItem rec,int saif7) {
        boolean isSaif6ADontCare;
        boolean isSaif9ADontCare;
        boolean isSaif10ADontCare;
        boolean checked;
        for (Map.Entry<RecommendationItem, Boolean> entry : criteria.entrySet()) {
            RecommendationItem key = entry.getKey();
            isSaif6ADontCare = ((rec.getSaif6() + key.getSaif6()) < 0 || (rec.getSaif6() * key.getSaif6()) < 0);
            isSaif9ADontCare = ((rec.getSaif9() + key.getSaif9()) < 0 || (rec.getSaif9() * key.getSaif9()) < 0);
            isSaif10ADontCare = ((rec.getSaif10() + key.getSaif10()) < 0 || (rec.getSaif10() * key.getSaif10()) < 0);
            checked = ((rec.getSaif6() == key.getSaif6() || isSaif6ADontCare) && (rec.getSaif9() == key.getSaif9() || isSaif9ADontCare) && (rec.getSaif10() == key.getSaif10() || isSaif10ADontCare) && ((rec.isSaif7IsRemote() == key.isSaif7IsRemote()) && saif7>0));
            if (checked)
                return entry.getValue();;
        }
        return false;
    }

    private void trackTypeCount(Subject subject){
        if (subject.getSubjectType()==6){
            saif6+=1;
        }else if (subject.getSubjectType()==7) {
            saif7IsRemote = subject.getName().contains("Firestore");
            saif7+=1;
        }else if (subject.getSubjectType()==9){
            saif9+=1;
        }else if (subject.getSubjectType()==10){
            saif10+=1;
        }
    }
}