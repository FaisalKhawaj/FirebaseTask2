package com.example.firebasetask1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteData extends AppCompatActivity {
Button delete,show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);
        delete=(Button) findViewById(R.id.btn_delete);
        show=(Button) findViewById(R.id.btn_read);
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // instance of whole firebase
        final DatabaseReference databaseReference = database.getReference("User"); // reference of root node

        delete.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
                    Task<Void> task=  databaseReference.child("-MEqtL_LhlL5SuEIey5q").removeValue();
                    task.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("Tag","Data Deleted Successfully");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Tag","Data Not Deleted");
                        }
                    });

       }
   });




    }
}