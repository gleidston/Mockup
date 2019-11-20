package br.www.mycatwalk.com.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.www.mycatwalk.com.R;
import br.www.mycatwalk.com.helper.ConfiguracaoFirebase;
import br.www.mycatwalk.com.model.Usuario;

public class CadastroActivity extends AppCompatActivity {


    private EditText campoNome, campoEmail , campoSenha;
    private Button botaoCadastrar;
    private ProgressBar progressBar;

    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();

        botaoCadastrar.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                // recuperando os dados digitado nos campos
                String textoNome  = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textosenha = campoSenha.getText().toString();

                if( !textoNome.isEmpty() ){
                    if( !textoEmail.isEmpty() ){
                        if( !textosenha.isEmpty() ){

                            usuario = new Usuario();
                            usuario.setNome( textoNome );
                            usuario.setEmail( textoEmail );
                            usuario.setSenha( textosenha );
                            cadastrar( usuario );

                        }else{
                            Toast.makeText(CadastroActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CadastroActivity.this,
                                "Preencha o email!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });
    }


    /**
     * Método responsável por cadastrar usuário com e-mail e senha
     * e fazer validações ao fazer o cadastro
     */
    public void cadastrar(final Usuario usuario) {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
            // aqui esta um erro    "bolinhya123@gmail.com", "bo12345"
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if( task.isSuccessful() ){

                            try {

                                progressBar.setVisibility(View.GONE);



                                Toast.makeText(CadastroActivity.this,
                                        "Cadastro com sucesso",
                                        Toast.LENGTH_SHORT).show();

                                startActivity( new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                            }catch (Exception e){
                                e.printStackTrace();
                            }



                        }else {



                            String erroExcecao = "";
                            try{
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                                erroExcecao = "Digite uma senha mais forte!";
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                erroExcecao = "Por favor, digite um e-mail válido";
                            }catch (FirebaseAuthUserCollisionException e){
                                erroExcecao = "Este conta já foi cadastrada";
                            } catch (Exception e) {
                                erroExcecao = "ao cadastrar usuário: "  + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(CadastroActivity.this,
                                    "Erro: " + erroExcecao ,
                                    Toast.LENGTH_SHORT).show();


                          // apos o cadastro manda o usuario para activity principal


                            startActivity( new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }

                    }
                }
        );


    }

    // modulo para inicializar os componentes separadamente
    public void inicializarComponentes(){

        campoNome       = findViewById(R.id.editCadastroNome);
        campoEmail      = findViewById(R.id.editCadastroEmail);
        campoSenha      = findViewById(R.id.editCadastroSenha);
        botaoCadastrar  = findViewById(R.id.buttonCadastrar);
       // progressBar     = findViewById(R.id.progressBar);

        campoNome.requestFocus();

    }
}
