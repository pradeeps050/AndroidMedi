package com.orangeskill.elate.feature.session;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.SessionListItemBinding;
import com.orangeskill.elate.feature.session.model.Therapies;
import com.orangeskill.elate.feature.session.model.TherapySession;
import com.orangeskill.elate.framework.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionViewHolder>{

    private Context context;
    private List<Therapies> list;
    private LayoutInflater inflater;
    private ItemClickListner listner;

    public SessionAdapter(Context context, List<Therapies> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null){
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        SessionListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.session_list_item, viewGroup, false);

        return new SessionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder sessionViewHolder, int i) {
        Therapies therapies = list.get(i);
        sessionViewHolder.bind(therapies, context);
        sessionViewHolder.binding.mainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onClickItem(therapies); }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListner(ItemClickListner listner) {
        this.listner = listner;
    }
}

class SessionViewHolder extends RecyclerView.ViewHolder {
    SessionListItemBinding binding;

    public SessionViewHolder(@NonNull SessionListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Therapies therapies, Context context){
        binding.mainText.setText(therapies.getSubCatagoryName());
        binding.subText.setText(therapies.getTags());
        Glide.with(context).load(therapies.getIconPath()).into(binding.rowImg);
    }
}

    interface ItemClickListner {
    void onClickItem(Therapies therapies);
}