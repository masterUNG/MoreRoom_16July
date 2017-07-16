package app.sutthinant.nant.moreroom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private String nameString, priceString, phoneString;
    private EditText nameEditText, priceEditText, phoneEditText;
    private int indexAnInt = 0;
    private int[] picInts = new int[]{R.id.imvShowPic1, R.id.imvShowPic2,
            R.id.imvShowPic3, R.id.imvShowPic4};
    private Uri uri;
    private ArrayList<String> stringArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Constant
        setupConstant();

        //Back Controller
        backController();

        //Initial View
        initialView();

        //Add Picture Controller
        addPictureController();


    }   //Main Method

    private void setupConstant() {
        stringArrayList = new ArrayList<String>();
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String tag = "16JulyV1";
        Log.d(tag, "requestCode ==> " + requestCode);

        if (requestCode == indexAnInt && resultCode == RESULT_OK) {
            Log.d(tag, "All Result OK");


            //Show Image
            uri = data.getData();
            try {

                //Display Image
                Bitmap bitmap = BitmapFactory
                        .decodeStream(getContentResolver().openInputStream(uri));
                ImageView imageView = (ImageView) findViewById(picInts[indexAnInt]);
                bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                imageView.setImageBitmap(bitmap);

                //Find Path and Add ArrayList
                stringArrayList.add(findPahtImage());
                Log.d(tag, "stringArrayList ==> " + stringArrayList);


            } catch (Exception e) {
                Log.d(tag, "e ShowImage ==> " + e.toString());
            }


            indexAnInt += 1;
        }   // if

    }   // onActivityResult

    private String findPahtImage() {

        String strResult = null;

        String[] strings = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();
            int i = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            strResult = cursor.getString(i);

        } else {
            strResult = uri.getPath();
        }

        return strResult;
    }

    private void addPictureController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvadd);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (indexAnInt < picInts.length) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Please Choose App"),
                            indexAnInt);
                } else {
                    MyAlert myAlert = new MyAlert(MainActivity.this);
                    myAlert.myDialog("Over Picture", "Cannot Add More " + Integer.toString(picInts.length) + "Pic");
                }


            }
        });
    }

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
