package com.example.intentschallange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class addContact extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etWebsite, etAddress, etNumber;
    ImageView redEmoji, yellowEmoji, greenEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etAddress = findViewById(R.id.etAddress);
        etWebsite = findViewById(R.id.etWebsite);

        redEmoji = findViewById(R.id.redEmoji);
        greenEmoji = findViewById(R.id.greenEmoji);
        yellowEmoji = findViewById(R.id.yellowEmoji);

        greenEmoji.setOnClickListener(this);
        yellowEmoji.setOnClickListener(this);
        redEmoji.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (etName.getText().toString().isEmpty()||etAddress.getText().toString().isEmpty()||etNumber.getText().toString().isEmpty()||etWebsite.getText().toString().isEmpty())
            Toast.makeText(addContact.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            else
            {
                Intent intent = new Intent();
                intent.putExtra("number", etNumber.getText().toString());
                intent.putExtra("address", etAddress.getText().toString());
                intent.putExtra("web", etWebsite.getText().toString());

                if (view.getId() == R.id.greenEmoji)
                    intent.putExtra("emojiName", "green");
                else if (view.getId() == R.id.yellowEmoji)
                    intent.putExtra("emojiName", "yellow");
                else
                    intent.putExtra("emojiName", "red");

                setResult(RESULT_OK,intent);
                addContact.this.finish();
            }

    }
}