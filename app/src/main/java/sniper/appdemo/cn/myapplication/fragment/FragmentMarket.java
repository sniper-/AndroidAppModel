package sniper.appdemo.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sniper.appdemo.cn.myapplication.Constants;
import sniper.appdemo.cn.myapplication.R;
import sniper.appdemo.cn.myapplication.adapter.HomeContentAdapter;
import sniper.appdemo.cn.myapplication.bean.HomeContentBean;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;



public class FragmentMarket extends Fragment implements AdapterView.OnItemClickListener{
    private GridView mGridView1, mGridView2;
    private String IMAGE_ITEM = "imgage_item";
    private String TEXT_ITEM = "text_item";
    private String IMAGE_ITEM1 = "market_grid_big";
    private String TEXT_ITEM1 = "market_grid_big_name";
    private String TEXT_ITEM2 = "market_grid_big_content";

    private List<HomeContentBean> mHomeContentBeanList = new ArrayList<>();
    private HomeContentAdapter mAapter;
    private ListView listViewContent;


    private String[] arrText = new String[]{
            "Picture 1", "Picture 2", "Picture 3",
            "Picture 4", "Picture 5", "Picture 6",
            "Picture 7", "Picture 8"
    };

    private int[] arrImages=new int[]{
            R.drawable.ic_menu_camera, R.drawable.ic_menu_gallery, R.drawable.ic_menu_manage,R.drawable.ic_menu_send,
            R.drawable.ic_menu_share, R.drawable.ic_menu_slideshow, R.drawable.ic_navi_bottom_home,R.drawable.ic_navi_bottom_dash
    };

    private int[] arrImages1=new int[]{
            R.drawable.ic_menu_camera, R.drawable.ic_menu_gallery, R.drawable.ic_menu_manage,R.drawable.ic_menu_send};


    public static FragmentMarket newInstance(String s){
        FragmentMarket homeFragment = new FragmentMarket();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_market, container, false);

        mGridView1 = (GridView) view.findViewById(R.id.gridview);

        SimpleAdapter saImageItems = new SimpleAdapter(getContext(),
                getGridViewData(),
                R.layout.grid_item_market,
                new String[] { IMAGE_ITEM, TEXT_ITEM },
                new int[] { R.id.itemImage, R.id.itemText });
        // 设置GridView的adapter。GridView继承于AbsListView。
        mGridView1.setAdapter(saImageItems);
        mGridView1.setOnItemClickListener(this);


        mGridView2 = (GridView) view.findViewById(R.id.gridview1);

        SimpleAdapter saImageItems1 = new SimpleAdapter(getContext(),
                getGridViewData1(),
                R.layout.grid_item_market_big,
                new String[] { IMAGE_ITEM1, TEXT_ITEM1, TEXT_ITEM2 },
                new int[] { R.id.market_grid_big, R.id.market_grid_big_name, R.id.market_grid_big_content });
        // 设置GridView的adapter。GridView继承于AbsListView。
        mGridView2.setAdapter(saImageItems1);
        mGridView2.setOnItemClickListener(this);


        /**
         * list信息
         */
        listViewContent = view.findViewById(R.id.lv_main);
        initListData();


        return view;
    }

    /**
     *      * 获取GridView的数据
     */
    private List<HashMap<String, Object>> getGridViewData1() {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        for (int i=0; i<arrImages1.length; i++) {
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1.put(IMAGE_ITEM1, arrImages[i]);
            map1.put(TEXT_ITEM1, arrText[i]);
            map1.put(TEXT_ITEM2, arrText[i]);
            list.add(map1);
        }

        return list;
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

    private void initListData() {
        for (int i = 1; i < 9; i++) {
            HomeContentBean homeContentBean = new HomeContentBean();
            homeContentBean.homeItemRemark = "蚂蚁金服0"+i;
            homeContentBean.homeItemContent = "查验次数:"+(42783*i)+"次";
            mHomeContentBeanList.add(homeContentBean);
        }

        mAapter = new HomeContentAdapter(getChildFragmentManager(), getActivity() , mHomeContentBeanList);
        listViewContent.setAdapter(mAapter);
    }
}
