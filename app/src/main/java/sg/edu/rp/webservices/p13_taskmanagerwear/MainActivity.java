package sg.edu.rp.webservices.p13_taskmanagerwear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int notificationId = 001;
    ListView lvTask;
    Button btnAddTask;
    ArrayList<TaskInfo> list;
    ArrayAdapter<TaskInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTask = findViewById(R.id.lvTask);
        list = new ArrayList<TaskInfo>();
        adapter = new ArrayAdapter<TaskInfo>(this, android.R.layout.simple_list_item_1, list);

        btnAddTask = findViewById(R.id.btnAddTask);

        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TaskInfo selected = list.get(position);

                Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(i);
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager nm = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new
                            NotificationChannel("default", "Default Channel",
                            NotificationManager.IMPORTANCE_DEFAULT);

                    channel.setDescription("You have tasks to do");
                }

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(MainActivity.this, 0,
                                intent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Action action = new
                        NotificationCompat.Action.Builder(
                        R.mipmap.ic_launcher,
                        "Reminder",
                        pendingIntent).build();

                Intent intentreply = new Intent(MainActivity.this,
                        MainActivity.class);
                PendingIntent pendingIntentReply = PendingIntent.getActivity
                        (MainActivity.this, 0, intentreply,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                RemoteInput ri = new RemoteInput.Builder("status")
                        .setLabel("Status")
                        .setChoices(new String [] {"Done", "Not yet"})
                        .build();

                NotificationCompat.Action action2 = new
                        NotificationCompat.Action.Builder(
                        R.mipmap.ic_launcher,
                        "Task complete?",
                        pendingIntentReply)
                        .addRemoteInput(ri)
                        .build();

                NotificationCompat.WearableExtender extender = new
                        NotificationCompat.WearableExtender();
                extender.addAction(action);
                extender.addAction(action2);

                String text = getString(R.string.basic_notify_msg);
                String title = getString(R.string.notification_title);

                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentText(text);
                builder.setContentTitle(title);
                builder.setSmallIcon(android.R.drawable.ic_dialog_info);

                // Attach the action for Wear notification created above
                builder.extend(extender);

                Notification notification = builder.build();

                nm.notify(notificationId, notification);

            }
        });
    }
}
