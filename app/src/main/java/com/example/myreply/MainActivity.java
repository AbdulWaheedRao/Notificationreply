package com.example.myreply;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_TEXT_REPLY = "key_text_reply";
    Button btnNotify,btnProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNotify=findViewById(R.id.btnNotify);
        btnProgress=findViewById(R.id.btnprogress);
        final String CHANNEEL_ID = "MyMainChannel";

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder1=new NotificationCompat.Builder(this,CHANNEEL_ID);
        builder1.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_baseline_download_24)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        int PROGRESS_MAX=100;
        int PROGRESS_CURRENT=0;
        builder1.setContentText("Download complet")
                .setProgress(PROGRESS_MAX,PROGRESS_CURRENT,false);

        int notificationId=0;
        notificationManagerCompat.notify(notificationId,builder1.build());


        Intent intent= new Intent(this,MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        String replyLabel = getResources().getString(R.string.reply_label);
        androidx.core.app.RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("reply")
                .build();
        PendingIntent replyPendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);



        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground,
                        "reply", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEEL_ID)
                .setSmallIcon(R.drawable.ic_reply_icon)
                .setContentTitle("Email Received")
                .setContentText("notification provider")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("View  write code that interacts with views. Once view binding is enabled in a module, " +
                                "it generates a binding class for each XML layout file present in that module. " +
                                "An instance of a binding class contains direct references to all views that have an " +
                                "ID in the corresponding layout."))
                .setContentIntent(pendingIntent)
                .addAction(action)
//                .addAction(R.drawable.ic_launcher_foreground, "Call", pendingIntent)
//                .addAction(R.drawable.ic_launcher_background, "Email", pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEEL_ID, "NEWS", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationManager.notify(1, builder.build());
            }
        });

btnProgress.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        notificationManager.notify(2,builder1.build());
    }
});

    }
}