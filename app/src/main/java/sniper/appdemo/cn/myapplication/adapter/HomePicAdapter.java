//package sniper.appdemo.cn.myapplication.adapter;
//
//import android.content.Context;
//import android.support.v4.app.FragmentManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bigkoo.convenientbanner.holder.Holder;
//
//import sniper.appdemo.cn.myapplication.R;
//import sniper.appdemo.cn.myapplication.bean.HomePicBean;
//
//import com.bumptech.glide.Glide;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import sniper.appdemo.cn.myapplication.bean.HomeContentBean;
//
///**
// * Created by sniper on 2018/1/16.
// */
//
//
//public class HomePicAdapter extends BaseAdapter implements Holder<HomePicBean> {
//    private FragmentManager mFragment;
//    private List<HomePicBean> mList;
//    private Context mContext;
//    private LayoutInflater mLayoutInflater;
//    private String imgUrl;
//    private HomePicBean bannerBean;
//
//    @BindView(R.id.bannerImage)
//    ImageView bannerImage;
//    @BindView(R.id.bannerFlag)
//    TextView bannerFlag;
//
//    @Override
//    public View createView(Context context) {
//        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
//        ButterKnife.bind(this,view);
//        return view;
//    }
//
//    @Override
//    public void UpdateUI(Context context, int position, HomePicBean data) {
//        Glide.with(context).load(data.getImgUrl()).into(bannerImage);
//        bannerFlag.setText(data.getTag());
//
//    }
//
//
//    /**
//     * 构造方法
//     * @return
//     */
//    public HomePicAdapter(FragmentManager fragment, Context context, List<HomePicBean> list) {
//        mFragment = fragment;
//        mContext = context;
//        mList = list;
//        mLayoutInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount() {
//        return mList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    private static class ViewHolder {
//        private int type;
//        private String imgUrl;
//        private String url;
//        private String id;
//        private String tag;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        HomePicAdapter.ViewHolder viewHolder;
//        if(convertView == null) {
//            viewHolder = new HomePicAdapter.ViewHolder();
//            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, null);
//            Glide.with(mContext).load(ViewHolder.imgUrl);
//            bannerFlag.setText(bannerBean.getTag());
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (HomePicAdapter.ViewHolder) convertView.getTag();
//        }
//
//        HomePicBean bean = mList.get(position);
//
//        return convertView;
//    }
//}

package sniper.appdemo.cn.myapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HomePicAdapter extends PagerAdapter {

    private List<View> mListView;
    private Context mContext;

    public HomePicAdapter(List<View> listView, Context context){
        this.mListView = listView;
        this.mContext = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);

        ((ViewPager)container).removeView(mListView.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(mListView.get(position));
        return mListView.get(position);
        //return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return mListView.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
        //return false;
    }
}