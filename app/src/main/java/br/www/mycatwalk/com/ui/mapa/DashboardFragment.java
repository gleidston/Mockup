package br.www.mycatwalk.com.ui.mapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import br.www.mycatwalk.com.R;
import br.www.mycatwalk.com.ui.activities.MapsActivity;

public class DashboardFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map,
                container, false);
        Button button = (Button) rootView.findViewById(R.id.buttonAbrirMapa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        return rootView;
    }

    public void updateDetail() {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
    }
}
