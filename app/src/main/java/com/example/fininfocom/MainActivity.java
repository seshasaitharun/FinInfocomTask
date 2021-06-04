package com.example.fininfocom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText emailId, mobileNumber;
    Button submit;
    RecyclerView recyclerView1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<ModelClass> aList;
    AdapterClass adapterClass;

    public boolean isValidEmail(String str) {
        return (!TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailId = findViewById(R.id.emaiId);
        mobileNumber = findViewById(R.id.mobileNumber);
        submit = findViewById(R.id.submit);
        recyclerView1 = findViewById(R.id.recyclerView);
        loadData();
        buildRecyclerView();


        emailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!isValidEmail(emailId.getText().toString())) {

                    emailId.setError(("Please enter Valid Email"));
                } else {
                    emailId.setError(null);
                }
            }

        });

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(mobileNumber.getText().toString().length()==0||emailId.getText().toString().length()==0){
            Toast.makeText(MainActivity.this, "Please Enter Mobile or EmailID", Toast.LENGTH_SHORT).show();
        }else {

            aList.add(new ModelClass(emailId.getText().toString(),mobileNumber.getText().toString()));
            adapterClass.notifyItemInserted(aList.size());
            Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            sharedPreferences = getSharedPreferences("shared preferences123", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(aList);
            editor.putString("personDetails", json);
            editor.apply();




        }
    }
});

    }

    private void buildRecyclerView() {


        adapterClass = new AdapterClass(aList, MainActivity.this);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView1.setAdapter(adapterClass);



    }

    private void loadData() {

        sharedPreferences = getSharedPreferences("shared preferences123 ", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("personDetails", null);
        Type type = new TypeToken<ArrayList<ModelClass>>() {
        }.getType();
        aList = gson.fromJson(json, type);
        if (aList == null) {
            // if the array list is empty
            // creating a new array list.
            aList = new ArrayList<>();
        }




    }




}