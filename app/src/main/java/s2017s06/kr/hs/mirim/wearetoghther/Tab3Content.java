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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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

    Sharevariable tab3 = new Sharevariable();
    String Tab3room = tab3.room;

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
    Button cal_btn;

    TextView textView;

    int i=0,pos = 0;
    public static int firstcheck=300;
    String caldate="";
    String calday="";
    String calcheck="";
    String str = "",b = "";
    int myInt = 0;
    EditText edit1,edit2,edit3;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wtab3, container, false);


        cal_btn = rootView.findViewById(R.id.cal_button);
        cal_btn.setVisibility(View.INVISIBLE);

        textView = rootView.findViewById(R.id.dateTv);
        textView.setVisibility(View.INVISIBLE);

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

        final MaterialCalendarView materialCalendarView = (MaterialCalendarView) rootView.findViewById(R.id.calendarView);
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

                cal_btn.setVisibility(View.VISIBLE);
                cal_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cal_btn.findViewById(R.id.cal_button);
                        cal_btn.setVisibility(View.INVISIBLE);

                        materialCalendarView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        listView.findViewById(R.id.lv);
                        listView2.findViewById(R.id.lv2);
                        listView.setVisibility(View.INVISIBLE);
                        listView2.setVisibility(View.INVISIBLE);

                        button.findViewById(R.id.cal_plus_btn);
                        button.setVisibility(View.INVISIBLE);
                        edit1.findViewById(R.id.cal_wed1);
                        edit2.findViewById(R.id.cal_wed2);
                        edit3.findViewById(R.id.cal_wed3);

                        edit1.setVisibility(View.INVISIBLE);
                        edit2.setVisibility(View.INVISIBLE);
                        edit3.setVisibility(View.INVISIBLE);
                    }
                });

                button.setVisibility(View.VISIBLE);
                materialCalendarView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                listView2.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.VISIBLE);
                edit2.setVisibility(View.VISIBLE);
                edit3.setVisibility(View.VISIBLE);

                textView.setText(2018 + "년" + (date.getMonth() + 1) + " 월 " + date.getDay() + "일");

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        str = edit1.getText().toString() + " " + edit2.getText().toString() + " " + edit3.getText().toString();
                        edit1.setText("");
                        edit2.setText("");
                        edit3.setText("");
                        databaseReference.child("user").child(Tab3room).child(caldate).push().setValue(str);
                    }
                });

                // Toast.makeText(getActivity(),""+(date.getMonth()+1)+"월 "+date.getDay()+"일", Toast.LENGTH_LONG).show();




                switch (date.getDay() % 7) {
                    case 1:
                        calday = "월요일";
                        calcheck="월요일확인";
                        break;
                    case 2:
                        calday = "화요일";
                        calcheck="화요일확인";
                        break;
                    case 3:
                        calday = "수요일";
                        calcheck="수요일확인";
                        break;
                    case 4:
                        calday = "목요일";
                        calcheck="목요일확인";
                        break;
                    case 5:
                        calday = "금요일";
                        calcheck="금요일확인";
                        break;
                    case 6:
                        calday = "토요일";
                        calcheck="토요일확인";
                        break;
                    case 0:
                        calday = "일요일";
                        calcheck="일요일확인";
                        break;
                }

                initDatabase();

                final ArrayList<String> midList1 = new ArrayList<String>();
                final ArrayList<String> midList2 = new ArrayList<String>();
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,midList2);
                adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,midList1);

                listView.setAdapter(adapter);
                listView2.setAdapter(adapter2);

                caldate = "10" + String.valueOf(date.getDay());


                    mReference = mDatabase.getReference("user");
                    mReference.child(Tab3room).child(calday).addValueEventListener(new ValueEventListener() {
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


                mReference = mDatabase.getReference("user");
                mReference.child(Tab3room).child(caldate).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adapter2.clear();
                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                            String msg3 = messageData.getValue().toString();
                            Array2.add(msg3);
                            adapter2.add(msg3);
                        }
                        adapter2.notifyDataSetChanged();
                        listView2.setSelection(adapter2.getCount() - 1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        midList2.remove(position);
                        adapter.notifyDataSetChanged();

                        firstcheck = 0;

                        return false;
                    }
                });

                listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        i = 0;
                        pos = position + 1;
                        midList1.remove(position);
                        adapter2.notifyDataSetChanged();

                        mReference = mDatabase.getReference("user");
                        mReference.child(Tab3room).child(caldate).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                    i++;
                                    if (pos == i) {
                                        mReference.child(Tab3room).child(caldate).child(messageData.getKey()).removeValue();
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        return false;
                    }
                });



            }
        });

        materialCalendarView.addDecorator(new oneDayDecorator());
        return rootView;
    }



//                materialCalendarView.addDecorators(new oneDayDecorator());
//                return rootView;



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
