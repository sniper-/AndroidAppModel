package sniper.appdemo.cn.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;

import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.bean.BannerBean;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sniper on 2018/1/16.
 */


public class BannerAdapter implements Holder<BannerBean> {
    @BindView(R.id.bannerImage)
    ImageView bannerImage;
    @BindView(R.id.bannerFlag)
    TextView bannerFlag;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, BannerBean data) {
        Glide.with(context).load(data.getImgUrl()).into(bannerImage);
        bannerFlag.setText(data.getTag());

    }
}
