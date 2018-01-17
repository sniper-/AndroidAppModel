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
import sniper.appdemo.cn.myapplication.bean.HomeContentBean;

/**
 * Created by sniper on 2018/1/10.
 */

/**
 * Created by sniper on 2017/10/25.
 */

public class HomeContentAdapter extends BaseAdapter {

    private FragmentManager mFragment;
    private List<HomeContentBean> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    /**
     * 构造方法
     * @return
     */
    public HomeContentAdapter(FragmentManager fragment, Context context, List<HomeContentBean> list) {
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

    private static class ViewHolder {
        public TextView mTvHomeItemRemark;
        public TextView mTvHomeItemContent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item_home, null);
            viewHolder.mTvHomeItemRemark = convertView.findViewById(R.id.home_item_remark);
            viewHolder.mTvHomeItemContent = convertView.findViewById(R.id.home_item_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomeContentBean bean = mList.get(position);
        viewHolder.mTvHomeItemRemark.setText(bean.homeItemRemark);
        viewHolder.mTvHomeItemContent.setText(bean.homeItemContent);

        return convertView;
    }
}
