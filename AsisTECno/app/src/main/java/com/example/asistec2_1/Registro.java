
package com.example.asistec2_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Se importan las clases necesarias para trabajar con Firebase
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth; // Para Authentication
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase; // Si usas Realtime Database
import com.google.firebase.firestore.FirebaseFirestore; // Si usas Cloud Firestore
import android.util.Log;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Botones (Objetos)
        Button btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        Button btnInicioSesion = findViewById(R.id.btnInicioSesion);
        //Firebase
        FirebaseApp.initializeApp(this);//inicializa firebase

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();//Para Authentication
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(); // Si usas Realtime Database
        FirebaseFirestore firestore = FirebaseFirestore.getInstance(); // Si usas Cloud Firestore
        //Objetos (Acciones)
        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intentPonerCuenta = new Intent(Registro.this, MainActivity.class);
                    startActivity(intentPonerCuenta);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.nombre)).getText().toString();
                String apellidos = ((EditText) findViewById(R.id.apellidos)).getText().toString();
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                String confPassword = ((EditText) findViewById(R.id.confPassword)).getText().toString();
                //Verificacion de los campos si estan vacios
                if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || password.isEmpty() || confPassword.isEmpty()){
                    Toast.makeText(Registro.this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confPassword)){
                    Toast.makeText(Registro.this, "Las conseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }
                //crear un usuario en firebase Authentication
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Registro exitoso
                                if (task.isSuccessful()){
                                    DatabaseReference databaseReference = firebaseDatabase.getReference("usuarios");
                                    String userID = firebaseAuth.getCurrentUser().getUid();
                                    Usuario usuario = new Usuario(nombre, apellidos, email);
                                    databaseReference.child(userID).setValue(usuario);
                                    // Redirigir al usuario a la ventana inicial.
                                    Intent intentInicio = new Intent(Registro.this, IniciodeAlumnos.class);
                                    startActivity(intentInicio);
                                } else {
                                    // Error al registrar el usuario
                                    //Toast.makeText(Registro.this, "Error al crear usuario: " + task.getException().getMessage();
                                    String errorMessage = "Error al crear usuario: " + task.getException().getMessage();
                                    Log.e("Registro", errorMessage);
                                    Toast.makeText(Registro.this, errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    //Clase usuario
    public class Usuario{

        private String nombre;
        private String apellidos;
        private String correo;
        //Constructor
        public Usuario(String nombre, String apellidos, String correo){
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.correo = correo;
        }
        //getter's
        public String getNombre(){
            return nombre;
        }
        public String getApellidos(){
            return apellidos;
        }
        public String getCorreo(){
            return correo;
        }
        //setter's
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }
        public void setCorreo(String correo) {
            this.correo = correo;
        }
    }
}