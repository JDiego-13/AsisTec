package com.example.asistec2_1;
/*import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MenuPrincipal extends AppCompatActivity {

    private FirebaseFirestore db;
    private static final String TAG = "FirebaseExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Configura la Toolbar como ActionBar
     //   Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        // Referencia al TextView en la Toolbar
        //androidx.appcompat.widget.TextView toolbarTextView = toolbar.findViewById(R.id.toolbarTextView);

        //db = FirebaseFirestore.getInstance();
        //CollectionReference collection = db.collection("usuarios");

        // Recupera datos de Firestore
        collection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    StringBuilder userData = new StringBuilder();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Accede a los datos de Firestore
                        String nombre = document.getString("nombre");
                        userData.append(nombre).append(", ");
                    }

                    // Muestra la información en la Toolbar
                    toolbarTextView.setText(userData.toString());
                } else {
                    Log.d(TAG, "Error al recuperar datos: ", task.getException());
                }
            }
        });
    }
}
*/