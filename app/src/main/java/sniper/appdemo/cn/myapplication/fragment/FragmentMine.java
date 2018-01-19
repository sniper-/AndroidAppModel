package sniper.appdemo.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import sniper.appdemo.cn.myapplication.Constants;
import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.adapter.HomeContentAdapter;
import sniper.appdemo.cn.myapplication.bean.HomeContentBean;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description:
 */

public class FragmentMine extends Fragment implements AdapterView.OnItemClickListener {

    private GridView mGridView, mGridView1;
    private String IMAGE_ITEM = "imgage_item";
    private String TEXT_ITEM = "text_item";

    private String FORTUNE_NAME = "gridview_fortune_item_name";
    private String FORTUNE_NUM = "gridview_fortune_item_num";


    private String[] arrTextFortuneNum = new String[]{
            "20", "89", "13423"};

    private String[] arrTextFortuneName = new String[]{
            "信用等级", "积分", "万链币"};

    private String[] arrText = new String[]{
            "全部", "已购买", "已收藏", "已关注"};

    private int[] arrImages=new int[]{
            R.drawable.ic_menu_camera, R.drawable.ic_menu_gallery, R.drawable.ic_menu_manage,R.drawable.ic_menu_send};

    private List<HomeContentBean> mHomeContentBeanList = new ArrayList<>();
    private HomeContentAdapter mAapter;
    private ListView listViewContent;

    public static FragmentMine newInstance(String s){
        FragmentMine mMineFragment = new FragmentMine();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        mMineFragment.setArguments(bundle);
        return mMineFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        /**
         * 我的订单
         */
        mGridView = (GridView) view.findViewById(R.id.gridview_mine);

        SimpleAdapter saImageItems = new SimpleAdapter(getContext(),
                getGridViewData(),
                R.layout.grid_item_market,
                new String[] { IMAGE_ITEM , TEXT_ITEM },
                new int[] { R.id.itemImage, R.id.itemText });
        // 设置GridView的adapter。GridView继承于AbsListView。
        mGridView.setAdapter(saImageItems);
        mGridView.setOnItemClickListener(this);

        /**
         * 财富信息
         */
        mGridView1 = (GridView) view.findViewById(R.id.gridview_fortune);

        SimpleAdapter saImageItems1 = new SimpleAdapter(getContext(),
                getGridViewData1(),
                R.layout.grid_item_mine_fortune,
                new String[] { FORTUNE_NAME, FORTUNE_NUM },
                new int[] { R.id.gridview_fortune_item_name, R.id.gridview_fortune_item_num });
        // 设置GridView的adapter。GridView继承于AbsListView。
        mGridView1.setAdapter(saImageItems1);
        mGridView1.setOnItemClickListener(this);

        /**
         * list信息
         */
        listViewContent = view.findViewById(R.id.mine_item_QA);
        initListData();


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
     *      * 获取GridView的数据
     */
    private List<HashMap<String, Object>> getGridViewData1() {
        List<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();

        for (int i=0; i<arrTextFortuneNum.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(FORTUNE_NAME, arrTextFortuneName[i]);
            map.put(FORTUNE_NUM, arrTextFortuneNum[i]);
            list1.add(map);
        }

        return list1;
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

    private void initListData() {
        for (int i = 1; i < 9; i++) {
            HomeContentBean homeContentBean = new HomeContentBean();
            homeContentBean.homeItemRemark = "我的问题"+i;
            homeContentBean.homeItemContent = "作答次数:"+(42783*i)+"次，点击查看更多";
            mHomeContentBeanList.add(homeContentBean);
        }

        mAapter = new HomeContentAdapter(getChildFragmentManager(), getActivity() , mHomeContentBeanList);
        listViewContent.setAdapter(mAapter);
    }
}
