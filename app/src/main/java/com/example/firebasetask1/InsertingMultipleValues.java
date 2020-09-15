package com.example.firebasetask1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.firebasetask1.R.drawable.ic_baseline_error_24;

public class InsertingMultipleValues extends AppCompatActivity {
    EditText name,age,phone;
    TextView tv;
    Button insert,read;
    ProgressBar loading;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserting_multiple_values);
        name = (EditText) findViewById(R.id.et_name);
        age=(EditText) findViewById(R.id.et_age);
        phone=(EditText) findViewById(R.id.Phoneno);

        tv=(TextView) findViewById(R.id.tv);

        insert= (Button) findViewById(R.id.btn_save);
        read=(Button) findViewById(R.id.btn_read);
        loading=(ProgressBar) findViewById(R.id.loading);

        FirebaseDatabase database = FirebaseDatabase.getInstance(); // instance of whole firebase
        final DatabaseReference databaseReference = database.getReference("User"); // reference of root node

        firebaseAuth=FirebaseAuth.getInstance();   // getting instance of firebase
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String namee=  name.getText().toString();
             String agee=age.getText().toString();
             String Phone=phone.getText().toString();
                if(TextUtils.isEmpty((CharSequence) namee))
                {
                    Drawable customErrorDrawable = getResources().getDrawable(ic_baseline_error_24);
                    customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                    name.setError("Name Required", customErrorDrawable);
                    return;
                }
                if(TextUtils.isEmpty((CharSequence) agee))
                {
                    Drawable customErrorDrawable = getResources().getDrawable(ic_baseline_error_24);
                    customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                    age.setError("Age Required", customErrorDrawable);
                    return;
                }
                if(TextUtils.isEmpty((CharSequence) Phone))
                {
                    Drawable customErrorDrawable = getResources().getDrawable(ic_baseline_error_24);
                    customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                    phone.setError("Phone Number Required", customErrorDrawable);
                    return;
                }

                Map<String,Object> insertValues=new HashMap<>();
                insertValues.put("Name",namee);
                insertValues.put("Age",agee);
                insertValues.put("Phoneno",Phone);
                String key=databaseReference.push().getKey();
                databaseReference.child(key).setValue(insertValues);
                Toast.makeText(InsertingMultipleValues.this,"Data Inserted",Toast.LENGTH_SHORT).show();

            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Map<String,Object> data= (Map<String, Object>) dataSnapshot.getValue();  // data mein child k values ha
                        Log.d("Tag","OnChildAdded Called: Name : "+data.get("Name"));
                        Log.d("Tag","OnChildAdded Called: Age : "+data.get("Age"));
                        Log.d("Tag","OnChildAdded Called: Phoneno : "+data.get("Phoneno"));
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

    }
}