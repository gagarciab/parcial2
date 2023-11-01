/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelview;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.conexion;


/**
 *
 * @author USUARIO
 */
public class cuerpo {
    CollectionReference reference;
    static Firestore basedatos;
    public static boolean guardarPersona(String coleccion, String referencia, Map<String, Object> data){
        basedatos=FirestoreClient.getFirestore();
        try {
            DocumentReference docRef = basedatos.collection(coleccion).document(referencia);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Exito al guardar");
            return true;
        } catch (Exception e) {
            System.out.println("Error "+ e.getMessage());
        }
        return false;
    }
    
    public static boolean actualizarPersona(String coleccion, String referencia, Map<String, Object> data){
        basedatos=FirestoreClient.getFirestore();
        try {
            DocumentReference docRef = basedatos.collection(coleccion).document(referencia);
            ApiFuture<WriteResult> result = docRef.update(data);
            System.out.println("Exito al actualziar");
            return true;
        } catch (Exception e) {
            System.out.println("Error "+ e.getMessage());
        }
        return false;
    }
    public static boolean eliminarPersona(String coleccion, String documento){
        basedatos=FirestoreClient.getFirestore();
        try {
            DocumentReference docRef = basedatos.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            System.out.println("Exito al eliminar");
            return true;
        } catch (Exception e) {
            System.out.println("Error "+ e.getMessage());
        }
        return false;
    }
    public static void mostrarDatos(JTable table){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Articulo");
        model.addColumn("Proveedor");
        model.addColumn("Referencia");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
    
        
        try {
            CollectionReference personas = conexion.basedatos.collection("Pesona") ;
            ApiFuture<QuerySnapshot> qs = personas.get();
            for(DocumentSnapshot document: qs.get().getDocuments()){
            model.addRow(new Object[]{
                document.getId(),
                document.getString("Articulo"),
                document.getString("Proveedor"),
                document.getDouble("Referencia"),
                document.getDouble("Precio"),
                document.getDouble("Cantidad"),
            });
        }
            
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error: " +e.getMessage());
        }
        table.setModel(model);
    }
    
}

    
