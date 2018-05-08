package com.company.haaru.postresnotificacion.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.company.haaru.postresnotificacion.R;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by NoridaVanegas on 19/11/2016.
 */

public class OpinionContentFragment extends Fragment implements View.OnClickListener{
    FloatingActionsMenu fab;
    CoordinatorLayout cor;
    LinearLayout ln;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.opcion_item,container,false);

        fab = (FloatingActionsMenu) view.findViewById(R.id.opciones_fab);
        cor=(CoordinatorLayout) view.findViewById(R.id.cor);
        ln = (LinearLayout) view.findViewById(R.id.ln);

        ln.setOnClickListener(this);
        fab.setOnClickListener(this);
        cor.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        Log.d("hola","click");
        if(view.getId() != R.id.opciones_fab){
            fab.collapse();
        }
    }
}
