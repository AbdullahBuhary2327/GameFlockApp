package com.example.gameflock;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gameflock.model.UserModel;
import com.example.gameflock.service.NetworkBroadcast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {
    UserModel currentUserModel;
    FirebaseDatabase database;
    FirebaseStorage storage;
    private CircleImageView profile;
    EditText name, email, phone;
    ProgressBar progressBar;
    private Button close, savebutton;
    private DatabaseReference databaseReference;
    private Button cameraButton;
    private Button galleryButton;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private BroadcastReceiver broadcastReceiver;
    private Uri selectedImageUri;
    private MyDatabaseHelper dbHelper;

    private String myUri = "";
    private StorageTask uploadTask;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private StorageReference storageProfilePicsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        broadcastReceiver = new NetworkBroadcast();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        dbHelper = new MyDatabaseHelper(this);



        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        storageProfilePicsRef = FirebaseStorage.getInstance().getReference().child("Profile pic");
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        profile = findViewById(R.id.profile_image);
        close = findViewById(R.id.btnClose);
        savebutton = findViewById(R.id.btnSave);
        name = findViewById(R.id.userText);
        phone = findViewById(R.id.phoneText);
        email = findViewById(R.id.mailText);
        progressBar = findViewById(R.id.progress_bar_profile);
        cameraButton = findViewById(R.id.btnCamera);
        galleryButton = findViewById(R.id.btnGallery);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();










        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(EditProfile.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
            }
        });










        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(EditProfile.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            email.setText(user.getEmail());
        }

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchChoosePictureIntent();
            }
        });



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });























    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchChoosePictureIntent() {
        Intent choosePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(choosePictureIntent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                profile.setImageBitmap(imageBitmap);
            } else if (requestCode == REQUEST_IMAGE_PICK) {
                Uri selectedImageUri = data.getData();
                profile.setImageURI(selectedImageUri);
            }
        }
    }




    void setInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }




    public void saveProfileData(View view) {
        String name = ((EditText) findViewById(R.id.userText)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phoneText)).getText().toString();
        String email = ((EditText) findViewById(R.id.mailText)).getText().toString();

        // Save the image URI (or path) in the database
        String imageUri = selectedImageUri.toString(); // Replace with the actual image URI

        // Insert data into the database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("email", email);
        values.put("image_uri", imageUri);

        long newRowId = db.insert("my_table", null, values);

        db.close();

        // Show a message or navigate to another screen
        // You can also load the saved data from the database and display it on the profile page.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

}




