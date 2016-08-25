package rs.dodatnaoprema.dodatnaoprema;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import rs.dodatnaoprema.dodatnaoprema.common.utils.BaseActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.SharedPreferencesUtils;


public class InfoActivity extends BaseActivity implements OnMapReadyCallback {
    private String title;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTextView = (TextView) findViewById(R.id.title);

        LinearLayout map = (LinearLayout) findViewById(R.id.mapHolder);

        Intent intent = getIntent();
        title = intent.getStringExtra("infoTip");
        if (mTextView != null) mTextView.setText(title);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        byte[] data;

        LinearLayout.LayoutParams webViewParams = new

                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final WebView mWebViewTab = (WebView) findViewById(R.id.mWebViewInfo);
        mWebViewTab.setVisibility(View.GONE);
        mWebViewTab.setLayoutParams(webViewParams);
        if (title != null && title.equals(getString(R.string.how_to_buy))) {
            data = Base64.decode(SharedPreferencesUtils.getString(this, "INFO_HOW_TO_BUY"), Base64.DEFAULT);
        } else {
            data = Base64.decode(SharedPreferencesUtils.getString(this, "INFO_CONTACT"), Base64.DEFAULT);
            Log.e("InfoActivity","Info Contact");
            map.setVisibility(View.VISIBLE);
            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        mWebViewTab.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mWebViewTab.setVisibility(View.VISIBLE);

            }
        });
        mWebViewTab.loadDataWithBaseURL(null, new String(data), "text/html", "UTF-8", null);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (title != null && title.equals(getString(R.string.how_to_buy))) {
            menu.findItem(R.id.item_instructions).setEnabled(false);
            SpannableString s = new SpannableString(menu.findItem(R.id.item_instructions).getTitle());
            s.setSpan(new ForegroundColorSpan(Color.GRAY), 0, s.length(), 0);
            menu.findItem(R.id.item_instructions).setTitle(s);

        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // googleMap.addMarker(new MarkerOptions().position(new LatLng(44.8066679, 20.4691681)).title("Marker"));

        Log.e("InfoActivity"," OnMapReady");

        LatLng latLng = new LatLng(44.806664, 20.471357);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        //googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        googleMap.addMarker(new MarkerOptions().position(latLng).draggable(true).title("Mobilni centar").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).showInfoWindow();


    }
}
