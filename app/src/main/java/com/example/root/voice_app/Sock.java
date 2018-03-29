package com.example.root.voice_app;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class Sock extends MainActivity {

    private EditText myet;

    private TextView mytv;

    private Button mybtn;

    public String remsg;

    @Override

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        myet = (EditText)findViewById(R.id.edit);

        mytv = (TextView)findViewById(R.id.text);

        mybtn = (Button)findViewById(R.id.sendbtn);

        mybtn.setOnClickListener(new OnClickListener(){

            @Override

            public void onClick(View v){

                if((mybtn.getText().toString() != null)&&

                        (!mybtn.getText().toString().equals(""))){

                    Connect tp = new Connect(myet.getText().toString());
                    tp.run();



                    mytv.setText(myet.getText().toString());
                    myet.setText("");
                }
            }
        });
    }
}
