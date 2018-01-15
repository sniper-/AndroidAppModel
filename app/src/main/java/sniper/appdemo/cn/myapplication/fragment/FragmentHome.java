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

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description: FragmentHome
 */

public class FragmentHome extends Fragment {

    private List<HomeBean> mHomeBeanList = new ArrayList<>();
    private HomeAdapter mAapter;

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

        initData();
        ListView viewList = (ListView) view.findViewById(R.id.lv_main);
        mAapter = new HomeAdapter(getChildFragmentManager(), getActivity() , mHomeBeanList);
        viewList.setAdapter(mAapter);
        return view;
    }

    private void initData() {
        for (int i = 1; i < 9; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.homeItemRemark = "蚂蚁金服0"+i;
            homeBean.homeItemContent = "查验次数:"+(42783*i)+"次";
            mHomeBeanList.add(homeBean);
        }
    }
}
