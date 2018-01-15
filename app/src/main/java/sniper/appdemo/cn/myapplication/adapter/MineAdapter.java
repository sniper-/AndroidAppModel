package sniper.appdemo.cn.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.bean.MineBean;

import java.util.List;

/**
 * Created by sniper on 2018/1/10.
 */

/**
 * Created by sniper on 2017/10/25.
 */

public class MineAdapter extends BaseAdapter {

    private FragmentManager mFragment;
    private List<MineBean> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    /**
     * 构造方法
     */
    public MineAdapter(FragmentManager fragment, Context context, List<MineBean> list) {
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
        public TextView mTvMineItemName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHoder;
        if(convertView == null) {
            viewHoder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item_mine, null);
            viewHoder.mTvMineItemName = (TextView) convertView.findViewById(R.id.mine_item_name);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHolder) convertView.getTag();
        }

        MineBean bean = mList.get(position);
        viewHoder.mTvMineItemName.setText(bean.mineItemName);

        return convertView;
    }
}
