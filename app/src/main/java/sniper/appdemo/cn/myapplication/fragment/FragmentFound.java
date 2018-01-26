package sniper.appdemo.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import sniper.appdemo.cn.myapplication.Constants;
import sniper.appdemo.cn.myapplication.MainActivity;
import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.utils.MarqueView;
import sniper.appdemo.cn.myapplication.utils.LooperTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description:
 */

public class FragmentFound extends Fragment implements AdapterView.OnItemClickListener{

    private GridView mGridView1;
    private String IMAGE_ITEM = "imgage_item";
    private String TEXT_ITEM = "text_item";
    private String[] arrText = new String[]{"瞅一瞅", "搜一搜", "问一问"};
    private int[] arrImages=new int[]{
            R.drawable.ic_menu_camera, R.drawable.ic_menu_gallery, R.drawable.ic_menu_manage};

    private LooperTextView lpviewOpinion, lpviewProduct;
    private MarqueView mvOption, mvProducts;
    List<String> data = new ArrayList<>();
    List<View> viewOptions = new ArrayList<>();

    List<String> dataProducts = new ArrayList<>();
    List<View> viewProducts = new ArrayList<>();


    public static FragmentFound newInstance(String s){
        FragmentFound foundFragment = new FragmentFound();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        foundFragment.setArguments(bundle);
        return foundFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_found, container, false);

        mGridView1 = (GridView) view.findViewById(R.id.found_gridview);

        SimpleAdapter saImageItems = new SimpleAdapter(getContext(),
                getGridViewData(),
                R.layout.grid_item_market,
                new String[] { IMAGE_ITEM, TEXT_ITEM },
                new int[] { R.id.itemImage, R.id.itemText });
        // 设置GridView的adapter。GridView继承于AbsListView。
        mGridView1.setAdapter(saImageItems);
        mGridView1.setOnItemClickListener(this);

        /**
         * 设置轮播信息
         */
//        initTips();
//        lpviewOpinion = view.findViewById(R.id.loopOpinion_text);
//        lpviewOpinion.setTipList(data);
        mvOption = view.findViewById(R.id.mv1);
        initTips();
        initMvOption();

//        lpviewProduct = view.findViewById(R.id.loopProduct_text);
//        lpviewProduct.setTipList(data);
        mvProducts = view.findViewById(R.id.mv2);
        initTips2();
        initMvProduct();

        return view;
    }

    /**
     *      * 获取GridView的数据
     */
    private List<HashMap<String, Object>> getGridViewData() {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        for (int i=0; i<arrImages.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(IMAGE_ITEM, arrImages[i]);
            map.put(TEXT_ITEM, arrText[i]);
            list.add(map);
        }

        return list;
    }

    /**
     * GridView的点击回调函数
     *
     * @param adapter  -- GridView对应的dapterView
     * @param view     -- AdapterView中被点击的视图(它是由adapter提供的一个视图)。
     * @param position -- 视图在adapter中的位置。
     * @param rowid    -- 被点击元素的行id。
     */
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long rowid) {

        // 根据元素位置获取对应的值
        HashMap<String, Object> item = (HashMap<String, Object>) adapter.getItemAtPosition(position);

        String itemText=(String)item.get(TEXT_ITEM);
        Object object=item.get(IMAGE_ITEM);
        Toast.makeText(getContext(), "You Select "+itemText, Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < data.size(); i = i + 4) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);
            TextView tv3 = (TextView) moreView.findViewById(R.id.tv3);
            TextView tv4 = (TextView) moreView.findViewById(R.id.tv4);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), position + "你点击了" + data.get(position + 1).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() >= (i+4)) {
                tv2.setText(data.get(i + 1).toString());
                tv3.setText(data.get(i + 2).toString());
                tv4.setText(data.get(i + 3).toString());
            }else if((data.size()-i) == 2){
                tv2.setText(data.get(i + 1).toString());
                moreView.findViewById(R.id.rl3).setVisibility(View.GONE);
                moreView.findViewById(R.id.rl4).setVisibility(View.GONE);
            }else if((data.size()-i) == 3){
                tv2.setText(data.get(i + 1).toString());
                tv3.setText(data.get(i + 2).toString());
                moreView.findViewById(R.id.rl4).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            viewOptions.add(moreView);
        }
    }

    private void initMvOption(){
        setView();
        mvOption.setViews(viewOptions);
    }
    private void setView2() {
        for (int i = 0; i < dataProducts.size(); i = i + 4) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);
            TextView tv3 = (TextView) moreView.findViewById(R.id.tv3);
            TextView tv4 = (TextView) moreView.findViewById(R.id.tv4);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), position + "你点击了" + dataProducts.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), position + "你点击了" + dataProducts.get(position + 1).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            //进行对控件赋值
            tv1.setText(dataProducts.get(i).toString());
            if (dataProducts.size() >= (i+4)) {
                tv2.setText(dataProducts.get(i + 1).toString());
                tv3.setText(dataProducts.get(i + 2).toString());
                tv4.setText(dataProducts.get(i + 3).toString());
            }else if((dataProducts.size()-i) == 2){
                tv2.setText(dataProducts.get(i + 1).toString());
                moreView.findViewById(R.id.rl3).setVisibility(View.GONE);
                moreView.findViewById(R.id.rl4).setVisibility(View.GONE);
            }else if((dataProducts.size()-i) == 3){
                tv2.setText(dataProducts.get(i + 1).toString());
                tv3.setText(dataProducts.get(i + 2).toString());
                moreView.findViewById(R.id.rl4).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            viewProducts.add(moreView);
        }
    }
    private void initMvProduct(){
        setView2();
        mvProducts.setViews(viewProducts);
    }

    private List<String> initTips() {
        data = new ArrayList<>();
        data.add("国务院:建好\"信用时代\"的基础设施");
        data.add("腾讯公布吃鸡专属信用体系严打外挂");
        data.add("五项新政策 让\"信用\"开花结果");
        data.add("首批500家失信电商黑名单发布");
        data.add("发改委双11前强化信用问题");
        data.add("芝麻信用现\"刷分\"产业链");

        return data;
    }

    private List<String> initTips2() {
        dataProducts = new ArrayList<>();
        dataProducts.add("首批500家失信电商黑名单发布");
        dataProducts.add("发改委双11前强化信用问题");
        dataProducts.add("芝麻信用现\"刷分\"产业链");
        dataProducts.add("国务院:建好\"信用时代\"的基础设施");
        dataProducts.add("腾讯公布吃鸡专属信用体系严打外挂");
        dataProducts.add("五项新政策 让\"信用\"开花结果");
        dataProducts.add("人民银行等八部委联合印发《关于改进和加强海洋经济发展金融服务的指导意见》");
        dataProducts.add("七部门发文促核准对外投资资金真实去向");

        return dataProducts;
    }
}
