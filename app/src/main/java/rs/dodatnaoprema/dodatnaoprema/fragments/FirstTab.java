package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import rs.dodatnaoprema.dodatnaoprema.customview.CustomListView;
import rs.dodatnaoprema.dodatnaoprema.customview.CustomProgressDialog;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.common.config.AppConfig;
import rs.dodatnaoprema.dodatnaoprema.models.categories.AllCategories;
import rs.dodatnaoprema.dodatnaoprema.network.PullAllCategories;
import rs.dodatnaoprema.dodatnaoprema.network.WebRequestCallbackInterface;
import views.adapters.AllCategoriesAdapter;

public class FirstTab extends Fragment {
    private CustomListView listView;
    private AllCategoriesAdapter adapter;
    private CustomProgressDialog progressDialog;
    private PullAllCategories pal;
    private boolean loaded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_tab, container, false);
        listView = (CustomListView) view.findViewById(R.id.listView);


        // Progress dialog
        progressDialog = new CustomProgressDialog(getActivity());
        progressDialog.showDialog(getResources().getString(R.string.progress_dialog_message));

        pal = new PullAllCategories(getActivity());
        pal.setCallbackListener(new WebRequestCallbackInterface<AllCategories>() {
            @Override
            public void webRequestSuccess(boolean success, AllCategories allCategories) {
                if (success) {
                    progressDialog.hideDialog();
                    Log.d("Lala", "Success");
                    //successfully loaded sensor list
                    adapter = new AllCategoriesAdapter(getActivity(), allCategories);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void webRequestError(String error) {
                progressDialog.hideDialog();


            }
        });
        pal.pullCategoriesList(AppConfig.URL_ALL_CATEGORIES);


        return view;
    }
}
