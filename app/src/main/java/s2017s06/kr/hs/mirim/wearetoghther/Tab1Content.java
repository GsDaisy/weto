package s2017s06.kr.hs.mirim.wearetoghther;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.TrustAnchor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

class MyListAdapter extends ArrayAdapter<String>{
    public static final String TAG="권한";
    Context maincon;
    LayoutInflater inflater;
    int layout;
    ArrayList<String> arSrc;
    // public Context c;
    public MyListAdapter(Context context, int alayout, ArrayList<String> arrSrc){
        super(context, alayout,arrSrc);
        maincon = context;
        this.arSrc = arrSrc;
        layout = alayout;
        inflater = LayoutInflater.from(maincon);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //final int pos = position;

        final Context context = parent.getContext();

        View v = convertView;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.widget_icontext, parent, false);

            TextView txt = (TextView) v.findViewById(R.id.widget_text);
            txt.setText("");
            txt.setText(arSrc.get(position));
            txt.setTextSize(25);

            CheckBox checkBox = (CheckBox)v.findViewById(R.id.checkbox);
            //checkBox.setClickable(false);
            //checkBox.setChecked(items.get(position).isChecked());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton view, boolean isChecked) {

                    if(view.isChecked()){
                        //Log.d(LOG_TAG, "checked list item is : " + String.valueOf(listnumber+1));
                        //Log.d(LOG_TAG, "checked list name is : " + String.valueOf(view.getText()));
                        //states[listnumber]=isChecked;
                    }
                }
            });


        }

        return v;
    }


}


public class Tab1Content extends Fragment {

    Sharevariable tab1 = new Sharevariable();

    String Tab1room = tab1.room;
    String Tab1nick = tab1.nick;

    //private FloatingActionButton fab;
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

    Calendar calendar = Calendar.getInstance();
    String weekDay = dayFormat.format(calendar.getTime());

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    String Day = "";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.wtab1, container, false);

        listView = rootView.findViewById(R.id.listviewmsg);


        initDatabase();

        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,new ArrayList<String>());
        listView.setAdapter(adapter);


        switch(weekDay) {
            case "Monday":Day = "월요일"; break;
            case "Tuesday":Day ="화요일";break;
            case "Wednesday":Day = "수요일";break;
            case "Thursday":Day = "목요일";break;
            case "Friday":Day = "금요일";break;
            case "Saturday":Day = "토요일"; break;
            case "Sunday": Day = "일요일"; break;
            default:Day = weekDay;
        }

        mReference = mDatabase.getReference("user");
        mReference.child(Tab1room).child(Day).addValueEventListener(new ValueEventListener() {
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



