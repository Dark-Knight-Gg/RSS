package com.example.rss5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.AsynchronousByteChannel;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ImageView m2_imgBack;
    ListView m2_lv1;
    ArrayAdapter adapter;
    ArrayList<String> listTitle = new ArrayList<String>();
    ArrayList<String> listLink = new ArrayList<String>();
    private void AnhXa(){
        m2_imgBack=(ImageView) findViewById(R.id.m2_imgBack);
        m2_lv1=(ListView) findViewById(R.id.m2_lv1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AnhXa();
        m2_imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        new RSS5().execute("https://vnexpress.net/rss/giai-tri.rss");
        adapter = new ArrayAdapter(MainActivity2.this, android.R.layout.simple_list_item_1,listTitle);
        m2_lv1.setAdapter(adapter);
        m2_lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link = listLink.get(position);
                Intent intent2 = new Intent(MainActivity2.this,MainActivity3.class);
                intent2.putExtra("Link",link);
                startActivity(intent2);
            }
        });
    }
    private class RSS5 extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line="";
                while((line= bufferedReader.readLine())!=null){
                    content.append(line+"\n");
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            for(int i=0;i<nodeList.getLength();i++){
                Element element = (Element) nodeList.item(i);
                listTitle.add(parser.getValue(element,"title"));
                listLink.add(parser.getValue(element,"link"));
            }
            adapter.notifyDataSetChanged();
        }
    }
}