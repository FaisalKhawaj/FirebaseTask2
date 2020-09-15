package com.example.firebasetask1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UpdatedDataa extends AppCompatActivity {
    EditText name,age;
    TextView tv;
    Button update,read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_dataa);
        name = (EditText) findViewById(R.id.et_name);
        age=(EditText) findViewById(R.id.et_age);
        update = (Button) findViewById(R.id.btn_update);
        tv=(TextView) findViewById(R.id.tv);
        read=(Button) findViewById(R.id.btn_read);
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // instance of whole firebase
        final DatabaseReference databaseReference = database.getReference("User"); // reference of root node
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namee = name.getText().toString();
                int agee= Integer.parseInt(age.getText().toString());
// databaseReference now refers to users
                      Map<String,Object> updatedData=new HashMap<>();
                      updatedData.put("/user1/Name",namee);
                      updatedData.put("/user1/Age",agee);
                                    // is m parent k reference ni hoga blky child k hoga
                updatedData.put("/-MEneygMJTdmbMSuoAu1/Age",agee);
                updatedData.put("/-MEneygMJTdmbMSuoAu1/Name",namee);
databaseReference.updateChildren(updatedData);  // updated value will be atomic
            }
        });                      /*save button code ends*/

        // Read data from root node of firebase
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("user1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) { // called twice , 1.initial state,2 whenever data changes

                        Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue(); // previously data returned as string but here returend as map
                        // listener will get username and value and age and value
                        Log.d("tag", "onDataChanged: Name : " + data.get("Name"));
                        Log.d("tag", "onDataChanged: Age : " + data.get("Age"));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("Tag", "On Failure" + databaseError.getMessage());
                    }
                });


            }
            });
    }
}