package rs.dodatnaoprema.dodatnaoprema.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import rs.dodatnaoprema.dodatnaoprema.FullScreenImageViewActivity;
import rs.dodatnaoprema.dodatnaoprema.R;
import rs.dodatnaoprema.dodatnaoprema.network.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OneArticleImageFragment.OnProductImageGalleryDraw} interface
 * to handle interaction events.
 * Use the {@link OneArticleImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneArticleImageFragment extends Fragment {
    private static final String ARG_IMAGE_POSITION = "param1";

    private int imagePosition;

    private OnProductImageGalleryDraw mListener;

    public OneArticleImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment OneArticleImageFragment.
     */
    public static OneArticleImageFragment newInstance(int param1) {
        OneArticleImageFragment fragment = new OneArticleImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_POSITION, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imagePosition = getArguments().getInt(ARG_IMAGE_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one_article_image, container, false);
        NetworkImageView nimage = (NetworkImageView) v.findViewById(R.id.img_one_product);
        if (mListener != null) {
            ImageLoader mImageLoader = VolleySingleton.getsInstance(getActivity()).getImageLoader();
            nimage.setImageUrl(mListener.getProductImage(imagePosition), mImageLoader);
            nimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Launch Full Screen image Viewer
                    Intent i = new Intent(getActivity(), FullScreenImageViewActivity.class);
                    i.putExtra("position", imagePosition);
                    i.putStringArrayListExtra("urls", mListener.getProductImgLargeUrls());
                    getActivity().startActivity(i);
                }
            });
        }

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProductImageGalleryDraw) {
            mListener = (OnProductImageGalleryDraw) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProductImageGalleryDraw");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnProductImageGalleryDraw {
        String getProductImage(int position);
        ArrayList<String> getProductImgLargeUrls();
    }
}
