package com.example.firebasetask1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ReadWriteListOfChilds extends AppCompatActivity {
    EditText name,age;
    TextView tv;
    Button save,read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write_list_of_childs);
        name = (EditText) findViewById(R.id.et_name);
        age=(EditText) findViewById(R.id.et_age);
        save = (Button) findViewById(R.id.btn_save);
        tv=(TextView) findViewById(R.id.tv);
        read=(Button) findViewById(R.id.btn_read);
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // instance of whole firebase
        final DatabaseReference databaseReference = database.getReference("User"); // reference of root node




        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Map<String,Object> data= (Map<String, Object>) dataSnapshot.getValue();  // data mein child k values ha
                        Log.d("Tag","OnChildAdded Called: Name : "+data.get("Name"));
                        Log.d("Tag","OnChildChanged Called: Age : "+data.get("Age"));
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                    }


                });
save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String namee = name.getText().toString();
        int agee= Integer.parseInt(age.getText().toString());

        String key = databaseReference.push().getKey(); // generated child key of user
        databaseReference.child(key).child("Name").setValue(namee);
        // root k child  key k child name hoga r us ki value
        databaseReference.child(key).child("Age").setValue(agee);
    }
});


    }

    }

