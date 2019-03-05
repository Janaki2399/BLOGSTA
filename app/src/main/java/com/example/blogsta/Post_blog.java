package com.example.blogsta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.UUID;

public class Post_blog extends AppCompatActivity {
    // private static final String TextUtils = ;
    private ImageView imageView;
    private EditText department;
    private EditText mPostTitle;
    private EditText mType;
    //  private ProgressDialog mProgress;
    private EditText mPostDesc;
    private Button mSubmitButton;
    private static final String TAG = "adding_blog";
    private Uri mImageUri=null;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private static final int GALLERY_REQUEST=1;
    int position;
    int number;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_blog);
        mType=(EditText)findViewById(R.id.type);
        department=(EditText)findViewById(R.id.department);
        mPostTitle=(EditText)findViewById(R.id.title);
        mPostDesc=(EditText)findViewById(R.id.description);
        mSubmitButton=(Button)findViewById(R.id.post);
        imageView =(ImageView)findViewById(R.id.imageBtn);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorage=FirebaseStorage.getInstance().getReference();

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startPosting();
            }
        });
    }
    private void startPosting(){

        String array[]={"fuck","shit","bullshit"};

        // mProgress.setMessage("Posting to blog...");
        //mProgress.show();
        final String type_val=mType.getText().toString().trim();
        final String dept_val=department.getText().toString().trim();
        final String title_val=mPostTitle.getText().toString().trim();
        final String desc_val=mPostDesc.getText().toString().trim();
        if(!TextUtils.isEmpty(title_val)&& !TextUtils.isEmpty(desc_val)&& mImageUri!=null)
        {
        for(String i :array)
        {
            if(desc_val.contains(i))
            {
               flag=1;
                break;
            }
        }
        if(flag==1)
        {
            Toast.makeText(Post_blog.this, " Contains inappropriate words.Upload Again.", Toast.LENGTH_SHORT).show();
        }
        else {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            if (type_val.equalsIgnoreCase("blog")) {
                number = 10;
                if (dept_val.equalsIgnoreCase("cse") || dept_val.equalsIgnoreCase("cs") || dept_val.equalsIgnoreCase("computer")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("cse");
                    position = 0;
                }
                if (dept_val.equalsIgnoreCase("mech") || dept_val.equalsIgnoreCase("mechanical")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Mechanical");
                    position = 1;
                }
                if (dept_val.equalsIgnoreCase("electrical") || dept_val.equalsIgnoreCase("electronics") || dept_val.equalsIgnoreCase("EEE")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Electronics");
                    position = 2;
                }
                if (dept_val.equalsIgnoreCase("Automobile") || dept_val.equalsIgnoreCase("Vehicles")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Automobile");
                    position = 3;
                }
                if (dept_val.equalsIgnoreCase("civil") || dept_val.equalsIgnoreCase("architecture")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Civil");
                    position = 4;
                }
                if (dept_val.equalsIgnoreCase("Chemical") || dept_val.equalsIgnoreCase("Chemical Engineering")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Chemical");
                    position = 5;
                }
                if (dept_val.equalsIgnoreCase("nano") || dept_val.equalsIgnoreCase("nano Engineering")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Nano");
                    position = 6;
                }
                if (dept_val.equalsIgnoreCase("Mechatronics") || dept_val.equalsIgnoreCase("Mechanicalelectronics")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Mechatronics");
                    position = 7;
                }
                if (dept_val.equalsIgnoreCase("robotics") || dept_val.equalsIgnoreCase("robots")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Robotics");
                    position = 8;
                }
                if (dept_val.equalsIgnoreCase("aerospace") || dept_val.equalsIgnoreCase("aeronautics")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Aerospace");
                    position = 9;
                }
            } else if (type_val.equalsIgnoreCase("guide")) {
                number = 11;
                if (dept_val.equalsIgnoreCase("cse") || dept_val.equalsIgnoreCase("cs") || dept_val.equalsIgnoreCase("computer")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("cse");
                    position = 0;
                }
                if (dept_val.equalsIgnoreCase("mech") || dept_val.equalsIgnoreCase("mechanical")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Mechanical");
                    position = 1;
                }
                if (dept_val.equalsIgnoreCase("electrical") || dept_val.equalsIgnoreCase("electronics") || dept_val.equalsIgnoreCase("EEE")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Electronics");
                    position = 2;
                }
                if (dept_val.equalsIgnoreCase("Automobile") || dept_val.equalsIgnoreCase("Vehicles")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Automobile");
                    position = 3;
                }
                if (dept_val.equalsIgnoreCase("civil") || dept_val.equalsIgnoreCase("architecture")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Civil");
                    position = 4;
                }
                if (dept_val.equalsIgnoreCase("Chemical") || dept_val.equalsIgnoreCase("Chemical Engineering")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Chemical");
                    position = 5;
                }
                if (dept_val.equalsIgnoreCase("nano") || dept_val.equalsIgnoreCase("nano Engineering")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Nano");
                    position = 6;
                }
                if (dept_val.equalsIgnoreCase("Mechatronics") || dept_val.equalsIgnoreCase("Mechanicalelectronics")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Mechatronics");
                    position = 7;
                }
                if (dept_val.equalsIgnoreCase("robotics") || dept_val.equalsIgnoreCase("robots")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Robotics");
                    position = 8;
                }
                if (dept_val.equalsIgnoreCase("aerospace") || dept_val.equalsIgnoreCase("aeronautics")) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Aerospace");
                    position = 9;
                }
            }

            final StorageReference ref = mStorage.child("Blog/" + UUID.randomUUID().toString());
            ref.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;
                                    //Do what you want with the url
                                    DatabaseReference newPost = mDatabase.push();
                                    newPost.child("title").setValue(title_val);
                                    newPost.child("desc").setValue(desc_val);
                                    newPost.child("image").setValue(downloadUrl.toString());


                                }
                            });
                            progressDialog.dismiss();
                            Toast.makeText(Post_blog.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            Intent mainIntent = new Intent(Post_blog.this, Feed.class);
                            mainIntent.putExtra("position", position);
                            mainIntent.putExtra("number", number);
                            startActivity(mainIntent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Post_blog.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
            //final StorageReference filePath=mStorage.child("Blog").child(mImageUri.getLastPathSegment());
           /*filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   Uri downloadUrl=taskSnapshot.mStorage.getDownloadUrl();
               }
           });
       }*/
        }
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri=data.getData();
            imageView.setImageURI(mImageUri);
        }
    }
}

