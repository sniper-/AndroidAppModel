package sniper.appdemo.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.adapter.HomeAdapter;
import sniper.appdemo.cn.myapplication.bean.HomeBean;
import sniper.appdemo.cn.myapplication.utils.CycleViewPager;
import sniper.appdemo.cn.myapplication.utils.Info;
import sniper.appdemo.cn.myapplication.utils.LooperTextView;

public class FragmentHome extends Fragment {

    private List<HomeBean> mHomeBeanList = new ArrayList<>();
    private HomeAdapter mAapter;
    private LooperTextView looperview;
    CycleViewPager mCycleViewPager;
    List<Info> mList = new ArrayList<>();

    public static FragmentHome newInstance(String s){
        FragmentHome homeFragment = new FragmentHome();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.ARGS,s);
//        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /**
         * 添加左右轮播图片
         */
//        uilViewPagerText = (TextView)view.findViewById(R.id.home_viewpager);
//        uilViewPagerText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),ViewPagerActivity.class);
//                startActivity(intent);
//            }
//        });
        mCycleViewPager = (CycleViewPager) view.findViewById(R.id.cycle_view);
        initData();
        initView();

        /**
         * 添加上下轮播文字
         */
        looperview = (LooperTextView)view.findViewById(R.id.looperview);
        looperview.setTipList(generateTips());

        /**
         * list信息
         */
        initListData();
        ListView viewList = (ListView) view.findViewById(R.id.lv_main);
        mAapter = new HomeAdapter(getChildFragmentManager(), getActivity() , mHomeBeanList);
        viewList.setAdapter(mAapter);
        return view;
    }

    private void initListData() {
        for (int i = 1; i < 9; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.homeItemRemark = "蚂蚁金服0"+i;
            homeBean.homeItemContent = "查验次数:"+(42783*i)+"次";
            mHomeBeanList.add(homeBean);
        }
    }

    private List<String> generateTips() {
        List<String> tips = new ArrayList<>();
        tips.add("赵丽颖");
        tips.add("杨颖");
        tips.add("郑爽");
        tips.add("杨幂");
        tips.add("刘诗诗");
        tips.add("迪丽热巴");
        tips.add("李沁");
        tips.add("唐嫣");
        tips.add("林心如");
        tips.add("陈乔恩");
        tips.add("范冰冰");
        tips.add("刘亦菲");
        tips.add("李小璐");
        tips.add("佟丽娅");
        return tips;
    }

    private void initData() {
        mList.add(new Info("标题1", "http://img2.3lian.com/2014/c7/25/d/40.jpg"));
        mList.add(new Info("标题2", "http://img2.3lian.com/2014/c7/25/d/41.jpg"));
        mList.add(new Info("标题3", "http://imgsrc.baidu.com/forum/pic/item/b64543a98226cffc8872e00cb9014a90f603ea30.jpg"));
        mList.add(new Info("标题4", "http://imgsrc.baidu.com/forum/pic/item/261bee0a19d8bc3e6db92913828ba61eaad345d4.jpg"));
    }

    /**
     * 初始化View
     */
    private void initView() {
        //设置选中和未选中时的图片
        assert mCycleViewPager != null;
        mCycleViewPager.setIndicators(R.mipmap.ad_select, R.mipmap.ad_unselect);
        //设置轮播间隔时间，默认为4000
        mCycleViewPager.setDelay(2000);
        mCycleViewPager.setData(mList, mAdCycleViewListener);
    }

    /**
     * 轮播图点击监听
     */
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(Info info, int position, View imageView) {

            if (mCycleViewPager.isCycle()) {
                position = position - 1;
            }
        }
    };
}
