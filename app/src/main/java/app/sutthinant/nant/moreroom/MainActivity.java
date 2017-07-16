package app.sutthinant.nant.moreroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private String nameString, priceString, phoneString;
    private EditText nameEditText, priceEditText, phoneEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Back Controller
        backController();

        //Initial View
        initialView();


    }   //Main Method

    private void initialView() {
        nameEditText = (EditText) findViewById(R.id.edtName);
        priceEditText = (EditText) findViewById(R.id.edtPrice);
        phoneEditText = (EditText) findViewById(R.id.edtPhone);
    }

    public void clickSave(View view) {

        //Get Value From Edit Text
        nameString = nameEditText.getText().toString().trim();
        priceString = priceEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();

        //Check Space
        if (nameString.equals("") || priceString.equals("") || phoneString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert(MainActivity.this);
            myAlert.myDialog(getString(R.string.title_have_space),
                    getString(R.string.message_have_space));
        } else {
            //No Space
        }



    }   // clickSave

    private void backController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}   //Main Class
