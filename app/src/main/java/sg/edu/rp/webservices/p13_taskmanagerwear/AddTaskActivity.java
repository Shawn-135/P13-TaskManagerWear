package sg.edu.rp.webservices.p13_taskmanagerwear;

import android.app.TaskInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    ListView lvTask;
    ArrayList<TaskInfo> list;
    ArrayAdapter<TaskInfo> adapter;
    Button btnAddTask2, btnCancel;
    EditText etName, etDescription, etSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btnAddTask2 = findViewById(R.id.btnAddTask2);
        btnCancel = findViewById(R.id.btnCancel);

        btnAddTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new ArrayAdapter<TaskInfo>(this, android.R.layout.simple_list_item_1, list);
                TaskInfo taskInfo = TaskInfo.getTask();
                taskInfo.add(name, description, 5);
                list.add(etName.getText().toString(),
                        etDescription.getText().toString(),
                        etSeconds.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
