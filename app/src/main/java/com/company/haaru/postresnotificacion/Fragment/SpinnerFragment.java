package com.company.haaru.postresnotificacion.Fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.company.haaru.postresnotificacion.Host.ClaseParaHost;
import com.company.haaru.postresnotificacion.R;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

/**
 * Created by Haaru on 21/11/2016.
 */

public class SpinnerFragment extends Fragment {
    Spinner Anadidos;
    View view;
    EditText nombre_receta;
    EditText receta;
    Button botoncito;
    Spinner spinner;


 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.spinner_usuarios, container, false);
        Anadidos = (Spinner) view.findViewById(R.id.idSpinner);
        nombre_receta = (EditText) view.findViewById(R.id.campo_texto);
        receta = (EditText) view.findViewById(R.id.campo_texto2);
        botoncito = (Button) view.findViewById(R.id.button2);
        spinner = (Spinner)view.findViewById(R.id.idSpinner);
        botoncito.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             new addPos().execute(nombre_receta.getText().toString(),
                     receta.getText().toString(),
                     FirebaseInstanceId.getInstance().getToken(),
                     spinner.getSelectedItem().toString());
         }
        });
     new ConectarAsync().execute();
        return view;
    }

    public class addPos extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {
            ClaseParaHost cc = new ClaseParaHost();
            cc.agregarPostre(strings[0],strings[1],strings[2]);
            cc.enviarNoti("¡Genial! Te han mandado una receta","Echa un vistazo",strings[3]);
            return null;
        }
    }

    public class ConectarAsync extends AsyncTask<Void, Void, Void> {
        List<String> usuarios;

        @Override
        protected Void doInBackground(Void... voids) {
            ClaseParaHost cc = new ClaseParaHost();
            usuarios = cc.retornarUsuarios();
                Log.d("USER_SIZE",""+usuarios.size());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.Anadidos, android.R.layout.simple_spinner_item);
            ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, usuarios);
            //ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(MainActivity.mn.getApplicationContext(), R.layout.sp_tv, categories);
            Anadidos.setAdapter(adapter);
        }
    }



}
