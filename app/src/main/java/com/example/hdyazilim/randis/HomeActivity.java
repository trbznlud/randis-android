package com.example.hdyazilim.randis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ListView listView;
    private FirebaseAuth kayitlikimlik;
    private ArrayList<String> konularList = new ArrayList<>();
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private ArrayAdapter<String> adapter;

    @Override
    public void onBackPressed(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        kayitlikimlik = FirebaseAuth.getInstance();
        listView = findViewById(R.id.listViewKonular);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("ChatSubjects");

        adapter = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,konularList);
        listView.setAdapter(adapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                konularList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    konularList.add(ds.getKey());
                    Log.d("LOGVALUE",ds.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),""+databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this,ChatActivity.class);
                intent.putExtra("subject",konularList.get(position));
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.exit)
        {
            kayitlikimlik.signOut();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}