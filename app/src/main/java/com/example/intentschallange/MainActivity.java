package com.example.intentschallange;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvContactsApp;
    Button btnAddCont;
    ImageView emoji, webImg, locationImg, callImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContactsApp = findViewById(R.id.tvContactsApp);
        btnAddCont = findViewById(R.id.btnAddCont);

        emoji = findViewById(R.id.greenEmoji);
        emoji.setVisibility(View.GONE);
        callImg = findViewById(R.id.callImg);
        callImg.setVisibility(View.GONE);
        webImg = findViewById(R.id.webImg);
        webImg.setVisibility(View.GONE);
        locationImg = findViewById(R.id.locationImg);
        locationImg.setVisibility(View.GONE);

        btnAddCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.intentschallange.addContact.class);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                String res = data.getStringExtra("emojiName");

                if (res.equals("green") )
                    emoji.setImageResource(R.drawable.greenemoji);
                else if (res.equals("yellow"))
                    emoji.setImageResource(R.drawable.yellowemoji);
                else
                    emoji.setImageResource(R.drawable.redemoji);


                webImg.setVisibility(View.VISIBLE);
                callImg.setVisibility(View.VISIBLE);
                locationImg.setVisibility(View.VISIBLE);
                emoji.setVisibility(View.VISIBLE);

                final String number = data.getStringExtra("number");

                callImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
                        startActivity(intent);
                    }
                });

                final String page = data.getStringExtra("web");

                webImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+page));
                        startActivity(intent);
                    }
                });

                final String adr = data.getStringExtra("address");

                locationImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+adr));
                        startActivity(intent);
                    }
                });

            }


            if (resultCode == RESULT_CANCELED)
            {
                Toast.makeText(MainActivity.this, "No data received", Toast.LENGTH_SHORT).show();
            }
        }
    }
}