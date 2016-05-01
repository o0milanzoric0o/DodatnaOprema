package rs.dodatnaoprema.dodatnaoprema;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;

public class SubCategorySpecificationActivity extends BaseActivity {

    private int numberOfResults;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategory_specification_activity);

        Intent intent = getIntent();
        numberOfResults = intent.getIntExtra("NumberOfArticles", 0);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText(""+numberOfResults);
    }
}
