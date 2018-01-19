package sniper.appdemo.cn.myapplication.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sniper.appdemo.cn.myapplication.Constants;
import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.adapter.HomePicAdapter;
import sniper.appdemo.cn.myapplication.adapter.HomeContentAdapter;
import sniper.appdemo.cn.myapplication.bean.HomeContentBean;
import sniper.appdemo.cn.myapplication.utils.LooperTextView;

public class FragmentHome extends Fragment implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private HomePicAdapter homePicAdapter;
    private List<View> viewPics;
    private ImageView[] dots;
    private int[] dotIds = {R.id.picDot1, R.id.picDot2, R.id.picDot3, R.id.picDot4};

    private LooperTextView looperview;

    private List<HomeContentBean> mHomeContentBeanList = new ArrayList<>();
    private HomeContentAdapter mAapter;
    private ListView listViewContent;




    public static FragmentHome newInstance(String s){
        FragmentHome homeFragment = new FragmentHome();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /**
         * 添加左右轮播图片
         */
        viewPager = view.findViewById(R.id.listPics);
        initViewPics(view);

        /**
         * 添加上下轮播文字
         */
        looperview = view.findViewById(R.id.looperview);
        looperview.setTipList(initTips());

        /**
         * list信息
         */
        listViewContent = view.findViewById(R.id.lv_main);
        initListData();

        return view;
    }

    private void initListData() {
        for (int i = 1; i < 9; i++) {
            HomeContentBean homeContentBean = new HomeContentBean();
            homeContentBean.homeItemRemark = "蚂蚁金服0"+i;
            homeContentBean.homeItemContent = "查验次数:"+(42783*i)+"次";
            mHomeContentBeanList.add(homeContentBean);
        }
        
        mAapter = new HomeContentAdapter(getChildFragmentManager(), getActivity() , mHomeContentBeanList);
        listViewContent.setAdapter(mAapter);
    }

    private List<String> initTips() {
        List<String> tips = new ArrayList<>();
        tips.add("国务院:建好\"信用时代\"的基础设施");
        tips.add("腾讯公布吃鸡专属信用体系严打外挂");
        tips.add("五项新政策 让\"信用\"开花结果");
        tips.add("首批500家失信电商黑名单发布");
        tips.add("发改委双11前强化信用问题");
        tips.add("芝麻信用现\"刷分\"产业链");

        return tips;
    }

    private void initViewPics(View mView){
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        viewPics = new ArrayList<View>();
        viewPics.add(inflater.inflate(R.layout.content_pic1, null));
        viewPics.add(inflater.inflate(R.layout.content_pic2, null));
        viewPics.add(inflater.inflate(R.layout.content_pic3, null));
        viewPics.add(inflater.inflate(R.layout.content_pic4, null));

        homePicAdapter = new HomePicAdapter(viewPics, getActivity());
        viewPager = viewPager.findViewById(R.id.listPics);
        viewPager.setAdapter(homePicAdapter);
        viewPager.addOnPageChangeListener(this);

        dots = new ImageView[viewPics.size()];
        for(int i=0; i<viewPics.size(); i++){
            dots[i] = (ImageView) mView.findViewById(dotIds[i]);
            //dots[i] =  getView().findViewById(dotIds[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        System.out.println("get position["+position+"]");
        System.out.println("get dot_focused["+R.drawable.dot_focused+"]");
        System.out.println("get dot_normal["+R.drawable.dot_normal+"]");
        System.out.println("get dotIds.length["+dotIds.length+"]");
        for (int i = 0; i < dotIds.length; i++) {
            dots[i].setImageResource(R.drawable.dot_normal);
        }
        dots[position].setImageResource(R.drawable.dot_focused);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
