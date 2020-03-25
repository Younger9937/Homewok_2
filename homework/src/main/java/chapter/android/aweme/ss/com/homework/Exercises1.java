package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import android.app.Application;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */

public class Exercises1 extends AppCompatActivity {

    private static final String TAG = "Exercise1";
    private MyApp myApp;
    public String message = "";
    private TextView lifeCycleText;

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";

    public void resetLifecycleDisplay(View view){
        lifeCycleText.setText("Lifecycle callbacks:\n");
        Log.d(TAG,"Lifecycle callbacks");
    }

    private void LogAndAppend(String lifecycleEvent) {//用于输出日志并且在UI界面中显示
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
        lifeCycleText.append(lifecycleEvent + "\n");
    }

    @Override
    public void onSaveInstanceState(Bundle outstate){//保存数据
        super.onSaveInstanceState(outstate);
        LogAndAppend(ON_SAVE_INSTANCE_STATE);
        outstate.putString("key", lifeCycleText.getText().toString());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        lifeCycleText = findViewById(R.id.LifeCycle);
        myApp = (MyApp)getApplication();
        if(savedInstanceState != null){//恢复数据
            message = savedInstanceState.getString("key");
            if(myApp.test()==1){//判断是否调用了onDestroy
                message += "onDestroy\n";
            }
            lifeCycleText.append(message);
        }
        LogAndAppend(ON_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogAndAppend(ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogAndAppend(ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogAndAppend(ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogAndAppend(ON_STOP);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogAndAppend(ON_RESTART);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogAndAppend(ON_DESTROY);
        myApp.Destroy();//发给Application，告知调用了一个Destroy
    }
}
