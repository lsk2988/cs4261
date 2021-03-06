package com.example.shuangke.weproject2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ComputerScienceProjectList extends AppCompatActivity {
//    String[] TITLES ={"Computer Science Project1","Arts Project2","Arts Project3","Arts Project4","Arts Project5","Arts Project6","Arts Project7","Arts Project8","Arts Project9"};
//    String[] REWARD ={"1000$","2000$","3000$","4000$","5000$","6000$","7000$","8000$","9000$"};
//    String[] DATE ={"From 2016/4/6","From 2017/4/6","From 2018/4/6","From 2019/4/6","From 2020/4/6","From 2021/4/6","From 2022/4/6","From 2023/4/6","From 2024/4/6"};

    ArrayList<String> plist;
    ArrayList<String> TITLES;
    ArrayList<String> REWARD;
    ArrayList<String> Date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science_project_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Computer Science Project List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        plist = new ArrayList<>();
        TITLES = new ArrayList<String>();
//        TITLES.add("Business Project1");
//        TITLES.add("Business Project2");
        REWARD = new ArrayList<String>();
        ListView listView =(ListView)findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Intent intent = new Intent(view.getContext(),ComputerScienceProjectDetail.class);
                intent.putExtra("position",position);
                intent.putExtra("ptitle",TITLES.get(position));
                intent.putStringArrayListExtra("plist",plist);
                startActivityForResult(intent, position);

            }
        });

       new GetDataTask().execute("https://testfirebase-1fb45.firebaseio.com/categories/cs.json");

    }

    class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return TITLES.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.customlayoutprojectlist,null);
            TextView textView_title =(TextView)view.findViewById(R.id.project_title);
            TextView textView_reward =(TextView)view.findViewById(R.id.TextView_);
            TextView textView_date =(TextView)view.findViewById(R.id.date);
            textView_title.setText("Title:  "+TITLES.get(position));
            textView_reward.setText("Reward value:" + REWARD.get(position));
            textView_date.append(Date.get(position));
            return view;
        }
    }


    class GetDataTask extends AsyncTask<String,Void,String> {

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog = new ProgressDialog(ComputerScienceProjectList.this);
            progressDialog.setMessage("loading data...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return getData(params[0]);
            } catch (IOException ex) {
                return "network error!";
            } catch (JSONException ex) {
                return "Data Invalid";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //mResult.setText(result);
            if(progressDialog != null) {
                progressDialog.dismiss();
            }
        }


        private String getData(String urlPath) throws IOException, JSONException{
            StringBuilder result = new StringBuilder();
            BufferedReader bufferedReader = null;
            String out;
            try{
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    result.append(line);

                }
                out = result.toString();

            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }

            String[] oblist = out.split("&");
            //System.out.println(oblist[1] + "==================================");
            TITLES = parseobjects(oblist);
            REWARD = parseRewards(oblist);
            Date = parseDate(oblist);
            return out;
        }
    }

    private ArrayList<String> parseobjects(String[] oblist){


        String item;
        ArrayList<String> titles = new ArrayList<>();
        for(int i = 1; i < oblist.length;i++){
            item = oblist[i];
            int index = item.indexOf("title");
            int indexend = item.indexOf("}");
            titles.add(item.substring(index+8,indexend-1));
            int indexp = 0;
            int indexendp = item.indexOf(":");
            plist.add(item.substring(indexp,indexendp-1));

        }

        return titles;
    }

    private ArrayList<String> parseRewards(String[] oblist) {
        String item;
        ArrayList<String> rewards = new ArrayList<>();
        for(int i = 1; i < oblist.length;i++){
            item = oblist[i];
            int indexrew = item.indexOf("award");
            int end = item.indexOf("beginDate");
            String sub = item.substring(indexrew,end);
            int num = Integer.parseInt(sub.replaceAll("[\\D]", ""));
            rewards.add("  "+"$"+Integer.toString(num));

        }
        return rewards;

    }

    private ArrayList<String> parseDate(String[] oblist) {
        String item;
        ArrayList<String> dates = new ArrayList<>();
        for(int i = 1; i < oblist.length;i++){
            item = oblist[i];
            int indexrew = item.indexOf("beginDate");
            int end = item.indexOf("catagories");
            String fdate = item.substring(indexrew + 12,end - 3);
            int indexend = item.indexOf("endDate");
            int enddate = item.indexOf("members");
            String ldate = item.substring(indexend + 10, enddate - 3);
            dates.add(fdate + "   to   " + ldate);

        }
        return dates;
    }


}
