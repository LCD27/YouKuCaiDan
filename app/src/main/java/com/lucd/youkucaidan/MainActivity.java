package com.lucd.youkucaidan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private RelativeLayout rl_level1;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;
    private ImageView iv_menu;
    private ImageView iv_home;
    private boolean visible1, visible2, visible3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        initVisible(true, true, true);
    }

    private void initVisible(boolean visible1, boolean visible2, boolean visible3) {
        this.visible1 = visible1;
        this.visible2 = visible2;
        this.visible3 = visible3;
    }

    private void setListener() {
//        rl_level1.setOnClickListener(this);
//        rl_level2.setOnClickListener(this);
//        rl_level3.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        iv_home.setOnClickListener(this);
    }

    private void initView() {
        rl_level1 = findViewById(R.id.rl_level_1);
        rl_level2 = findViewById(R.id.rl_level_2);
        rl_level3 = findViewById(R.id.rl_level_3);
        iv_menu = findViewById(R.id.iv_menu);
        iv_home = findViewById(R.id.iv_home);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                if (visible3) {
                    startAnimationOut(rl_level3, 500, 0);
                    initVisible(true, true, false);
                } else {
                    startAnimationIn(rl_level3, 500, 0);
                    initVisible(true, true, true);
                }

                break;
            case R.id.iv_home:
                if (visible2) {
                    if (visible3) {
                        startAnimationOut(rl_level3, 500, 0);
                        startAnimationOut(rl_level2, 500, 200);
                    } else {
                        startAnimationOut(rl_level2, 500, 0);
                    }
                    initVisible(true, false, false);
                } else {
                    startAnimationIn(rl_level3, 500, 200);
                    startAnimationIn(rl_level2, 500, 0);
                    initVisible(true, true, true);
                }
                break;
            default:
                break;
        }
    }


    //查看类的结构
    //方法1：
    //Windows： alt + 7
    //查看继承关系
    // Windows：ctrl + h

    /**
     * Y = 270
     * *****************************
     * *
     * *
     * X=180 * ******************* X = 0
     * *
     * *
     * *****************************
     * * Y = 90
     * 开启进入的动画   旋转方向：顺时针
     *
     * @param view   view开启动画
     * @param time   持续多久
     * @param offset 延迟时间  单位 毫秒
     */
    private void startAnimationIn(View view, long time, int offset) {
        startMyAnimation(view,time,offset,180,360);
    }

    private void startAnimationOut(View view, long time, int offset) {
        startMyAnimation(view,time,offset,0,180);
    }

    private void startMyAnimation(View view, long time, int offset, float fromDegrees, float toDegrees) {
        int width = view.getWidth();
        int height = view.getHeight();
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees,toDegrees,width/2,height);
        rotateAnimation.setDuration(time);//  持续时间
        rotateAnimation.setFillAfter(true);// 设置为true，不然开启动画后，会回到最初的状态
        rotateAnimation.setStartOffset(offset); // 开启延迟offset毫秒后进行动画
        view.startAnimation(rotateAnimation);
    }
}
