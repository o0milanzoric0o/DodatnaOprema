package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomRecyclerView;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.models.articles.ArticleSpec;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

public class ListViewSpecificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ArticleSpec> articlesSpec;
    private TextView specItem;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            specItem = (TextView) view.findViewById(R.id.specification_item);

        }


    }

    public ListViewSpecificationAdapter(Context context, List<ArticleSpec> articleSpec) {
        this.articlesSpec = articleSpec;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_specification_item, parent, false);


        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        specItem.setText("LALALALA");


    }


    @Override
    public int getItemCount() {

        return articlesSpec.size();
    }


}
