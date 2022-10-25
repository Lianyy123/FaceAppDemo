package com.firefly.faceApi.V2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.firefly.faceApi.V2.fragment.NewsFragment;
import com.firefly.faceApi.V2.fragment.PhoFragment;
import com.firefly.faceApi.V2.fragment.WeiChatFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    WeiChatFragment mWeiChatF=new WeiChatFragment();
    PhoFragment phoF=new PhoFragment();
    NewsFragment newsF=new NewsFragment();

    LinearLayout  Home,User,Setting;
    //ImageView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // textView=(TextView)findViewById(R.id.title);
        // 1.获取管理类
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.container_content,phoF)
                .add(R.id.container_content,mWeiChatF)
                .hide(mWeiChatF)
                .add(R.id.container_content,newsF)
                .hide(newsF)

                // 2.事物添加  默认：显示首页   其他页面：隐藏
                //3.提交
                .commit();

        initView();
    }



    /**
     * 初始化视图
     * */
    public void initView(){
        Home=(LinearLayout) this.findViewById(R.id.menu_home);
        User=(LinearLayout) this.findViewById(R.id.menu_user);
        Setting=(LinearLayout) this.findViewById(R.id.menu_setting);
        //add=findViewById(R.id.id_add);

       // id_add=this.findViewById(R.id.id_add);
        //baidu=(LinearLayout) this.findViewByI`d(R.id.menu_baidu);
        //添加点击事件
        Home.setOnClickListener(this);
        User.setOnClickListener(this);
        Setting.setOnClickListener(this);
        //add.setOnClickListener(this);
        //baidu.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Home.setSelected(true);
        User.setSelected(false);
        Setting.setSelected(false);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.menu_user://图片
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mWeiChatF)
                        .hide(phoF)
                        .hide(newsF)

                        .commit();
                Home.setSelected(false);
                User.setSelected(true);
                Setting.setSelected(false);

               // textView.setText("User");
                //baidu.setSelected(false);
                break;
            case  R.id.menu_home://home
             //   textView.setText("Home");
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mWeiChatF)
                        .show(phoF)
                        .hide(newsF)

                        .commit();
                Home.setSelected(true);
                User.setSelected(false);
                Setting.setSelected(false);
              //  baidu.setSelected(false);




                break;
            case R.id.menu_setting://
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mWeiChatF)
                        .hide(phoF)
                        .show(newsF)

                        .commit();
                Home.setSelected(false);
                User.setSelected(false);
                Setting.setSelected(true);


                break;

            default:break;

        }
    }

}