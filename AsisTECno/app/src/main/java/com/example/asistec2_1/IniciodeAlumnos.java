package com.example.asistec2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IniciodeAlumnos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciode_alumnos);

        // Accede al TextView donde deseas mostrar el nombre
        TextView textView14 = findViewById(R.id.textView14);
        TextView textView13 = findViewById(R.id.textView13);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");

            databaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // El usuario existe en la base de datos
                        String nombre = dataSnapshot.child("nombre").getValue(String.class);
                        // También puedes obtener otros datos, como el correo electrónico
                        String correo = dataSnapshot.child("correo").getValue(String.class);
                        String apellidos = dataSnapshot.child("apellidos").getValue(String.class);

                        if (nombre != null && !nombre.isEmpty()) {
                            textView14.setText("nombre: " + nombre + " " + apellidos);
                            textView13.setText("correo: " + correo);
                        } else {
                            textView14.setText("nombre: Nombre no disponible");
                            textView13.setText("correo: Correo no disponible");
                        }
                    } else {
                        // El usuario no existe en la base de datos
                        textView13.setText("nombre: Nombre no disponible");
                        textView14.setText("correo: Correo no disponible");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle any errors that may occur
                }
            });

            // Configura un OnClickListener para el botón "Ir a Mis Asistencias"
            Button misAsistenciasButton = findViewById(R.id.button);
            misAsistenciasButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Define una intención para iniciar la actividad MisAsistencias
                    Intent intent = new Intent(IniciodeAlumnos.this, MisAsistencias.class);
                    startActivity(intent);
                }
            });
        }
    }
}

