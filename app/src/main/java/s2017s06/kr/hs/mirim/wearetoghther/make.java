package s2017s06.kr.hs.mirim.wearetoghther;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class make extends AppCompatActivity {


    EditText room,nick;
    Button button;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);

        room = (EditText)findViewById(R.id.user_chat);
        nick = (EditText)findViewById(R.id.user_edit);
        button = (Button)findViewById(R.id.user_next);



         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 final String r = room.getText().toString();
                 final String n = nick.getText().toString();

                 databaseReference.child("user").child(r).push().setValue(n);

                 Intent intent = new Intent(make.this,MainActivity.class);
                 startActivity(intent);
             }
         });
    }



}
