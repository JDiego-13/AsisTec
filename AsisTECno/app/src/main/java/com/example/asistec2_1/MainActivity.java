package com.example.asistec2_1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText; // Asegúrate de tener esta importación
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
// Otras importaciones...

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
//Se importan las clases necesarias para trabajar con Firebase
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth; // Para Authentication
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase; // Si usas Realtime Database
import com.google.firebase.firestore.FirebaseFirestore; // Si usas Cloud Firestore
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        Button btnRegistrarse = findViewById(R.id.btnRegistrarse);

        ImageView imgTec = findViewById(R.id.imgTec);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Inicio de sesión exitoso
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    Intent intentInicioSesion = new Intent(MainActivity.this, IniciodeAlumnos.class);
                                    startActivity(intentInicioSesion);
                                } else {
                                    // Error al iniciar sesión
                                    Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intentRegistro = new Intent(MainActivity.this, Registro.class);
                    startActivity(intentRegistro);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        imgTec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.tecnm.mx/";
                Intent intentImg = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (intentImg.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentImg);
                } else {
                    startActivity(intentImg);
                }
            }
        });
    }
}
