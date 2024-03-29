package com.example.android.architectureexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditBookActivity extends AppCompatActivity {
    private static final int PregCode = 2;
    private static final int REQUESCODE = 1;



    public static final String EXTRA_TITLE = "com.example.android.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.android.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.android.architectureexample.EXTRA_PRIORITY";
    public static final String EXTRA_ID= "com.example.android.architectureexample.EXTRA_ID";

    // public static final String EXTRA_IMAGE= "com.example.android.architectureexample.EXTRA_IMAGE";
   // public static final String EXTRA_CATEGORY= "com.example.android.architectureexample.EXTRA_CATEGORY";

    private   EditText category, title, description;
    private   NumberPicker numberPickerPriority;
    private ImageView ImgUserPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        category = findViewById(R.id.category_add);
        title = findViewById(R.id.title_add);
        description = findViewById(R.id.description_add);
        numberPickerPriority = findViewById(R.id.priority);
        ImgUserPhoto = findViewById(R.id.book_img_add);


        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(5);

        // get a book Image
        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                } else {
                    openGallery();
                }
            }
        });


       Intent intent = getIntent();



        setTitle("Add Edit Book");

        // we can distinguih between edit intent and creation intent with Our
        //EXTRA_ID, if there is this constatnt in extras we are in the edition, if it isn"t we are on the creation of new title
        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Book");
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else
        {
            setTitle("Add Book");
        }
    }

    private void checkAndRequestForPermission() {
        //PAY ATTENTION PERMISSION GRANTED NT DENIED !!
        if (ContextCompat.checkSelfPermission(AddEditBookActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(AddEditBookActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Please accept for required permission", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(AddEditBookActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PregCode);


            }
        } else {
            openGallery();
        }
    }

    private void openGallery() {
        //this method to open the gallery and pick the user image
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    private void saveBook() {
        String titleString = title.getText().toString();
        String descriptionString = description.getText().toString();
        int priority = numberPickerPriority.getValue();
        String categoryString = category.getText().toString();

        if (titleString.trim().isEmpty() || descriptionString.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_TITLE, titleString);
        dataIntent.putExtra(EXTRA_DESCRIPTION, descriptionString);
        dataIntent.putExtra(EXTRA_PRIORITY, priority);

            int id = getIntent().getIntExtra(EXTRA_ID,-1);
            if(id != -1)
            {
                // we add ID to extras only if it id != -1, i.e it is used
                dataIntent.putExtra(EXTRA_ID,id);
            }


        setResult(RESULT_OK, dataIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveBook();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}