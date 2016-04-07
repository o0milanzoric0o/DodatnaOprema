package views.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.models.articles.Article;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

/**
 * Created by milan on 4/6/2016.
 */
public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private List<Article> data = new ArrayList();

    public GridViewAdapter(Context context, int layoutResourceId, List<Article> articles) {
        super(context, layoutResourceId, articles);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = articles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (NetworkImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Article article = data.get(position);
        holder.imageTitle.setText(article.getArtikalNaziv());
        ImageLoader mImageLoader = VolleySingleton.getsInstance(context).getImageLoader();
        holder.image.setImageUrl(article.getSlike().get(0).getSrednjaSlika(), mImageLoader);
        return row;
    }

    public void updateContent(List<Article> articles) {
        // clear and add to keep the old object reference
        this.data.clear();
        this.data.addAll(articles);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView imageTitle;
        NetworkImageView image;
    }
}