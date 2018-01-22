package sniper.appdemo.cn.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bigkoo.convenientbanner.ConvenientBanner;

import sniper.appdemo.cn.myapplication.bean.HomePicBean;
import sniper.appdemo.cn.myapplication.fragment.TextTabFragment;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private TextTabFragment mTextTabFragment;
    ConvenientBanner<HomePicBean> convenientBanner;
    private Toolbar mToolbar;
    private static final long DELAY_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setCurrentFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.menu, menu);
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

        //创建签到按钮监听事件
        if (id == R.id.sign){
            //判断是否登录:若登录，则执行签到成功，反之跳转登录
//            if(1==1){
//
//            }else{
//
//            }
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            return true;
        }

        //创建搜索按钮监听事件
        if(id == R.id.search){
            Intent j = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(j);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setCurrentFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mTextTabFragment = mTextTabFragment.newInstance(getString(R.string.navigation_text_tab));
        //transaction.replace(R.id.frame_content, mTextTabFragment).commit();
        transaction.add(R.id.frame_content, mTextTabFragment).commit();

    }

    @Override
    public void onClick(View view) {

    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        if(convenientBanner != null){
            convenientBanner.startTurning(DELAY_TIME);
        }
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        if(convenientBanner != null){
            convenientBanner.stopTurning();
        }
    }
}
