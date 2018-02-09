package com.usermindarchive.h.projectsms;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

@BindView(R.id.msglist)
    RecyclerView msglist;

    String[] permissions= new String[]{Manifest.permission.READ_SMS,};
    List<MessageFormat> msg=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        permission();
        Recyclerview();

    }

    private void Recyclerview(){

        MyAdapter recy=new MyAdapter();
        LinearLayoutManager one=new LinearLayoutManager(this);
        one.setOrientation(LinearLayoutManager.VERTICAL);
        msglist.setLayoutManager(one);
        msglist.setAdapter(recy);
    }

    private void permission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, permissions[0])== PackageManager.PERMISSION_GRANTED){
//            add operation if permission is given
            displaySmsLog();
        }else
        {
            ActivityCompat.requestPermissions(MainActivity.this,permissions,6);
        }
    }

    private void displaySmsLog() {
        Uri inbox = Uri.parse("content://sms/inbox");

        Uri outbox = Uri.parse("content://sms/outbox");

        Uri conversations = Uri.parse("content://sms/conversations");

        String columns[] = new String[]{"thread_id","read","person", "address", "body", "date", "status"};
        String num = null;
        String req = "+15129033547";
//                num=columns[1]+"="+req;

        Cursor cursorInbox = this.getContentResolver().query(inbox, columns,
                num, null, "date DESC");
        Cursor cursorOutbox = this.getContentResolver().query(outbox, columns,
                num, null, "date DESC");
        Cursor cursorConversations = this.getContentResolver().query(conversations, null,
                num, null, "date DESC");

        Log.e("Size of Data", String.valueOf(cursorInbox.getCount()) + "\n" + columns[1] + "\n" + cursorInbox.getColumnName(1));
        Log.e("Size of Data", String.valueOf(cursorOutbox.getCount()) + "\n" + columns[1] + "\n" + cursorOutbox.getColumnName(1));
        Log.e("Size of Data", String.valueOf(cursorConversations.getCount()) + "\n" + columns[1] + "\n" + cursorConversations.getColumnName(1));
        Log.e("COLUMNS NAMES", String.valueOf(cursorConversations.getColumnCount()) + "\n" + Arrays.toString(cursorConversations.getColumnNames()));
        Log.e("COLUMNS NAMES", String.valueOf(cursorInbox.getColumnCount()) + "\n" + Arrays.toString(cursorInbox.getColumnNames()));
        Log.e("COLUMNS NAMES", String.valueOf(cursorOutbox.getColumnCount()) + "\n" + Arrays.toString(cursorOutbox.getColumnNames()));

        while (cursorInbox.moveToNext()) {

//            if(cursorInbox.getString(1).contains(req)){
//            for (int i = 0; i < cursorInbox.getColumnCount(); i++) {
//
////                if(cursor.getColumnName(i)==columns[1])
////                    if(i==2)
////                    Log.e(cursorInbox.getString(1) + "", cursorInbox.getString(i) + "");
////                else
//
//                Log.e(cursorInbox.getColumnName(i) + "", cursorInbox.getString(i) + "");
//
////                msg.add(new MessageFormat(
////                        Integer.parseInt(cursorInbox.getString(0)),
////                        Boolean.parseBoolean( cursorInbox.getString(1)),
////                        cursorInbox.getString(2),
////                        cursorInbox.getString(3),
////                        cursorInbox.getString(4),
////                        Integer.parseInt(cursorInbox.getString(5)),
////                        cursorInbox.getString(6),
////                        1));
//
//            }
            msg.add(new MessageFormat(
                    Integer.parseInt(cursorInbox.getString(0)),
                    Boolean.parseBoolean( cursorInbox.getString(1)),
                    cursorInbox.getString(2),
                    cursorInbox.getString(3),
                    cursorInbox.getString(4),
                    Long.parseLong(cursorInbox.getString(5)),
                    cursorInbox.getString(6),
                    1));

//            Log.e("One row finished",
//                    "**************************************************");
//        }
    }

        while (cursorOutbox.moveToNext()) {

////            if(cursorOutbox.getString(1).contains(req)){
//                for (int i = 0; i < cursorOutbox.getColumnCount(); i++) {
//
////                if(cursor.getColumnName(i)==columns[1])
////                    if(i==2)
////                    Log.e(cursorOutbox.getString(1) + "", cursorOutbox.getString(i) + "");
////                else
//
//                    Log.e(cursorOutbox.getColumnName(i) + "", cursorOutbox.getString(i) + "");
//                }

            msg.add(new MessageFormat(
                    Integer.parseInt(cursorOutbox.getString(0)),
                    Boolean.parseBoolean( cursorOutbox.getString(1)),
                    cursorOutbox.getString(2),
                    cursorOutbox.getString(3),
                    cursorOutbox.getString(4),
                    Long.parseLong(cursorOutbox.getString(5)),
                    cursorOutbox.getString(6),
                    0));
//                Log.e("One row finished",
//                        "**************************************************");
            }
//    }
/*
        while (cursorConversations.moveToNext()) {

//            if(cursorConversations.getString(1).contains(req)){
                for (int i = 0; i < cursorConversations.getColumnCount(); i++) {

//                if(cursor.getColumnName(i)==columns[1])
//                    if(i==2)
//                    Log.e(cursorConversations.getString(1) + "", cursorConversations.getString(i) + "");
//                else

                    Log.e(cursorConversations.getColumnName(i) + "", cursorConversations.getString(i) + "");
                }
                Log.e("One row finished",
                        "**************************************************");
            }}*/
//    }
        Log.e("One row finished",
                "**************************************************");
        Log.e("One row finished",
                String.valueOf(msg.size()));

    }

    public class MyAdapter extends RecyclerView.Adapter<Holder>{
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//            if(viewType==1) {
                View testview = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
                return new First(testview);
//            }else{
//                View test2view = LayoutInflater.from(parent.getContext()).inflate(R.layout.secondrow, parent, false);
//                return new SecondView(test2view);

//            }
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
//            switch (holder.getItemViewType()) {
//                case 1:
//                    ((First)holder).text.setText(num.get(position));
//                    break;
//                case 2:
//                    ((SecondView)holder).text2.setText(num.get(position));
//                    break;
//            }
            Date date = new Date(msg.get(position).DATE);
            DateFormat formatter = new SimpleDateFormat("dd-MM-yy hh:mm a");

            ((First)holder).number.setText(msg.get(position).ADDRESS);
            ((First)holder).time.setText(formatter.format(date));
            ((First)holder).message.setText(msg.get(position).BODY);

        }

        @Override
        public int getItemViewType(int position) {
            if(position%2==0){
                return 1;
            }else {
                return 2;
            }
        }

        @Override
        public int getItemCount() {
            return msg.size();
        }
    }

    public class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }


    public class First extends Holder{
        @BindView(R.id.number) TextView number;
        @BindView(R.id.time) TextView time;
        @BindView(R.id.message) TextView message;

        public First(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,"THIS IS THE Message "+message.getText(),Toast.LENGTH_LONG).show();
                }
            });

        }
    }

//    public class SecondView extends Holder{
//        TextView text2;
//
//        public SecondView(View itemView) {
//            super(itemView);
//            text2=(TextView)itemView.findViewById(R.id.textView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getActivity(),"THIS IS THE TEXT "+text2.getText(),Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 6 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //the main function u need

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    Toast.makeText(MainActivity.this, "THIS IS shouldS", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
                    dia.setMessage("Enable the required Permission for the Application\n Go to Setting and Enable the Permissions");
                    dia.setTitle("PERMISSIONS NEEDED");
                    dia.setPositiveButton("PERMISSION", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    });
                    dia.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "PERMISSIONS ARE NOT ENABLED", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = dia.create();
                    dialog.show();

                }
            }
        }


    }
}
