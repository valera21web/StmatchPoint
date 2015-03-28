package com.valera21web.stmatchpoint.stmatchpoint.libs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


abstract public class APIConnect extends AsyncTask<String, String, JSONObject> {

    private String url;
    private String params;
    protected View view;

    abstract public void init(JSONObject result);

    public APIConnect(View _view, String _url, String _params) {
        this.view = _view;
        this.url = _url;
        this.params = _params;
    }

    public APIConnect(View _view, String _url) {
        this(_view, _url, "");
    }

    @Override
    protected JSONObject doInBackground(String... arg) {
        return this.convert(this.connect(this.url, this.params));
    }

    @Override
    protected void onPostExecute (JSONObject jsonArray) {
        this.init(jsonArray);
    }

    private JSONObject convert(String result) {
        Log.d("APIConnect", "convert: "+result);
        try {
            Log.d("APIConnect", "convert true");
            return new JSONObject(result);
        } catch (JSONException e) {
            Log.d("APIConnect", "convert false "+e);
            return null;
        }
    }

    private String connect(String url, String params) {
        Log.d("APIConnect", "start");
        String charset = "UTF-8";
        int timeout = 10000;
        try {
            String parametrs = URLEncoder.encode(params, charset);
            HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Length", ""+Integer.toString(parametrs.getBytes().length));
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            Log.d("APIConnect", "OutputStream");

            OutputStream outputStream = conn.getOutputStream();
            Log.d("APIConnect", "OutputStream1");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, charset));
            Log.d("APIConnect", "OutputStream1");
            writer.write(parametrs);
            Log.d("APIConnect", "OutputStream3");
            writer.flush();
            writer.close();
            Log.d("APIConnect", "OutputStream4");
            outputStream.close();
            Log.d("APIConnect", "connect");
            conn.connect();
            Log.d("APIConnect", "getResponseCode");
            int status = conn.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }
            return "status "+status;
        } catch (MalformedURLException ex1) {
            Log.e("APIConnect", "MalformedURLException" + ex1.toString());
        } catch (IOException ex2) {
            Log.e("APIConnect", "IOException" + ex2.toString());
        } catch (Exception e) {
            Log.e("APIConnect", "Exception" + e.toString());
        }
        return null;
    }
}
