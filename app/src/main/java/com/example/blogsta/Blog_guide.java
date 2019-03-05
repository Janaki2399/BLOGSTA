package com.example.blogsta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Blog_guide extends AppCompatActivity {
Button blog;
Button guide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_guide);

        blog = (Button) findViewById(R.id.blog);
        guide=(Button)findViewById(R.id.guide);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Blog_guide.this, SectionActivity.class);
                intent.putExtra("Blog_guide_number", 10);
                startActivity(intent); // start Intent
            }
        });
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Blog_guide.this, SectionActivity.class);
                intent.putExtra("Blog_guide_number", 11);
                startActivity(intent); // start Intent
            }
        });

    }
}
