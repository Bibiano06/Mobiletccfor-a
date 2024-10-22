package com.example.projetofirebase.controller;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.example.projetofirebase.conexao;
import com.example.projetofirebase.model.LoginModel;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    public LoginModel validarLogin(Context context, String email, String senha) {
        Connection conn = conexao.conectar();
        LoginModel loginModel = null;

        try {
            String query = "SELECT email, senha FROM Usuario WHERE email = ? and senha = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String senhaArmazenada = rs.getString("senha");
                // Decodifica a senha armazenada
                byte[] decodePass = Base64.decode(senhaArmazenada, Base64.DEFAULT);
                String senhaDecodificada = new String(decodePass);
            } else {
                Log.d("LoginController", "Usuário não encontrado.");
            }
        } catch (Exception e) {
            Log.e("LoginController", "Erro ao validar login: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                Log.e("DB_ERROR", "Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return loginModel;
    }
}
