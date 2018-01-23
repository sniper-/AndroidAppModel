package sniper.appdemo.cn.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

/**
 * Created by sniper on 2018/1/23.
 */

public class SearchActivity extends AppCompatActivity {
    private SearchView mSearchView;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private String [] data = {"Java","kotlin","C","C++","C#","Python","PHP","JavaScript"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mListView = (ListView) findViewById(R.id.search_list);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(mAdapter);
        mListView.setTextFilterEnabled(true);

        mSearchView = (SearchView) findViewById(R.id.search_edge);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
