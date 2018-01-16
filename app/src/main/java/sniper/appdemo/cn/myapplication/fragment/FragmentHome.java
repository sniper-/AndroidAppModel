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
import sniper.appdemo.cn.myapplication.adapter.BannerAdapter;
import sniper.appdemo.cn.myapplication.adapter.HomeAdapter;
import sniper.appdemo.cn.myapplication.bean.BannerBean;
import sniper.appdemo.cn.myapplication.bean.HomeBean;
import sniper.appdemo.cn.myapplication.utils.CycleViewPager;
import sniper.appdemo.cn.myapplication.utils.Info;
import sniper.appdemo.cn.myapplication.utils.LooperTextView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentHome extends Fragment implements AdapterView.OnItemClickListener {

    private List<HomeBean> mHomeBeanList = new ArrayList<>();
    private HomeAdapter mAapter;
    private LooperTextView looperview;
//    CycleViewPager mCycleViewPager;
//    List<Info> mList = new ArrayList<>();

    private List<String> transformerList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    List<BannerBean> bannerList;
    View banner;
    ConvenientBanner<BannerBean> convenientBanner;

    ListView listView;

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
//        mCycleViewPager = (CycleViewPager) view.findViewById(R.id.cycle_view);
//        initData();
//        initView();

        ButterKnife.bind(getActivity());
        initListView();
        loadTestData();
        initBanner();

        /**
         * 添加上下轮播文字
         */
        looperview = view.findViewById(R.id.looperview);
        looperview.setTipList(generateTips());

        /**
         * list信息
         */
        initListData();
        ListView viewList = view.findViewById(R.id.lv_main);
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

//    private void initData() {
//        mList.add(new Info("标题1", "http://img2.3lian.com/2014/c7/25/d/40.jpg"));
//        mList.add(new Info("标题2", "http://img2.3lian.com/2014/c7/25/d/41.jpg"));
//        mList.add(new Info("标题3", "http://imgsrc.baidu.com/forum/pic/item/b64543a98226cffc8872e00cb9014a90f603ea30.jpg"));
//        mList.add(new Info("标题4", "http://imgsrc.baidu.com/forum/pic/item/261bee0a19d8bc3e6db92913828ba61eaad345d4.jpg"));
//    }
//
//    /**
//     * 初始化View
//     */
//    private void initView() {
//        //设置选中和未选中时的图片
//        assert mCycleViewPager != null;
//        mCycleViewPager.setIndicators(R.mipmap.ad_select, R.mipmap.ad_unselect);
//        //设置轮播间隔时间，默认为4000
//        mCycleViewPager.setDelay(2000);
//        mCycleViewPager.setData(mList, mAdCycleViewListener);
//    }
//
//    /**
//     * 轮播图点击监听
//     */
//    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {
//
//        @Override
//        public void onImageClick(Info info, int position, View imageView) {
//
//            if (mCycleViewPager.isCycle()) {
//                position = position - 1;
//            }
//        }
//    };

    private void initListView() {
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, transformerList);
        listView.setAdapter(adapter);
    }

    private void loadTestData(){
        //对应banner中需要填充的数据模型，可根据所需自定义属性 一般包括对应图片和你点击所需要跳转的链接或者标识你广告类型的Type等；
        bannerList = BannerBean.getBanners();
        //添加切换动作特效
        transformerList.add(DefaultTransformer.class.getSimpleName());
        transformerList.add(AccordionTransformer.class.getSimpleName());
        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
        transformerList.add(CubeInTransformer.class.getSimpleName());
        transformerList.add(CubeOutTransformer.class.getSimpleName());
        transformerList.add(DepthPageTransformer.class.getSimpleName());
        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
        transformerList.add(FlipVerticalTransformer.class.getSimpleName());
        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
        transformerList.add(RotateDownTransformer.class.getSimpleName());
        transformerList.add(RotateUpTransformer.class.getSimpleName());
        transformerList.add(StackTransformer.class.getSimpleName());
        transformerList.add(ZoomInTransformer.class.getSimpleName());
        transformerList.add(ZoomOutTranformer.class.getSimpleName());
        adapter.notifyDataSetChanged();
    }

    /**
     * 初始化广告栏
     */
    private void initBanner() {
        //在这里我使用banner作为listview的头部所以需要另外载入
        banner = LayoutInflater.from(getActivity()).inflate(R.layout.banner_layout, null);
        //从对应父布局加载你的banner
        convenientBanner = banner.findViewById(R.id.banner);
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new BannerAdapter();
            }
        }, bannerList);
        //这里设置底部小点的样式，若不设置则不显示
        convenientBanner.setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focused});
        //item点击事件
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //根据数据进行不同响应
                BannerBean bannerBean = bannerList.get(position);
            }
        });
        listView.addHeaderView(banner);
        listView.setOnItemClickListener(this);
    }

    //点击切换效果
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String transforemerName = transformerList.get(position);
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
            ABaseTransformer transforemer = null;
            try {
                transforemer = (ABaseTransformer) cls.newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            convenientBanner.getViewPager().setPageTransformer(true, transforemer);
            //部分3D特效需要调整滑动速度
            if (transforemerName.equals("StackTransformer")) {
                convenientBanner.setScrollDuration(1200);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
