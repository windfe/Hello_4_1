package com.example.administrator.repair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

//这和hell_4 的视频教学代码，有些不同，
//自己的想法，可能不严谨，实现的效果是相同的

public class MainActivity extends AppCompatActivity {
    private HashMap<String,String> dictionary;
    private MainActivity myActivity;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActivity=this;

        lookup();
        pick6Defitions();

    }


private void pick6Defitions(){
    final ArrayList<String> choseWords=new ArrayList<>(this.dictionary.keySet());
    final ArrayList<String> defitions=new ArrayList<>();

    Collections.shuffle(choseWords);


    for (int i = 0; i <6 ; i++) {
        String defn=this.dictionary.get(choseWords.get(i));
        defitions.add(defn);
    }

    final String  word=choseWords.get(0);
    Collections.shuffle(choseWords);
    TextView textView=findViewById(R.id.textView);
    textView.setText(word);

    final ArrayAdapter myAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,defitions);
    final ListView listView=findViewById(R.id.listView);
    listView.setAdapter(myAdapter);


    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            String onchickItem=listView.getItemAtPosition(position).toString();
            String correctDefn=dictionary.get(word);

            if (onchickItem.equals(correctDefn)) {
                Toast.makeText(myActivity,"正确",Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(myActivity,"错误",Toast.LENGTH_SHORT).show();

            }
            pick6Defitions();
            TextView textView=findViewById(R.id.textView);
            textView.setText(word);
        }
    });


}
private void lookup(){
    if (dictionary==null){
        this.dictionary=new HashMap<>();
    }
    Scanner scan=new Scanner(getResources().openRawResource(R.raw.dictionary));
    while(scan.hasNext()){
        String line=scan.nextLine();
        String piece[]=line.split(":");
        this.dictionary.put(piece[0],piece[1]);
    }

}

}
