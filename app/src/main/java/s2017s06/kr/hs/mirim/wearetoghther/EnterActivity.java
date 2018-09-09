package s2017s06.kr.hs.mirim.wearetoghther;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class EnterActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference = firebaseDatabase.getReference();

    EditText enter_room,enter_nick;
    Button button;
    String sroom,snick;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        mReference = FirebaseDatabase.getInstance().getReference();

        enter_room = findViewById(R.id.enter_room);
        enter_nick = findViewById(R.id.enter_nick);
        button = findViewById(R.id.enter_next);

        sroom = enter_room.getText().toString();
        snick = enter_nick.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sroom = enter_room.getText().toString();
                final String snick = enter_nick.getText().toString();

                    databaseReference.child("user").child(sroom).push().setValue(snick);
                    Intent intent = new Intent(EnterActivity.this,MainActivity.class);
                    startActivity(intent);
            }
        });


    }

}
