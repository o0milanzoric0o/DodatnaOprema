package rs.dodatnaoprema.dodatnaoprema.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.SubCategoryArticlesActivity;
import rs.dodatnaoprema.dodatnaoprema.common.utils.Log;
import rs.dodatnaoprema.dodatnaoprema.customview.MultiSelectionSpinner;
import rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification.Detail;
import rs.dodatnaoprema.dodatnaoprema.models.categories.category_specification.Spec;

public class RecyclerViewSubcategorySpecification extends RecyclerView.Adapter<RecyclerViewSubcategorySpecification.MyViewHolder> {

    private List<Spec> specification;
    private Context context;
    private MultiSelectionSpinner multiSelectionSpinner;

    private TextView specificationGroup;

    public class MyViewHolder extends RecyclerView.ViewHolder implements MultiSelectionSpinner.OnMultipleItemsSelectedListener {

        public MyViewHolder(View view) {

            super(view);
            specificationGroup = (TextView) view.findViewById(R.id.specificationGroup);
            multiSelectionSpinner = (MultiSelectionSpinner) view.findViewById(R.id.multiSelectionSpinner);
            multiSelectionSpinner.setListener(this);
        }

        @Override
        public void selectedIndices(List<Integer> indices) {

        }

        @Override
        public void selectedStrings(List<String> strings) {

            boolean found = false;

            Log.logInfo("MULTISPINNER", strings.toString());
            for (Spec spec : specification) {
                for (Detail detail : specification.get(specification.indexOf(spec)).getDetalj()) {
                    if (strings.contains(detail.getIdSpecVrednostiImeVre())) {
                        Log.logInfo("MULTISPINNER", spec.getGrupe());
                        ((SubCategoryArticlesActivity)context).setSelectedSpecifications(spec.getGrupe(), strings);
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            //((SubCategoryArticlesActivity)context).setSelectedSpecifications();

        }
    }


    public RecyclerViewSubcategorySpecification(Context context, List<Spec> specification) {

        this.specification = specification;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.specification_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewSubcategorySpecification.MyViewHolder holder, int position) {

        List<String> myCollection = new ArrayList<>();
        for (int i = 0; i < specification.get(position).getDetalj().size(); i++) {
            myCollection.add(specification.get(position).getDetalj().get(i).getIdSpecVrednostiImeVre());
        }
        specificationGroup.setText(specification.get(position).getGrupe());
        multiSelectionSpinner.setItems(myCollection);
        //  multiSelectionSpinner.setSelection(new int[]{0});
    }

    @Override
    public int getItemCount() {
        return specification.size();
    }

}
