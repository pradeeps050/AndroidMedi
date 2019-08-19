package com.orangeskill.elate.feature.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.HomeItemsBinding;
import com.orangeskill.elate.feature.home.model.TherapyCategory;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private Context context;
    private List<TherapyCategory> list;
    private LayoutInflater inflater;
    private OnItemClick clickListner;

    public HomeAdapter(Context context, List<TherapyCategory> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        HomeItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_items, viewGroup, false);
        return new HomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i) {
        TherapyCategory category = list.get(i);
        homeViewHolder.bind(context, category);
        homeViewHolder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onItemClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClick(OnItemClick onClick) {
        this.clickListner = onClick;
    }
}


class HomeViewHolder extends RecyclerView.ViewHolder {

    HomeItemsBinding binding;

    public HomeViewHolder(@NonNull HomeItemsBinding itemsBinding) {
        super(itemsBinding.getRoot());
        this.binding = itemsBinding;
    }

    public void bind(Context context, TherapyCategory category) {
        if (category != null) {
            binding.imageText.setText(category.getName());
            Glide.with(context).load(category.getImageUrl()).into(binding.image);
        }
    }
}

 interface OnItemClick {
    void onItemClick(TherapyCategory category);
}
