package com.example.lessondop_first.ui.home.HomeAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lessondop_first.databinding.ItemLayoutBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{
    private ItemLayoutBinding binding;
    private List<HomeModel> list = new ArrayList<>();
    private Listen listen;

    public HomeAdapter(Listen listen) {
        this.listen = listen;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.onBind(list.get(position), listen);
        if (position % 2 == 0) {
            binding.holderItem.setBackgroundColor(Color.CYAN);
        }else {
            binding.holderItem.setBackgroundColor(Color.GREEN);
        }


    }

    public void addModel(HomeModel homeModel){
        list.add(homeModel);
        notifyDataSetChanged();
    }
    public void del(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public HomeModel getModelToID(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        public HomeViewHolder(@NonNull ItemLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }


        public void onBind(HomeModel homeModel, Listen listen) {

            binding.tittle.setText(homeModel.getTitle());
            binding.description.setText(homeModel.getDescription());
            binding.getRoot().setOnClickListener(v -> {
                listen.setDataForForm(homeModel, getAdapterPosition());

            });

            binding.getRoot().setOnLongClickListener(v -> {
                listen.del(getAdapterPosition());
                return true;
            });
        }
    }


}
