package sniper.appdemo.cn.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.bean.HomeBean;

/**
 * Created by sniper on 2018/1/10.
 */

/**
 * Created by sniper on 2017/10/25.
 */

public class HomeAdapter extends BaseAdapter {

    private FragmentManager mFragment;
    private List<HomeBean> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    /**
     * 构造方法
     * @return
     */
    public HomeAdapter(FragmentManager fragment, Context context, List<HomeBean> list) {
        mFragment = fragment;
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHoder {
        public TextView mTvHomeItemRemark;
        public TextView mTvHomeItemContent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if(convertView == null) {
            viewHoder = new ViewHoder();
            convertView = mLayoutInflater.inflate(R.layout.list_item_home, null);
            viewHoder.mTvHomeItemRemark = (TextView) convertView.findViewById(R.id.home_item_remark);
            viewHoder.mTvHomeItemContent = (TextView) convertView.findViewById(R.id.home_item_content);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        HomeBean bean = mList.get(position);
        viewHoder.mTvHomeItemRemark.setText(bean.homeItemRemark);
        viewHoder.mTvHomeItemContent.setText(bean.homeItemContent);

        return convertView;
    }
}
