package com.example.projetofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetofirebase.controller.LoginController;
import com.example.projetofirebase.model.LoginModel;

public class Login extends AppCompatActivity {

    TextView edtEmail, edtSenha;
    String usuarioAtual, senha;
    Button btnCadastro, btnLogin;
    LoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        edtEmail =  findViewById(R.id.edit_email);
        edtSenha = findViewById(R.id.edit_senha);

        btnCadastro = findViewById(R.id.btnTela_cadastro);
        btnLogin = findViewById(R.id.btn_login);

        loginController = new LoginController();

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaDados()) {

                    usuarioAtual = edtEmail.getText().toString();
                    senha = edtSenha.getText().toString();


                    // Validar login e obter o modelo de usuário
                    LoginModel loginModel = loginController.validarLogin(getApplicationContext(), usuarioAtual, senha);

                    if (loginModel != null) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("user_id", loginModel.getIdUsuario());
                        editor.putString("email", loginModel.getEmail());
                        editor.putBoolean("isFirstLogin", false);
                        editor.apply();

                        iniciarMainActivity();

                    } else {
                        edtEmail.setText("");
                        edtSenha.setText("");
                        edtEmail.requestFocus();
                    }
                }
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, FormCadastro.class);
                startActivity(intent);
            }
        });
    }

    private boolean validaDados() {
        boolean retorno = true;
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Obrigatório*");
            edtEmail.requestFocus();
            retorno = false;
        } else if (edtSenha.getText().toString().isEmpty()) {
            edtSenha.setError("Obrigatório*");
            edtSenha.requestFocus();
            retorno = false;
        }
        return retorno;
    }


    private void iniciarMainActivity() {
        Intent intent = new Intent(getApplicationContext(), FormHome.class);
        startActivity(intent);
        finish();
    }

    private void mostrarToast(String mensagem) {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
    }
}