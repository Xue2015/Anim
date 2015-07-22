package com.example.ja.arcmenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {
    private int[] res = {R.id.imageView_a, R.id.imageView_b, R.id.imageView_c, R.id.imageView_d,
            R.id.imageView_e, R.id.imageView_f, R.id.imageView_g, R.id.imageView_h,};
    private List<ImageView> imageViewList = new ArrayList<ImageView>();
    private boolean flag = true;
    private int x0 = 0;
    private int y0 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            imageViewList.add(imageView);
        }
//        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
//        x0 = screenWidth;
//        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
//        y0 = screenHeight;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_a:
                if (flag) {
                    startAnim();
                } else {
                    if (flag == false) {
                        closeAnim();
                    }
                }

                break;
            case R.id.imageView_f:
                page();
            default:
                Toast.makeText(MainActivity.this, "Click" + v.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeAnim() {
        for (int i = 1; i < res.length; i++) {
            double theta = (2 * Math.PI / 7) * (i - 1);
            float x1 = (float) (x0 + 300 * Math.cos(theta));
            float y1 = (float) (y0 + 300 * Math.sin(theta));
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationY", y1, 0F);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationX", x1, 0F);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator1, animator2);
            set.setDuration(500);
            set.setInterpolator(new AnticipateInterpolator());
            set.start();

//            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),
//                    "translationY", i * 200, 0F);
//            animator.setDuration(500);
//            animator.setInterpolator(new BounceInterpolator());
////            animator.setStartDelay(i * 300);
//            animator.start();
        }
        flag = true;
    }


    private void startAnim() {
        for (int i = 1; i < res.length; i++) {
            double theta = (2 * Math.PI / 7) * (i - 1);
            float x1 = (float) (x0 + 300 * Math.cos(theta));
            float y1 = (float) (y0 + 300 * Math.sin(theta));
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationY", 0F, y1);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationX", 0F, x1);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator1, animator2);
            set.setDuration(500);
            set.setInterpolator(new OvershootInterpolator());
            set.start();
//            double theta = (2 * Math.PI/7) * (i-1);
//            float x1 = (float) (x0 + 100 * Math.cos(theta));
//            float y1 = (float) (y0 + 200 * Math.sin(theta));
//            Path path = new Path();
//            int xe = (int) x1;
//            int ye = (int) y1;
//            path.moveTo(x0,y0);
//            path.lineTo(xe,ye);
//            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),
//                    "translationX",x0,x1);
//            PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX",x0,x1);
//            PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY",y0,y1);
//            ObjectAnimator.ofPropertyValuesHolder(imageViewList.get(i),p1,p2).setDuration(500).start();
//            animator.setDuration(500);
//            animator.setInterpolator(new OvershootInterpolator());
////            animator.setStartDelay(i * 300);
//            animator.start();
//            ObjectAnimator.ofFloat(imageViewList.get(i),"translationX",x0,x1).start();
//            ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewList.get(i),"translationY",y0,y1);
//
//            AnimatorSet set = new AnimatorSet();
//            set.playTogether(animator1,animator2);
//            set.setDuration(500);
//            set.start();
        }
        flag = false;

    }

    public  void page () {
        Intent intent = new Intent(MainActivity.this, PaintActivity.class);
        startActivity(intent);
    }
}
