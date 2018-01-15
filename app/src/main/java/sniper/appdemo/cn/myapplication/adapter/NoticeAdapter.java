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
import sniper.appdemo.cn.myapplication.bean.NoticeBean;

/**
 * Created by sniper on 2018/1/10.
 */

public class NoticeAdapter extends BaseAdapter {

    private FragmentManager mFragment;
    private List<NoticeBean> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    /**
     * 构造方法
     * @return
     */
    public NoticeAdapter(FragmentManager fragment, Context context, List<NoticeBean> list) {
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
        public TextView mTvNoticeItemFrom;
        public TextView mTvNoticeItemContent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if(convertView == null) {
            viewHoder = new ViewHoder();
            convertView = mLayoutInflater.inflate(R.layout.list_item_notice, null);
            viewHoder.mTvNoticeItemFrom = (TextView) convertView.findViewById(R.id.notice_item_from);
            viewHoder.mTvNoticeItemContent = (TextView) convertView.findViewById(R.id.notice_item_content);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        NoticeBean noticeBean = mList.get(position);
        viewHoder.mTvNoticeItemFrom.setText(noticeBean.noticeItemFrom);
        viewHoder.mTvNoticeItemContent.setText(noticeBean.noticeItemContent);

        return convertView;
    }
}
