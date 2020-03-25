package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;


/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */

public class Exercises2 extends AppCompatActivity {

    private TextView textView;
    private static final String TAG = "Exercise2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativelayout);
        ViewGroup group = (RelativeLayout)findViewById(R.id.group);
        textView = findViewById(R.id.tv_center);
        int count = getAllChildViewCount(group);
        textView.setText(String.valueOf(count));
    }

    public int getAllChildViewCount(View view) {//用递归的方式统计页面中view的个数
        int viewCount = 0;
        if(view == null){
            return 0;
        }
        if(view instanceof ViewGroup){//判断左边对象是否为右边类的实例
            Log.d(TAG,String.valueOf(((ViewGroup) view).getChildCount()));
            for(int i=0; i<((ViewGroup) view).getChildCount(); i++){
                View child = ((ViewGroup) view).getChildAt(i);
                if(child instanceof ViewGroup){//如果是ViewGroup
                    viewCount += getAllChildViewCount(((ViewGroup) view).getChildAt(i));
                }
                else{
                    viewCount++;
                }
            }
        }
        else{
            viewCount++;
        }
        return viewCount;
    }
}
