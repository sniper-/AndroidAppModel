package sniper.appdemo.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import sniper.appdemo.cn.myapplication.Constants;
import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.adapter.MineAdapter;
import sniper.appdemo.cn.myapplication.bean.MineBean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description:
 */

public class FragmentMine extends Fragment {

    private List<MineBean> mMineBeanList = new ArrayList<>();
    private MineAdapter mAapter;

    public static FragmentMine newInstance(String s){
        FragmentMine mineFragment = new FragmentMine();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        mineFragment.setArguments(bundle);
        return mineFragment;
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        initData();
        ListView viewList = (ListView) view.findViewById(R.id.lv_mine);
        mAapter = new MineAdapter(getChildFragmentManager(), getActivity() , mMineBeanList);
        viewList.setAdapter(mAapter);
        return view;
    }

    private void initData() {
        MineBean mineBean0 = new MineBean();
        mineBean0.mineItemName = "虚拟货币";
        mMineBeanList.add(mineBean0);

        MineBean mineBean1 = new MineBean();
        mineBean1.mineItemName = "信用积分";
        mMineBeanList.add(mineBean1);

        MineBean mineBean2 = new MineBean();
        mineBean2.mineItemName = "信用等级";
        mMineBeanList.add(mineBean2);

        MineBean mineBean3 = new MineBean();
        mineBean3.mineItemName = "我的订单";
        mMineBeanList.add(mineBean3);

        MineBean mineBean4 = new MineBean();
        mineBean4.mineItemName = "我的提问";
        mMineBeanList.add(mineBean4);
    }
}
