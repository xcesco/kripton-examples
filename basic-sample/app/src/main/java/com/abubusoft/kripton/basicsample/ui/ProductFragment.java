package com.abubusoft.kripton.basicsample.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abubusoft.kripton.basicsample.db.dao.entity.CommentEntity;
import com.abubusoft.kripton.basicsample.db.dao.entity.ProductEntity;

import java.util.List;

public class ProductFragment extends Fragment {

    private static final String KEY_PRODUCT_ID = "product_id";

    private ProductFragmentBinding mBinding;

    private CommentAdapter mCommentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false);

        // Create and set the adapter for the RecyclerView.
        mCommentAdapter = new CommentAdapter(mCommentClickCallback);
        mBinding.commentList.setAdapter(mCommentAdapter);
        return mBinding.getRoot();
    }

    private final CommentClickCallback mCommentClickCallback = new CommentClickCallback() {
        @Override
        public void onClick(Comment comment) {
            // no-op

        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProductViewModel.Factory factory = new ProductViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_PRODUCT_ID));

        final ProductViewModel model = ViewModelProviders.of(this, factory)
                .get(ProductViewModel.class);

        mBinding.setProductViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final ProductViewModel model) {

        // Observe product data
        model.getObservableProduct().observe(this, new Observer<ProductEntity>() {
            @Override
            public void onChanged(@Nullable ProductEntity productEntity) {
                model.setProduct(productEntity);
            }
        });

        // Observe comments
        model.getComments().observe(this, new Observer<List<CommentEntity>>() {
            @Override
            public void onChanged(@Nullable List<CommentEntity> commentEntities) {
                if (commentEntities != null) {
                    mBinding.setIsLoading(false);
                    mCommentAdapter.setCommentList(commentEntities);
                } else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }

    /** Creates product fragment for specific product ID */
    public static ProductFragment forProduct(int productId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }
}