package yusuf.example.myviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ViewFlipper viewFlipper;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_filpper);
//        viewFlipper.setFlipInterval(1000);
//        viewFlipper.startFlipping();
        viewFlipper.setOnTouchListener(this);
        gestureDetector = new GestureDetector(getBaseContext(), new MTouch());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    public class MTouch extends GestureDetector.SimpleOnGestureListener {
        final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // Fling left
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {

                viewFlipper.showNext();

                Toast.makeText(MainActivity.this, "Fling Left", Toast.LENGTH_SHORT).show();
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // Fling right

                viewFlipper.showPrevious();

                Toast.makeText(MainActivity.this, "Fling Right", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}
