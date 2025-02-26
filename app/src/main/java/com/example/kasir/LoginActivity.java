package com.example.kasir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private ImageButton btnBack; // Tombol back
    private String userRole; // Untuk menyimpan role yang dipilih dari PilihanActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ambil data role dari intent
        userRole = getIntent().getStringExtra("ROLE");

        // Inisialisasi elemen UI
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack); // Inisialisasi tombol back

        // Event klik tombol login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validasi login berdasarkan role
                if (isValidLogin(username, password)) {
                    Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();

                    // Navigasi sesuai dengan role
                    if (userRole != null) {
                        if (userRole.equals("admin")) {
                            startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                        } else if (userRole.equals("kasir")) {
                            startActivity(new Intent(LoginActivity.this, KasirActivity.class));
                        }
                    }
                    finish(); // Menutup LoginActivity agar tidak bisa kembali dengan tombol back
                } else {
                    Toast.makeText(LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Event klik tombol back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, pilihan.class);
                startActivity(intent);
                finish(); // Menutup LoginActivity agar tidak bisa kembali dengan tombol back
            }
        });
    }

    // Metode untuk validasi login berdasarkan role
    private boolean isValidLogin(String username, String password) {
        if (userRole != null) {
            if (userRole.equals("admin")) {
                return username.equals("admin") && password.equals("1234");
            } else if (userRole.equals("kasir")) {
                return username.equals("kasir") && password.equals("5678");
            }
        }
        return false;
    }
}
