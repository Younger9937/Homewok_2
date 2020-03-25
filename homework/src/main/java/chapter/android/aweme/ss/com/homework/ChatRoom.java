package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {

    private static final String TAG = "ChatRoom";
    private TextView title;
    private TextView showIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        title = findViewById(R.id.tv_with_name);
        showIndex = findViewById(R.id.tv_content_info);
        title.setText("消息");

        Intent intent = getIntent();
        String Index = intent.getStringExtra("Index");
        Log.d(TAG,Index);
        showIndex.setText("这是#item"+Index);
    }
}
