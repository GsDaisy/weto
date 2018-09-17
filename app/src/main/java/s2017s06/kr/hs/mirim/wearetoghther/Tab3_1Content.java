package s2017s06.kr.hs.mirim.wearetoghther;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Tab3_1Content extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3_1_content);

        Intent intent = getIntent();

        int date = intent.getExtras().getInt("date");

        TextView textDay = (TextView)new TextView(this);
        textDay.setText("2018년 10월 "+date+"일");
        textDay.setTextSize(19);
        setContentView(textDay);

    }
}
