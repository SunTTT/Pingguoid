package com.suntt.pingguoid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Result extends Activity {
    private String key = "a6e42254bd7dca0e8993177971951fc8";
    private String address;
    private String result;
    private List<Apple> list = new ArrayList<Apple>();
    private myAdapter adapter;
    private ListView listView;
    private TextView textView;
    private Apple appleExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.tvID);
        appleExample = new Apple();
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        textView.setText(id.toUpperCase());
        Log.e("json", id);
        address = new StringBuffer("http://apis.juhe.cn/appleinfo/index?sn=").append(id).append("&key=").append(key).toString();
        IdTask task = new IdTask();
        task.execute(address);
    }

    public class myAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Apple littleApple = new Apple();
            littleApple = list.get(i);
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(Result.this).inflate(R.layout.result_item, null);
                holder = new ViewHolder();
                holder.phone_model = (TextView) view.findViewById(R.id.phone_model);
                holder.imei_number = (TextView) view.findViewById(R.id.imei_number);
                holder.active = (TextView) view.findViewById(R.id.active);
                holder.warranty_status = (TextView) view.findViewById(R.id.warranty_status);
                holder.warranty = (TextView) view.findViewById(R.id.warranty);
                holder.tele_support = (TextView) view.findViewById(R.id.tele_support);
                holder.tele_support_status = (TextView) view.findViewById(R.id.telt_support_status);
                holder.made_area = (TextView) view.findViewById(R.id.made_area);
                holder.start_date = (TextView) view.findViewById(R.id.start_date);
                holder.end_date = (TextView) view.findViewById(R.id.end_date);
                holder.color = (TextView) view.findViewById(R.id.color);
                holder.size = (TextView) view.findViewById(R.id.size);
                view.setTag(holder);


            }
            else {
                holder = (ViewHolder) view.getTag();
            }
holder.phone_model.setText(littleApple.getPhone_model());
            holder.imei_number.setText(littleApple.getImei_number());
            holder.active.setText(littleApple.getActive());
            holder.warranty_status.setText(littleApple.getWarranty_status());
            holder.warranty.setText(littleApple.getWarranty());
            holder.tele_support.setText(littleApple.getTele_support());
            holder.tele_support_status.setText(littleApple.getTele_support_status());
            holder.made_area.setText(littleApple.getMade_area());
            holder.start_date.setText(littleApple.getStart_date());
            holder.end_date.setText(littleApple.getEnd_date());
            holder.color.setText(littleApple.getColor());
            holder.size.setText(littleApple.getSize());

            return view;
        }

        class ViewHolder {
            TextView phone_model;
            TextView imei_number;
            TextView active;
            TextView warranty_status;
            TextView warranty;
            TextView tele_support;
            TextView tele_support_status;
            TextView made_area;
            TextView start_date;
            TextView end_date;
            TextView color;
            TextView size;
        }
    }

    public class IdTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            result = HttpUtil.sendHttpRequest(address);

            return result;
        }


        @Override
        protected void onPostExecute(String s) {

            if (TextUtils.isEmpty(s)) {
                Toast.makeText(Result.this, "NO RESULT", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Result.this, "GET RESULT", Toast.LENGTH_LONG).show();
                Log.e("json", "GETRESULT");
                try {
                    JSONObject jsonresult = new JSONObject(s);
                    String resultcode = jsonresult.getString("resultcode");
                    Log.e("json", "GETCODE");
                    if (resultcode.equals("200")) {
                        Log.e("json", "panduancehnggong");
                        JSONObject apple = jsonresult.getJSONObject("result");
                        String phone_model = apple.getString("phone_model");
                        String serial_number = apple.getString("serial_number");
                        String imei_number = apple.getString("imei_number");
                        String active = apple.getString("active");
                        String warranty_status = apple.getString("warranty_status");
                        String warranty = apple.getString("warranty");

                        String tele_support = apple.getString("tele_support");
                        String tele_support_status = apple.getString("tele_support_status");

                        String made_area = apple.getString("made_area");
                        String start_date = apple.getString("start_date");
                        String end_date = apple.getString("end_date");
                        String color = apple.getString("color");
                        String size = apple.getString("size");
                        adapter = new myAdapter();
                        appleExample.setPhone_model(phone_model);
                        appleExample.setImei_number(imei_number);
                        appleExample.setActive(active);
                        appleExample.setWarranty(warranty);
                        appleExample.setWarranty_status(warranty_status);
                        appleExample.setTele_support(tele_support);
                        appleExample.setTele_support_status(tele_support_status);
                        appleExample.setMade_area(made_area);
                        appleExample.setStart_date(start_date);
                        appleExample.setEnd_date(end_date);
                        appleExample.setColor(color);
                        appleExample.setSize(size);
                        list.add(appleExample);
//                        adapter = new ArrayAdapter<String>(Result.this,android.R.layout.simple_list_item_1);
//                        adapter.add(phone_model);
//                        adapter.add(serial_number);
//                        adapter.add(imei_number);
//                        adapter.add(active);
//                        adapter.add(warranty_status);
//                        adapter.add(warranty);
//                        adapter.add(tele_support);
//                        adapter.add(tele_support_status);
//                        adapter.add(made_area);
//                        adapter.add(start_date);
//                        adapter.add(end_date);
//                        adapter.add(color);
//                        adapter.add(size);
//                        list.add(phone_model);
//                        list.add(serial_number);
//                        list.add(imei_number);
//                        list.add(active);
//                        list.add(warranty_status);
//                        list.add(warranty);
//                        list.add(tele_support);
//                        list.add(tele_support_status);
//                        list.add(made_area);
//                        list.add(start_date);
//                        list.add(end_date);
//                        list.add(color);
//                        list.add(size);
                        listView.setAdapter(adapter);


                    } else {
                        Toast.makeText(Result.this, "JsonError", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
