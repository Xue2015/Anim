package com.example.ja.arcmenu;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            imageViewList.add(imageView);
        }
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
            default:
                Toast.makeText(MainActivity.this, "Click" + v.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeAnim() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationY", i * 200, 0F);
            animator.setDuration(500);
            animator.setInterpolator(new BounceInterpolator());
//            animator.setStartDelay(i * 300);
            animator.start();
        }
        flag = true;
    }

    private void startAnim() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationY", 0F, i * 200);
            animator.setDuration(500);
            animator.setInterpolator(new OvershootInterpolator());
//            animator.setStartDelay(i * 300);
            animator.start();
        }
        flag = false;

    }
}
