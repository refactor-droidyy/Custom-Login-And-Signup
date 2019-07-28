package com.example.oooollxxx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class WorkingActivity extends AppCompatActivity {

    private ImageView imgg;
    private Toolbar tolbar;
    private TextView sell;
    private TabAccessAdapter adapter;
    private FirebaseUser user;
    private TextView txt;
    private DatabaseReference reference;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        txt = findViewById(R.id.username_toolbar);
        imgg = findViewById(R.id.img_toolbar);

        tolbar = findViewById(R.id.toolbarworking);
        setSupportActionBar(tolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().dispatchMenuVisibilityChanged(true);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        sell = findViewById(R.id.add_sell);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkingActivity.this,SellFormActivity.class));
            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User userr = dataSnapshot.getValue(User.class);
                assert userr != null;
                txt.setText(userr.getUsername());
                if(userr.getImageURL().equals("noimage")){
                    imgg.setImageResource(R.drawable.ic_action_name);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        TabLayout tablayout  = findViewById(R.id.tablayout);
        ViewPager viewPagerr = findViewById(R.id.viewpager);
        adapter = new TabAccessAdapter(getSupportFragmentManager());
        viewPagerr.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPagerr);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        //setMenubackground();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WorkingActivity.this,MainActivity.class));

                finish();
            case R.id.action_sell:


                case R.id.action_about:


            case R.id.action_cart:


            case R.id.action_payment:


            case R.id.action_progile:

        }
        return true;
    }

}
