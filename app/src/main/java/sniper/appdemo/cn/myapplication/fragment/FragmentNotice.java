package sniper.appdemo.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.adapter.NoticeAdapter;
import sniper.appdemo.cn.myapplication.bean.NoticeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description:
 */

public class FragmentNotice extends Fragment {

    private List<NoticeBean> nNoticeBeanList = new ArrayList<>();
    private NoticeAdapter nAapter;

    public static FragmentNotice newInstance(String s){
        FragmentNotice homeFragment = new FragmentNotice();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.ARGS,s);
//        homeFragment.setArguments(bundle);
        return homeFragment;
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        initCostData();
        ListView viewList = (ListView) view.findViewById(R.id.lv_notice);
        nAapter = new NoticeAdapter(getChildFragmentManager(), getActivity(), nNoticeBeanList);
        viewList.setAdapter(nAapter);

        return view;
    }

    private void initCostData() {
        for (int i = 1; i < 9; i++) {
            NoticeBean noticeBean = new NoticeBean();
            noticeBean.noticeItemFrom = "知乎  "+(8*i) + "分钟前";
            noticeBean.noticeItemContent = "比特币市值飙升至"+(483*i)+"美元！创历史新高";
            nNoticeBeanList.add(noticeBean);
        }
    }

}
