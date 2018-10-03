package s2017s06.kr.hs.mirim.wearetoghther;

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

 class Check {
    public static int su1=0;
    public static String room ="";
    public static String nick = "";

    public Check(){
        su1++;
    }
    public Check(int a) {}
    public Check(int a,int b) {su1 = 0;}
}

class Check2{
     public static int su2=0;

     public Check2() {
         su2++;
     }
    public Check2(int i) {}
    public Check2(int a,int b) {su2 = 0;}
}

class Check3{
    public static int su3=0;

    public Check3() {
        su3++;
    }
    public Check3(int i) {

    }
    public Check3(int a,int b) {su3 = 0;}
}


public class EnterActivity extends AppCompatActivity {
    EditText enter_room,enter_nick;
    Button button;
    String sroom="",snick="";

    Check cho = new Check(1,2);
    Check2 cho2 = new Check2(3,4);
    Check3 cho3 = new Check3(5,6);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        enter_room = findViewById(R.id.enter_room);
        enter_nick = findViewById(R.id.enter_nick);
        button = findViewById(R.id.enter_next);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check a = new Check(1);
                Check2 a2 = new Check2(1);

                Check3 c3 = new Check3();
                if(c3.su3==1) {
                    Toast.makeText(EnterActivity.this, "데이터 확인 후 한번 더 클릭해주세요", Toast.LENGTH_SHORT).show();
                    a.su1=0;
                    a2.su2 = 0;
                }
                sroom = enter_room.getText().toString();
                snick = enter_nick.getText().toString();

                Check.room = sroom;
                Check.nick = snick;

                FirebaseDatabase rdatabase = FirebaseDatabase.getInstance();
                DatabaseReference rdatabaseRef = rdatabase.getReference("room");

                rdatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot messageData : dataSnapshot.getChildren()) {
                            String msg2 = messageData.getValue().toString();

                            if(msg2.equals(sroom)) {
                                Check ch = new Check();
                                break;
                            }
                        }
                        }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseRef = database.getReference("user");
                databaseRef.child(sroom).child("nick").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot messageData : dataSnapshot.getChildren()) {
                            String msg2 = messageData.getValue().toString();

                            if(msg2.equals(snick)) {
                                Check2 ch3 = new Check2();
                                break;
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Check t = new Check(1);
                Check2 t2 = new Check2(1);

                if(c3.su3!=1) {
                    if(t.su1==1&&t2.su2!=1) {
                        c3.su3 =0;
                        t.su1=0;
                        t2.su2 = 0;
                        Toast.makeText(EnterActivity.this,"방에 성공적으로 들어왔습니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                        intent.putExtra("room", Check.room);
                        intent.putExtra("name", Check.nick);
                        intent.putExtra("check", "1");
                        startActivity(intent);
                    }
                    else if(t.su1==1&&t2.su2==1){
                        c3.su3 =0;
                        t.su1=0;
                        t2.su2 = 0;
                        Toast.makeText(EnterActivity.this,"방에 성공적으로 들어왔습니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                        intent.putExtra("room", Check.room);
                        intent.putExtra("name", Check.nick);
                        intent.putExtra("check", "1");
                        startActivity(intent);
                    }
                    else if(t.su1==0) {
                        c3.su3 =0;
                        t.su1=0;
                        t2.su2 = 0;
                        Intent intent = new Intent(EnterActivity.this, BasicMake.class);
                        intent.putExtra("check", "check");
                        startActivity(intent);
                    }
                }



            }
        });


    }

}
