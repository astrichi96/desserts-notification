package com.company.haaru.postresnotificacion.Host;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Haaru on 21/11/2016.
 */

public class ClaseParaHost {
    public String url_host = "http://192.168.1.5";

    public ClaseParaHost() {
    }

    public void agregarUsuario(String nombre,String token){
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        String json = "{hola:hola1}";
        String url = url_host+"/controladores/Usuario.php?action=create&name="+nombre+"&Token="+token;

        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();

            Log.d("RESPONSE",response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("ASYNC", "MENSAJE LUL2");

    }

    public void agregarPostre(String nombre,String receta,String usuario_id){
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        String json = "{hola:hola1}";
        String url = url_host+"/controladores/Postres.php?action=create&nombre="+nombre+"&receta="+receta+"&usuarios_token="+usuario_id;
        Log.d("URL", url);
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();

            Log.d("RESPONSE",response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("ASYNC", "MENSAJE LUL2");

    }

    public List<String> retornarUsuarios (){
        List<String> usuarios = new ArrayList<>();
        //Hacer la peticion
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
         OkHttpClient client = new OkHttpClient();
        String json = "{hola:hola1}";
        String url = url_host+"/controladores/Usuario.php?action=read&name=all";
        //Recibir el response
        Log.d("URL", url);
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();

            String json_response = response.body().string();
            //Log.d("RESPONSE",json_response);
            //Parsear el json
            JSONObject resp = new JSONObject(json_response);
            String status = resp.getString("status");
            JSONArray usuarios_lst = resp.getJSONArray("msg");

            for (int i=0;i<usuarios_lst.length();i++){
                JSONObject obj_list = usuarios_lst.getJSONObject(i);
                String nombre = obj_list.getString("nombre");
                String token = obj_list.getString("token");
                //Log.d("JSON_USERS", nombre);
                //Log.d("JSON_TOKEN", token);
                usuarios.add(nombre);
            }

            //Agregar cada usuario al arraylist

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return usuarios;
    }

    public List<String> retornarRecetas(){
        List<String> postres = new ArrayList<>();
        //Hacer la peticion
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String json = "{hola:hola1}";
        String url = url_host+"/controladores/Postres.php?action=read&nombre=all";
        //Recibir el response
        Log.d("URL", url);
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();

            String json_response = response.body().string();
            Log.d("RESPONSE",json_response);
            //Parsear el json
            JSONObject resp = new JSONObject(json_response);
            String status = resp.getString("status");
            JSONArray usuarios_lst = resp.getJSONArray("msg");

            for (int i=0;i<usuarios_lst.length();i++){
                JSONObject obj_list = usuarios_lst.getJSONObject(i);
                String nombre = obj_list.getString("nombre");
                String  receta = obj_list.getString("receta");
//                String token = obj_list.getString("token");
                //Log.d("JSON_USERS", nombre);
                //Log.d("JSON_TOKEN", token);
                postres.add(nombre+";"+receta);

            }

            //Agregar cada usuario al arraylist

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    return postres;
    }

    public void enviarNoti(String title, String body_n, String to){
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String json = "{hola:hola1}";
        String url = url_host+"/controladores/Notificaciones.php?title="+title+"&body="+body_n+"&to="+to;
        Log.d("URL", url);
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();

            Log.d("RESPONSE",response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
