package com.company.haaru.postresnotificacion.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.company.haaru.postresnotificacion.Host.ClaseParaHost;
import com.company.haaru.postresnotificacion.R;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

/**
 * Created by NoridaVanegas on 19/11/2016.
 */

public class InicioContentFragment extends Fragment{

    EditText nombre;
    View view;
    Button boton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.perfil,container,false);
        nombre = (EditText) view.findViewById(R.id.campo_texto);
        boton = (Button) view.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConectarAsync().execute(nombre.getText().toString());
            }
        });
        return view;
    }

    public class ConectarAsync extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            ClaseParaHost cc = new ClaseParaHost();
            cc.agregarUsuario(strings[0], FirebaseInstanceId.getInstance().getToken());
            //Log.d("USER_SIZE",""+nombre_usuarios.size());
            return null;
        }
    }

}
//perfil