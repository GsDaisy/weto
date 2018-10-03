package s2017s06.kr.hs.mirim.wearetoghther;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BasicMake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_make);
        Intent intent = getIntent();
        String check = intent.getStringExtra("check");

        if(check.equals("check")) {
            Toast.makeText(BasicMake.this, "존재하지 않는 방입니다", Toast.LENGTH_LONG).show();
        }

        else if(check.equals("check3")) {
            Toast.makeText(BasicMake.this,"이미 생성되어 있는 방입니다.",Toast.LENGTH_LONG).show();
        }

        Button button1 = (Button)findViewById(R.id.makeroom);
        Button button2 = (Button)findViewById(R.id.enterroom);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(BasicMake.this,make.class);
                startActivity(intent2);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(BasicMake.this,EnterActivity.class);
                startActivity(intent3);
            }
        });

    }
}
