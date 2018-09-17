package s2017s06.kr.hs.mirim.wearetoghther;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class oneDayDecorator implements DayViewDecorator {

    private CalendarDay date;

    public oneDayDecorator() {
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.4f));
        view.addSpan(new ForegroundColorSpan(Color.GREEN));
    }

    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}

public class Tab3Content extends Fragment{

    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

    Calendar calendar = Calendar.getInstance();
    String weekDay;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    private ListView listView;
    private ListView listView2;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;
    List<Object> Array = new ArrayList<>();
    List<Object> Array2 = new ArrayList<>();
    Button button;

    String caldate="";
    String calday="";
    String str = "",b = "";
    EditText edit1,edit2,edit3;
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;
    private CalendarView mCalendarView;
    private List<EventDay> mEventDays = new ArrayList<>();

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wtab3, container, false);

        listView = rootView.findViewById(R.id.lv);
        listView2 = rootView.findViewById(R.id.lv2);
        listView.setVisibility(View.INVISIBLE);
        listView2.setVisibility(View.INVISIBLE);

        button = rootView.findViewById(R.id.cal_plus_btn);
        button.setVisibility(View.INVISIBLE);
        edit1 = rootView.findViewById(R.id.cal_wed1);
        edit2 = rootView.findViewById(R.id.cal_wed2);
        edit3 = rootView.findViewById(R.id.cal_wed3);

        edit1.setVisibility(View.INVISIBLE);
        edit2.setVisibility(View.INVISIBLE);
        edit3.setVisibility(View.INVISIBLE);

        final MaterialCalendarView materialCalendarView  = (MaterialCalendarView)rootView.findViewById(R.id.calendarView);
        materialCalendarView.setVisibility(View.VISIBLE);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2018, 9, 1))
                .setMaximumDate(CalendarDay.from(2018, 9, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                button.setVisibility(View.VISIBLE);
                materialCalendarView.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
                listView2.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                edit2.setVisibility(View.VISIBLE);
                edit3.setVisibility(View.VISIBLE);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        str = edit1.getText().toString()+" "+edit2.getText().toString()+" "+edit3.getText().toString();
                        edit1.setText("");
                        edit2.setText("");
                        edit3.setText("");
                        databaseReference.child(caldate).push().setValue(str);
                    }
                });

               // Toast.makeText(getActivity(),""+(date.getMonth()+1)+"월 "+date.getDay()+"일", Toast.LENGTH_LONG).show();

                switch(date.getDay()%7) {
                    case 1: calday = "월요일";break;
                    case 2: calday = "화요일";break;
                    case 3: calday = "수요일";break;
                    case 4: calday = "목요일";break;
                    case 5: calday = "금요일";break;
                    case 6: calday = "토요일";break;
                    case 0: calday = "일요일";break;
                }

                initDatabase();

                adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,new ArrayList<String>());
                adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,new ArrayList<String>());

                listView.setAdapter(adapter);
                listView2.setAdapter(adapter2);

                caldate = "10"+String.valueOf(date.getDay());

                //Toast.makeText(getActivity(),d,Toast.LENGTH_LONG).show();





                mReference = mDatabase.getReference(calday);
                mReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adapter.clear();
                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                            String msg2 = messageData.getValue().toString();
                            Array.add(msg2);
                            adapter.add(msg2);
                        }
                        adapter.notifyDataSetChanged();
                        listView.setSelection(adapter.getCount() - 1);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                mReference = mDatabase.getReference(caldate);
                mReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adapter2.clear();
                        for(DataSnapshot messageData : dataSnapshot.getChildren()) {
                            String msg3 = messageData.getValue().toString();
                            Array2.add(msg3);
                            adapter2.add(msg3);
                        }
                        adapter2.notifyDataSetChanged();
                        listView2.setSelection(adapter2.getCount()-1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


//                Intent intent = new Intent(getActivity(), Tab3_1Content.class);
//                intent.putExtra("date", date.getDay());
//                //Toast.makeText(CalendarActivity.this,date.getDay() , Toast.LENGTH_LONG).show();
//                startActivity(intent);
            }
        });
        materialCalendarView.addDecorators(new oneDayDecorator());
        return rootView;
    }

    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }
}
