package floaterr.floater;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class FloatingWindow extends IntentService {

    WindowManager wm;
    private View mFloatingView;

    public FloatingWindow() {
        super("my name");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
            Intent intent2=new Intent(this,MainActivity.class);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopSelf();
        startActivity(intent2);
        System.exit(0);


    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, LayoutParams.TYPE_PHONE,
                LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        parameters.gravity = Gravity.CENTER | Gravity.CENTER;
        parameters.x = 0;
        parameters.y = 0;



        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);
        wm.addView(mFloatingView, parameters);
        Button b1= (Button) mFloatingView.findViewById(R.id.button);
        final Button b2= (Button) mFloatingView.findViewById(R.id.button2);
        final Button b3= (Button) mFloatingView.findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2.setText("Hello");

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wm.removeView(mFloatingView);
                stopSelf();
                Intent intent2=new Intent(FloatingWindow.this,MainActivity.class);
                startActivity(intent2);
                System.exit(0);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }

}