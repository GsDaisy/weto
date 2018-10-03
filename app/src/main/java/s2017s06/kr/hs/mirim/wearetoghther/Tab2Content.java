package s2017s06.kr.hs.mirim.wearetoghther;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tab2Content extends Fragment {

    Sharevariable tab2 = new Sharevariable();
     String Tab2room = tab2.room;

    String str1;
    int pos,i=0;
    ArrayAdapter<String> adapter;
    ListView list;
    Button btnAdd;
    EditText editText1,editText2,editText3;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    List<Object> Array = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wtab2, container, false);



        final LinearLayout layout1 = (LinearLayout) rootView.findViewById(R.id.linear_mon);
        final LinearLayout layout2 = (LinearLayout) rootView.findViewById(R.id.linear_tue);
        final LinearLayout layout3 = (LinearLayout) rootView.findViewById(R.id.linear_wed);
        final LinearLayout layout4 = (LinearLayout) rootView.findViewById(R.id.linear_thu);
        final LinearLayout layout5 = (LinearLayout) rootView.findViewById(R.id.linear_fri);
        final LinearLayout layout6 = (LinearLayout) rootView.findViewById(R.id.linear_sat);
        final LinearLayout layout7 = (LinearLayout) rootView.findViewById(R.id.linear_sun);

        Button btnMon = (Button) rootView.findViewById(R.id.mon_btn);
        Button btnTue = (Button) rootView.findViewById(R.id.tue_btn);
        Button btnWed = (Button) rootView.findViewById(R.id.wed_btn);
        Button btnThu = (Button) rootView.findViewById(R.id.thu_btn);
        Button btnFri = (Button) rootView.findViewById(R.id.fri_btn);
        Button btnSat = (Button) rootView.findViewById(R.id.sat_btn);
        Button btnSun = (Button) rootView.findViewById(R.id.sun_btn);


        initDatabase();

        //Monday
        final ArrayList<String> midList1 = new ArrayList<String>();
        list = (ListView) rootView.findViewById(R.id.listView1);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, midList1);
        list.setAdapter(adapter);

        final EditText editText1 = (EditText) rootView.findViewById(R.id.edit_mon1);
        final EditText editText2 = (EditText) rootView.findViewById(R.id.edit_mon2);
        final EditText editText3 = (EditText) rootView.findViewById(R.id.edit_mon3);
        btnAdd = (Button) rootView.findViewById(R.id.plus_btn);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = editText1.getText().toString()+" "+editText2.getText().toString()+" "+editText3.getText().toString();
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");

                databaseReference.child("user").child(Tab2room).child("월요일").push().setValue(str1);
            }
        });

        mReference = mDatabase.getReference("user");
        mReference.child(Tab2room).child("월요일").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                        Array.add(msg2);
                        adapter.add(msg2);

                }
                adapter.notifyDataSetChanged();
                list.setSelection(adapter.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int position2 = position;

                i=0;
                pos = position2+1;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("일정 삭제")
                        .setMessage("일정을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                midList1.remove(position2);
                                adapter.notifyDataSetChanged();

                                Toast.makeText(getActivity(),"일정이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                mReference = mDatabase.getReference("user");
                                mReference.child(Tab2room).child("월요일").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                            i++;
                                            if(pos == i) {mReference.child(Tab2room).child("월요일").child(messageData.getKey()).removeValue();
                                                break;}
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });
        ////////////////////////////////////////////TuesDaY
        final ArrayList<String> midList2 = new ArrayList<String>();
        list = (ListView) rootView.findViewById(R.id.listView2);

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, midList2);
        list.setAdapter(adapter2);

        final EditText editText4 = (EditText) rootView.findViewById(R.id.edit_tue1);
        final EditText editText5 = (EditText) rootView.findViewById(R.id.edit_tue2);
        final EditText editText6 = (EditText) rootView.findViewById(R.id.edit_tue3);
        btnAdd = (Button) rootView.findViewById(R.id.plus_btn2);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = editText4.getText().toString()+" "+editText5.getText().toString()+" "+editText6.getText().toString();
                editText4.setText("");
                editText5.setText("");
                editText6.setText("");
                databaseReference.child("user").child(Tab2room).child("화요일").push().setValue(str1);

            }
        });

        mReference = mDatabase.getReference("user");
        mReference.child(Tab2room).child("화요일").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter2.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter2.add(msg2);
                }
                adapter2.notifyDataSetChanged();
                list.setSelection(adapter2.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int position2 = position;

                i=0;
                pos = position2+1;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("일정 삭제")
                        .setMessage("일정을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                midList2.remove(position2);
                                adapter2.notifyDataSetChanged();

                                Toast.makeText(getActivity(),"일정이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                mReference = mDatabase.getReference("user");
                                mReference.child(Tab2room).child("화요일").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                            i++;
                                            if(pos == i) {mReference.child(Tab2room).child("화요일").child(messageData.getKey()).removeValue();
                                                break;}
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });
        //////////////////////////////Wednesday
        final ArrayList<String> midList3 = new ArrayList<String>();
        list = (ListView) rootView.findViewById(R.id.listView3);

        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, midList3);
        list.setAdapter(adapter3);

        final EditText editText7 = (EditText) rootView.findViewById(R.id.edit_wed1);
        final EditText editText8 = (EditText) rootView.findViewById(R.id.edit_wed2);
        final EditText editText9 = (EditText) rootView.findViewById(R.id.edit_wed3);
        btnAdd = (Button) rootView.findViewById(R.id.plus_btn3);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = editText7.getText().toString()+" "+editText8.getText().toString()+" "+editText9.getText().toString();
                editText7.setText("");
                editText8.setText("");
                editText9.setText("");
                databaseReference.child("user").child(Tab2room).child("수요일").push().setValue(str1);

            }
        });

        mReference = mDatabase.getReference("user");
        mReference.child(Tab2room).child("수요일").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter3.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter3.add(msg2);
                }
                adapter3.notifyDataSetChanged();
                list.setSelection(adapter3.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int position2 = position;

                i=0;
                pos = position2+1;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("일정 삭제")
                        .setMessage("일정을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                midList3.remove(position2);
                                adapter3.notifyDataSetChanged();

                                Toast.makeText(getActivity(),"일정이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                mReference = mDatabase.getReference("user");
                                mReference.child(Tab2room).child("수요일").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                            i++;
                                            if(pos == i) {mReference.child(Tab2room).child("수요일").child(messageData.getKey()).removeValue();
                                                break;}
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });
        ////////////////Thursday
        final ArrayList<String> midList4 = new ArrayList<String>();
        list = (ListView) rootView.findViewById(R.id.listView4);

        final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, midList4);
        list.setAdapter(adapter4);

        final EditText editText10 = (EditText) rootView.findViewById(R.id.edit_thu1);
        final EditText editText11 = (EditText) rootView.findViewById(R.id.edit_thu2);
        final EditText editText12 = (EditText) rootView.findViewById(R.id.edit_thu3);
        btnAdd = (Button) rootView.findViewById(R.id.plus_btn4);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = editText10.getText().toString()+" "+editText11.getText().toString()+" "+editText12.getText().toString();
                editText10.setText("");
                editText11.setText("");
                editText12.setText("");
                databaseReference.child("user").child(Tab2room).child("목요일").push().setValue(str1);

            }
        });

        mReference = mDatabase.getReference("user");
        mReference.child(Tab2room).child("목요일").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter4.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter4.add(msg2);
                }
                adapter4.notifyDataSetChanged();
                list.setSelection(adapter4.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int position2 = position;

                i=0;
                pos = position2+1;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("일정 삭제")
                        .setMessage("일정을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                midList4.remove(position2);
                                adapter4.notifyDataSetChanged();

                                Toast.makeText(getActivity(),"일정이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                mReference = mDatabase.getReference("user");
                                mReference.child(Tab2room).child("목요일").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                            i++;
                                            if(pos == i) {mReference.child(Tab2room).child("목요일").child(messageData.getKey()).removeValue();
                                                break;}
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });
///////////////////Friday
        final ArrayList<String> midList5 = new ArrayList<String>();
        list = (ListView) rootView.findViewById(R.id.listView5);

        final ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, midList5);
        list.setAdapter(adapter5);

        final EditText editText13 = (EditText) rootView.findViewById(R.id.edit_fri1);
        final EditText editText14 = (EditText) rootView.findViewById(R.id.edit_fri2);
        final EditText editText15 = (EditText) rootView.findViewById(R.id.edit_fri3);
        btnAdd = (Button) rootView.findViewById(R.id.plus_btn5);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = editText13.getText().toString()+" "+editText14.getText().toString()+" "+editText15.getText().toString();
                editText13.setText("");
                editText14.setText("");
                editText15.setText("");
                databaseReference.child("user").child(Tab2room).child("금요일").push().setValue(str1);

            }
        });

        mReference = mDatabase.getReference("user");
        mReference.child(Tab2room).child("금요일").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter5.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter5.add(msg2);
                }
                adapter5.notifyDataSetChanged();
                list.setSelection(adapter5.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int position2 = position;

                i=0;
                pos = position2+1;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("일정 삭제")
                        .setMessage("일정을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                midList5.remove(position2);
                                adapter5.notifyDataSetChanged();

                                Toast.makeText(getActivity(),"일정이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                mReference = mDatabase.getReference("user");
                                mReference.child(Tab2room).child("금요일").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                            i++;
                                            if(pos == i) {mReference.child(Tab2room).child("금요일").child(messageData.getKey()).removeValue();
                                                break;}
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });
        //////////////////Saturday
        final ArrayList<String> midList6 = new ArrayList<String>();
        list = (ListView) rootView.findViewById(R.id.listView6);

        final ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, midList6);
        list.setAdapter(adapter6);

        final EditText editText16 = (EditText) rootView.findViewById(R.id.edit_sat1);
        final EditText editText17 = (EditText) rootView.findViewById(R.id.edit_sat2);
        final EditText editText18 = (EditText) rootView.findViewById(R.id.edit_sat3);
        btnAdd = (Button) rootView.findViewById(R.id.plus_btn6);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = editText16.getText().toString()+" "+editText17.getText().toString()+" "+editText18.getText().toString();
                editText16.setText("");
                editText17.setText("");
                editText18.setText("");
                databaseReference.child("user").child(Tab2room).child("토요일").push().setValue(str1);

            }
        });

        mReference = mDatabase.getReference("user");
        mReference.child(Tab2room).child("토요일").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter6.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter6.add(msg2);
                }
                adapter6.notifyDataSetChanged();
                list.setSelection(adapter6.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int position2 = position;

                i=0;
                pos = position2+1;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("일정 삭제")
                        .setMessage("일정을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                midList6.remove(position2);
                                adapter6.notifyDataSetChanged();

                                Toast.makeText(getActivity(),"일정이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                mReference = mDatabase.getReference("user");
                                mReference.child(Tab2room).child("토요일").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                            i++;
                                            if(pos == i) {mReference.child(Tab2room).child("토요일").child(messageData.getKey()).removeValue();
                                                break;}
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });

        ///////////////////////SUnday
        final ArrayList<String> midList7 = new ArrayList<String>();
        list = (ListView) rootView.findViewById(R.id.listView7);

        final ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, midList7);
        list.setAdapter(adapter7);

        final EditText editText19 = (EditText) rootView.findViewById(R.id.edit_sun1);
        final EditText editText20 = (EditText) rootView.findViewById(R.id.edit_sun2);
        final EditText editText21 = (EditText) rootView.findViewById(R.id.edit_sun3);
        btnAdd = (Button) rootView.findViewById(R.id.plus_btn7);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = editText19.getText().toString()+" "+editText20.getText().toString()+" "+editText21.getText().toString();
                editText19.setText("");
                editText20.setText("");
                editText21.setText("");
                databaseReference.child("user").child(Tab2room).child("일요일").push().setValue(str1);

            }
        });

        mReference = mDatabase.getReference("user");
        mReference.child(Tab2room).child("일요일").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter7.clear();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter7.add(msg2);
                }
                adapter7.notifyDataSetChanged();
                list.setSelection(adapter7.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int position2 = position;

                i=0;
                pos = position2+1;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("일정 삭제")
                        .setMessage("일정을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                midList7.remove(position2);
                                adapter7.notifyDataSetChanged();

                                Toast.makeText(getActivity(),"일정이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                mReference = mDatabase.getReference("user");
                                mReference.child(Tab2room).child("일요일").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                                            i++;
                                            if(pos == i) {mReference.child(Tab2room).child("일요일").child(messageData.getKey()).removeValue();
                                                break;}
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });


        btnMon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                layout4.setVisibility(View.INVISIBLE);
                layout5.setVisibility(View.INVISIBLE);
                layout6.setVisibility(View.INVISIBLE);
                layout7.setVisibility(View.INVISIBLE);
            }
        });
        btnTue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                layout4.setVisibility(View.INVISIBLE);
                layout5.setVisibility(View.INVISIBLE);
                layout6.setVisibility(View.INVISIBLE);
                layout7.setVisibility(View.INVISIBLE);
            }
        });
        btnWed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.VISIBLE);
                layout4.setVisibility(View.INVISIBLE);
                layout5.setVisibility(View.INVISIBLE);
                layout6.setVisibility(View.INVISIBLE);
                layout7.setVisibility(View.INVISIBLE);
            }
        });
        btnThu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                layout4.setVisibility(View.VISIBLE);
                layout5.setVisibility(View.INVISIBLE);
                layout6.setVisibility(View.INVISIBLE);
                layout7.setVisibility(View.INVISIBLE);
            }
        });
        btnFri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                layout4.setVisibility(View.INVISIBLE);
                layout5.setVisibility(View.VISIBLE);
                layout6.setVisibility(View.INVISIBLE);
                layout7.setVisibility(View.INVISIBLE);
            }
        });
        btnSat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                layout4.setVisibility(View.INVISIBLE);
                layout5.setVisibility(View.INVISIBLE);
                layout6.setVisibility(View.VISIBLE);
                layout7.setVisibility(View.INVISIBLE);
            }
        });

        btnSun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                layout4.setVisibility(View.INVISIBLE);
                layout5.setVisibility(View.INVISIBLE);
                layout6.setVisibility(View.INVISIBLE);
                layout7.setVisibility(View. VISIBLE);
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
