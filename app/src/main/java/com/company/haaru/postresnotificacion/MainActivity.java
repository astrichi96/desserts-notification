package com.company.haaru.postresnotificacion;

import android.animation.Animator;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.company.haaru.postresnotificacion.Controlador.Postre;
import com.company.haaru.postresnotificacion.Controlador.PostreAdapter;
import com.company.haaru.postresnotificacion.Fragment.CardContentFragment;
import com.company.haaru.postresnotificacion.Fragment.InicioContentFragment;
import com.company.haaru.postresnotificacion.Fragment.OpinionContentFragment;
import com.company.haaru.postresnotificacion.Fragment.SpinnerFragment;
import com.company.haaru.postresnotificacion.Host.ClaseParaHost;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
    FloatingActionsMenu fabM;

    public static Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




/*
         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.comenta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarComentario(view);
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
///////////////////////////////////////////////////////////////////////////////////////////////////////
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        InicioContentFragment newFragment = new InicioContentFragment();
        ft.replace(R.id.content_main,newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
        con = getApplicationContext();
        new ConectarAsync().execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            //FloatingActionsMenu fam = (FloatingActionsMenu) findViewById(R.id.opciones_fab);
            //fam.setEnabled(false);

            InicioContentFragment newFragment = new InicioContentFragment();
            ft.replace(R.id.content_main, newFragment);
        } //else if (id == R.id.nav_opiniones) {
            //FloatingActionsMenu fam = (FloatingActionsMenu) findViewById(R.id.opciones_fab);
            //fam.setEnabled(true);
            //OpinionContentFragment newFragment = new OpinionContentFragment();
            //ft.replace(R.id.content_main, newFragment);
        //}
          else if (id == R.id.nav_lista_postres) {
            CardContentFragment newFragment = new CardContentFragment();
            ft.replace(R.id.content_main, newFragment);
        } else if (id == R.id.nav_share) {
           SpinnerFragment newFragment = new SpinnerFragment();
          ft.replace(R.id.content_main, newFragment);
        }


        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
       return true;
    }

    public void agregarComentario(View view){

        Intent i = new Intent(this,AgregarComentario.class);
        startActivity(i);


    }


    @Override
    public void onClick(View view) {
        if(view.getId() != R.id.opciones_fab){
           fabM.setSelected(false);
        }
    }

    public class ConectarAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            //tus cabras aqui
            /*final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            OkHttpClient client = new OkHttpClient();
            String json = "{hola:hola1}";
            String url = "http://192.168.1.6/controladores/Postres.php?action=read&usuarios_id=1";

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

            Log.d("ASYNC", "MENSAJE LUL");*/
            //ClaseParaHost cc = new ClaseParaHost();
            //List<String> usuarios = cc.retornarUsuarios();

            return null;
        }
    }


}
