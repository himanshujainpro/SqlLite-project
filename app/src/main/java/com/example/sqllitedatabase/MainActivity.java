 package com.example.sqllitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {
     DatabaseHelper myDB;
    EditText id, name, email, courseCount;
    Button add, update, delete, view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        id=findViewById(R.id.editText_id);
        name=findViewById(R.id.editText_name);
        email=findViewById(R.id.editText_email);
        courseCount=findViewById(R.id.editText_CC);

        add=findViewById(R.id.button_add);
        update=findViewById(R.id.button_update);
        delete=findViewById(R.id.button_delete);
        view=findViewById(R.id.button_view);

        addData();
        updateData();
        deleteData();
        getData();


    }

    public void addData(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=myDB.insertData(id.getText().toString(),name.getText().toString(),email.getText().toString(),courseCount.getText().toString());
                if(isInserted)
                    Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void deleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletedRow=myDB.deleteData(id.getText().toString());

                if(deletedRow>0)
                    Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated=myDB.updateData(id.getText().toString(),name.getText().toString(),email.getText().toString(),courseCount.getText().toString());

                if(isUpdated)
                    Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getData(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_this=id.getText().toString();

                Cursor cursor=myDB.getData(id_this);
                String data=null;

                if (cursor.moveToNext()){
                    data = "ID: "+ cursor.getString(0) +"\n"+
                            "Name: "+ cursor.getString(1) +"\n"+
                            "Email: "+ cursor.getString(2) +"\n"+
                            "Course Count: "+ cursor.getString(3) +"\n";
                }

                showMessage("Data: ", data);
            }
        });
    }

    private void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
