package rs.dodatnaoprema.dodatnaoprema.common.utils;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import rs.dodatnaoprema.dodatnaoprema.R;


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
                Toast.makeText(this, "Clicked: Menu No. 2 - SubMenu No .2", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
