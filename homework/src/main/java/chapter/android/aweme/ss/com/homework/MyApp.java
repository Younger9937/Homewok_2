package chapter.android.aweme.ss.com.homework;

import android.app.Application;

public class MyApp extends Application {
       int judge = 0;//用于判断是否调用了onDestroy

       public void Destroy(){
            judge = 1;
       }

       public int test(){
           if(judge==1){
               judge = 0;
               return 1;
           }
           else{
               return 0;
           }
       }
}
