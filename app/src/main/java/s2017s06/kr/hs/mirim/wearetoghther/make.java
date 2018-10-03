package s2017s06.kr.hs.mirim.wearetoghther;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class mCheck {
    static int ma = 0;


    public mCheck(int a){
        this.ma = a;
    }
    public mCheck() {

    }
}

class mCheck2 {
    static int mb = 0;

    public mCheck2(int a) {
        this.mb = a;
    }

    public mCheck2(){}
}

public class make extends AppCompatActivity {

    EditText room,nick;
    Button button;

    mCheck m1 = new mCheck(0);
    mCheck m2 = new mCheck(0);

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

                 FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
                 DatabaseReference mdatabaseRef = mdatabase.getReference("room");

                mdatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i=0,j=0;

                        for (DataSnapshot messageData2 : dataSnapshot.getChildren())
                            i++;

                        for (DataSnapshot messageData : dataSnapshot.getChildren()){
                            j++;
                            String makeroom = messageData.getValue().toString();
                            mCheck c = new mCheck();
                            if(r.equals(makeroom)&&c.ma ==0) {
                                c.ma =1;
                                Intent intent = new Intent(make.this,BasicMake.class);

                                intent.putExtra("check", "check3");
                                startActivity(intent);
                                break;
                            }

                            else if(i==j&&c.ma == 0) {
                                databaseReference.child("room").push().setValue(r);

                                mCheck c2 = new mCheck(1);

                                Intent intent = new Intent(make.this,MainActivity.class);
                                intent.putExtra("room",r);
                                intent.putExtra("name",n);
                                intent.putExtra("check","1");
                                startActivity(intent);
                                break;
                            }
                            else {
                                continue;
                            }



                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



             }
         });
    }



}
