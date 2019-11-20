package br.www.mycatwalk.com.ui.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import br.www.mycatwalk.com.R;
import br.www.mycatwalk.com.model.Usuario;

public class FiltroActivity extends AppCompatActivity {

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    private ImageView imageFotoEscolhida;
    private Bitmap imagem;
    private Bitmap imagemFiltro;
    private TextInputEditText textDescricaoFiltro;
  //  private List<ThumbnailItem> listaFiltros;
    private String idUsuarioLogado;
    private Usuario usuarioLogado;
    private AlertDialog dialog;

    private RecyclerView recyclerFiltros;
   // private AdapterMiniaturas adapterMiniaturas;

    private DatabaseReference usuariosRef;
    private DatabaseReference usuarioLogadoRef;
    private DatabaseReference firebaseRef;
    private DataSnapshot seguidoresSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);



    }
}
