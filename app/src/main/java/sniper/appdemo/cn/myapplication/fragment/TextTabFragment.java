package sniper.appdemo.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sniper.appdemo.cn.myapplication.R;

/**
 * Created by Kevin on 2016/11/29.
 * Blog:http://blog.csdn.net/student9128
 * Description: Bottom Navigation Bar by TextView + LinearLayout.
 */

public class TextTabFragment extends Fragment implements View.OnClickListener {
    private TextView mTHome, mTLocation, mTLike, mTMe, mTextView;
    private FragmentHome mHomeFragment;
    private FragmentNotice mLocationFragment;
    private FragmentDash mLikeFragment;
    private FragmentMine mPersonFragment;

    public static TextTabFragment newInstance(String s) {
        TextTabFragment viewPagerFragment = new TextTabFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.ARGS, s);
//        viewPagerFragment.setArguments(bundle);
        return viewPagerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_tab, container, false);
        mTextView = (TextView) view.findViewById(R.id.activity_text_view);
        mTHome = (TextView) view.findViewById(R.id.tv_home);
        mTLocation = (TextView) view.findViewById(R.id.tv_dash);
        mTLike = (TextView) view.findViewById(R.id.tv_like);
        mTMe = (TextView) view.findViewById(R.id.tv_person);
        Bundle bundle = getArguments();
        if (bundle != null) {
//            String s = bundle.getString(Constants.ARGS);
            String s = "test";
            Log.i("Kevin", s + "");
            if (!TextUtils.isEmpty(s)) {
                mTextView.setText(s);
            }
        }
        mTHome.setOnClickListener(this);
        mTLocation.setOnClickListener(this);
        mTLike.setOnClickListener(this);
        mTMe.setOnClickListener(this);
        setDefaultFragment();
        return view;
    }

    /**
     * set the default Fragment
     */
    private void setDefaultFragment() {
        switchFrgment(0);
        //set the defalut tab state
        setTabState(mTHome, R.drawable.ic_navi_home_checked, getColor(R.color.colorPrimary));
    }

    @Override
    public void onClick(View view) {
        resetTabState();//reset the tab state
        switch (view.getId()) {
            case R.id.tv_home:
                setTabState(mTHome, R.drawable.ic_navi_home_checked, getColor(R.color.colorPrimary));
                switchFrgment(0);
                break;
            case R.id.tv_dash:
                setTabState(mTLocation, R.drawable.ic_navi_bottom_dash_checked, getColor(R.color.colorPrimary));
                switchFrgment(1);
                break;
            case R.id.tv_like:
                setTabState(mTLike, R.drawable.ic_navi_bottom_search_checked, getColor(R.color.colorPrimary));
                switchFrgment(2);
                break;
            case R.id.tv_person:
                setTabState(mTMe, R.drawable.ic_navi_bottom_person_checked, getColor(R.color.colorPrimary));
                switchFrgment(3);
                break;
        }
    }

    /**
     * switch the fragment accordting to id
     * @param i id
     */
    private void switchFrgment(int i) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        switch (i) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = mHomeFragment.newInstance();
                }
                /**
                 * 防止每点击一次，fragment就重新加载一次数据导致数据重复
                 * 下拉刷新可以使用transaction.replace(fragment)实现
                 */
                if(mLocationFragment != null){
                    transaction.hide(mLocationFragment);
                }
                if(mLikeFragment != null){
                    transaction.hide(mLikeFragment);
                }
                if(mPersonFragment != null){
                    transaction.hide(mPersonFragment);
                }
                if(!mHomeFragment.isAdded()){
                    transaction.add(R.id.sub_content, mHomeFragment);
                }else{
                    transaction.show(mHomeFragment);
                }

                break;
            case 2:
                if (mLocationFragment == null) {
                    mLocationFragment = FragmentNotice.newInstance(getString(R.string.item_location));
                }
                if(mHomeFragment != null){
                    transaction.hide(mHomeFragment);
                }
                if(mLikeFragment != null){
                    transaction.hide(mLikeFragment);
                }
                if(mPersonFragment != null){
                    transaction.hide(mPersonFragment);
                }
                if(!mLocationFragment.isAdded()){
                    transaction.add(R.id.sub_content, mLocationFragment);
                }else{
                    transaction.show(mLocationFragment);
                }
                break;
            case 1:
                if (mLikeFragment == null) {
                    mLikeFragment = FragmentDash.newInstance(getString(R.string.item_like));
                }
                if(mLocationFragment != null){
                    transaction.hide(mLocationFragment);
                }
                if(mHomeFragment != null){
                    transaction.hide(mHomeFragment);
                }
                if(mPersonFragment != null){
                    transaction.hide(mPersonFragment);
                }
                if(!mLikeFragment.isAdded()){
                    transaction.add(R.id.sub_content, mLikeFragment);
                }else{
                    transaction.show(mLikeFragment);
                }
                break;
            case 3:
                if (mPersonFragment == null) {
                    mPersonFragment = FragmentMine.newInstance(getString(R.string.item_person));
                }
                if(mLocationFragment != null){
                    transaction.hide(mLocationFragment);
                }
                if(mLikeFragment != null){
                    transaction.hide(mLikeFragment);
                }
                if(mHomeFragment != null){
                    transaction.hide(mHomeFragment);
                }
                if(!mPersonFragment.isAdded()){
                    transaction.add(R.id.sub_content, mPersonFragment);
                }else{
                    transaction.show(mPersonFragment);
                }

                break;
        }
        transaction.commit();
    }

    /**
     * set the tab state of bottom navigation bar
     *
     * @param textView the text to be shown
     * @param image    the image
     * @param color    the text color
     */
    private void setTabState(TextView textView, int image, int color) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, image, 0, 0);//Call requires API level 17
        textView.setTextColor(color);
    }


    /**
     * revert the image color and text color to black
     */
    private void resetTabState() {
        setTabState(mTHome, R.drawable.ic_navi_bottom_home, getColor(R.color.black_1));
        setTabState(mTLocation, R.drawable.ic_navi_bottom_dash, getColor(R.color.black_1));
        setTabState(mTLike, R.drawable.ic_navi_bottom_search, getColor(R.color.black_1));
        setTabState(mTMe, R.drawable.ic_navi_bottom_person, getColor(R.color.black_1));

    }

    /**
     * @param i the color id
     * @return color
     */
    private int getColor(int i) {
        return ContextCompat.getColor(getActivity(), i);
    }
}
