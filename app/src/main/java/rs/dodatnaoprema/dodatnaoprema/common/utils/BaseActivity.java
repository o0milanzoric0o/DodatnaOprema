package rs.dodatnaoprema.dodatnaoprema.common.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;

import rs.dodatnaoprema.dodatnaoprema.OffersActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;


public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pop_up_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_home:
                Toast.makeText(this, "Clicked: Menu No. 1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_signing:
                Toast.makeText(this, "Clicked: Menu No. 2 - SubMenu No .1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_sale:
                Intent intent = new Intent(this, OffersActivity.class);
                intent.putExtra("Artikli", AppConfig.FIRST_TAB_ITEMS[0]);
                intent.putExtra("AllCategories",(Serializable) SharedPreferencesUtils.getArrayListArticle(this,"SALE"));
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
