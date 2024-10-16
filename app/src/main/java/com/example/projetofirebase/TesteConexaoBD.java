package com.example.projetofirebase;

import static com.example.projetofirebase.conexao.conectar;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexaoBD extends AppCompatActivity {

 TextView BancoTeste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_conexao_bd);

        Connection conn = conectar(TesteConexaoBD.this);
        BancoTeste = findViewById(R.id.Bancoteste);

        try {
            if (conn !=null) {
                if (!conn.isClosed())
                    BancoTeste.setText("CONEXAO REALIZADA COM SUCESSO");
                else
                    BancoTeste.setText("A CONEXÃO ESTÁ FECHADA");
            } else {
                BancoTeste.setText("CONEXAO NULA, NÃO REALIZADA");
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
            BancoTeste.setText("CONEXÃO FALHOU!!!\n" +
                    ex.getMessage());
        }
    }

}
