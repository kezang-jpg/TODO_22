package com.example.todo_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView instuction,authorText,authorTitle;
    private Button search;
    private EditText bookinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instuction=findViewById(R.id.instruction);
        authorText=findViewById(R.id.authorText);
        search=findViewById(R.id.Search);
        bookinput=findViewById(R.id.bookInput);
        authorTitle=findViewById(R.id.authorTitle);
    }

    public void SearchBook(View view) {
        String queryString=bookinput.getText().toString();
        new fetchBook(authorTitle, authorText).execute(queryString);
        ConnectivityManager connMgr=(ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=null;
        if(connMgr!=null){
            networkInfo=connMgr.getActiveNetworkInfo();
        }

        if (networkInfo!=null &&networkInfo.isConnected()&&queryString.length()!=0){
            new fetchBook(authorTitle,authorText).execute(queryString);
            authorTitle.setText("");
            authorText.setText(R.string.loading);
        }else{
            if(queryString.length()==0){
                authorText.setText("");
                authorTitle.setText(R.string.nosearch);
            }else{
                authorText.setText("");
                authorTitle.setText(R.string.noNetwork);
            }
        }
    }
}