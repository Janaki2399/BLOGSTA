package com.example.blogsta;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;
import java.util.List;

import com.google.firebase.database.DatabaseError;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class Feed extends AppCompatActivity {
    private FirebaseAuth auth;
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabase;
    private List<Upload> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        auth = FirebaseAuth.getInstance();


        Intent mIntent = getIntent();
        int tempholder = mIntent.getIntExtra("position", 0);
        int number = mIntent.getIntExtra("number", 0);
if(number==10) {
    if (tempholder == 0) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("cse");

    } else if (tempholder == 1) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Mechanical");

    } else if (tempholder == 2) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Electronics");

    } else if (tempholder == 3) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Automobile");

    } else if (tempholder == 4) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Civil");
    } else if (tempholder == 5) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Chemical");

    } else if (tempholder == 6) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Nano");

    } else if (tempholder == 7) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Mechatronics");

    } else if (tempholder == 8) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Robotics");

    } else if (tempholder == 9) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog").child("Aerospace");

    }
}
else if(number==11)
{
    if (tempholder == 0) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("cse");

    } else if (tempholder == 1) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Mechanical");

    } else if (tempholder == 2) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Electronics");

    } else if (tempholder == 3) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Automobile");

    } else if (tempholder == 4) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Civil");
    } else if (tempholder == 5) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Chemical");

    } else if (tempholder == 6) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Nano");

    } else if (tempholder == 7) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Mechatronics");

    } else if (tempholder == 8) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Robotics");

    } else if (tempholder == 9) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Guide").child("Aerospace");

    }
}
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerAdapter<Upload,BlogViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter<Upload,BlogViewHolder>(  Upload.class,
                R.layout.activity_images,
                BlogViewHolder.class,mDatabase){


            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Upload model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(model.getImage());
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title){
            TextView post_title=(TextView) mView.findViewById(R.id.title);
            post_title.setText(title);

        }
        public void setDesc(String desc){
            TextView post_title=(TextView) mView.findViewById(R.id.desc);
            post_title.setText(desc);

        }
        public void setImage(String image)
        {
            ImageView post_image=(ImageView) mView.findViewById(R.id.image);
            Picasso.get()
                    .load(image)
                    .fit()
                    .centerCrop()
                    .into(post_image);


        }

    }


    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            startActivity(new Intent(Feed.this, Post_blog.class));
        }
        else if(id == R.id.logout){
            auth.signOut();
            Intent logouIntent = new Intent(Feed.this, MainActivity.class);
            logouIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logouIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
