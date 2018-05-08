package com.company.haaru.postresnotificacion.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.haaru.postresnotificacion.Controlador.Postre;
import com.company.haaru.postresnotificacion.Controlador.PostreAdapter;
import com.company.haaru.postresnotificacion.Host.ClaseParaHost;
import com.company.haaru.postresnotificacion.MainActivity;
import com.company.haaru.postresnotificacion.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NoridaVanegas on 19/11/2016.
 */

public class CardContentFragment extends Fragment {
    RecyclerView recyclerView;

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.RV_Postre);
        LinearLayoutManager lm=new LinearLayoutManager(view.getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
            new getPos().execute();
            return view;
    }

    public void setAdp(final List<Postre> ls){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new PostreAdapter(ls));
            }
        });
    }

    public class getPos extends AsyncTask<Void, Void, Void>{
        List<Postre> postre;
        @Override
        protected Void doInBackground(Void... voids) {
            postre=new ArrayList<>();

            ClaseParaHost cc = new ClaseParaHost();
            List<String> postres = cc.retornarRecetas();
            for (int i=0;i<postres.size();i++){
                postre.add(new Postre("Postre",postres.get(i),R.drawable.cup_03));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setAdp(postre);
        }
    }
}
