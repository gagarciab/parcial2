package view;

import java.io.FileInputStream;
import java.io.IOException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class conexion {
    public static Firestore basedatos;
    public static void conectarFirebase() {
        try {
            FileInputStream as = new FileInputStream("parcial2em.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(as))
                    .build();

            FirebaseApp.initializeApp(options);
            basedatos = FirestoreClient.getFirestore();
            System.out.println("Conexion exitosa");
            
        } catch (IOException e) {
            System.err.println("Error " + e.getMessage());

        }

    }
    
}
