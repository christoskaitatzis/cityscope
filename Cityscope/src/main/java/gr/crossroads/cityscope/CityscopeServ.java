package gr.crossroads.cityscope;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 *
 */
public class CityscopeServ extends Service {
    private static final String TAG = "BroadcastService";
    public static final String BROADCAST_ACTION = "gr.crossroads.buzz";
    public static int trigger=1;
    private final Handler handler = new Handler();
    Intent intent;
    int counter = 0;
    public static Boolean serviceRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();

        //AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1*60*1000, );
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask()
//        {
//            @Override
//            public void run()
//            {
//              trigger=trigger+1;
//
//                if(trigger>600){trigger=1;}
//
//            }
//        }, 0, 60000);

        intent = new Intent(BROADCAST_ACTION);
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 60000); // 1 second
        serviceRunning = true;
        return START_STICKY;
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 60000); // 10 seconds
        }
    };




    private void DisplayLoggingInfo() {
       // Log.d(TAG, "entered DisplayLoggingInfo");

        //intent.putExtra("time", new Date().toLocaleString());
       // intent.putExtra("counter", String.valueOf(++counter));
        /////////////////////////////////////////////////////////////////
        //sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
        serviceRunning=false;

    }

    public static int getTrigger(){return trigger;}

    public static void setTrigger(int updateTrigger){
        trigger=updateTrigger;
    }
}
