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
import sniper.appdemo.cn.myapplication.adapter.NoticeAdapter;
import sniper.appdemo.cn.myapplication.bean.NoticeBean;
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
        lpviewOpinion = view.findViewById(R.id.loopOpinion_text);
        lpviewOpinion.setTipList(initTips());

        lpviewProduct = view.findViewById(R.id.loopProduct_text);
        lpviewProduct.setTipList(initTips());

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

    private List<String> initTips() {
        List<String> tips = new ArrayList<>();
        tips.add("国务院:建好\"信用时代\"的基础设施");
        tips.add("腾讯公布吃鸡专属信用体系严打外挂");
        tips.add("五项新政策 让\"信用\"开花结果");
        tips.add("首批500家失信电商黑名单发布");
        tips.add("发改委双11前强化信用问题");
        tips.add("芝麻信用现\"刷分\"产业链");

        return tips;
    }
}
