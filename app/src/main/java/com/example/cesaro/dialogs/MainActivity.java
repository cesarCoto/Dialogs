package com.example.cesaro.dialogs;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    long queueid;
    DownloadManager dm;
    Button buttonD, buttondos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonD = findViewById(R.id.donwload);
        buttondos = findViewById(R.id.donwload1);
        buttondos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonwloadFile("http://cccmx.x10host.com/Crown%20the%20empire/1%20-%20If%20I%20Were%20You%20-%20Paralysis.mp3");
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonwloadFile("http://cccmx.x10host.com/Crown%20the%20empire/Emarosa%20-%20Don't%20Cry.mp3");
            }
        });


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();

                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
                    DownloadManager.Query rec_query = new DownloadManager.Query();
                    rec_query.setFilterById(queueid);

                    Cursor c = dm.query(rec_query);

                    if (c.moveToFirst()){
                        int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);

                        if (DownloadManager.STATUS_SUCCESSFUL== c.getInt(columnIndex)){
                            Toast.makeText(context,"Done",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        };

        registerReceiver(receiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }
    public void DonwloadFile(String url){
        dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://k46.kn3.net/taringa/B/F/C/9/4/1/crazy112/DA0.jpg"));

        queueid = dm.enqueue(request);


    }

}
