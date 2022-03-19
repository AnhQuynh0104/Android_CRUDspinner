package com.example.cat.model;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cat.R;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private List<Cat> list;
    private List<Cat> backupList;
    private OnItemListener onItemListener;

    public CatAdapter(List<Cat> list) {
        this.list = list;
    }

    public Cat getItem(int positon){
        return list.get(positon);
    }

    public List<Cat> getList() {
        return list;
    }

    public List<Cat> getBackup(){
        return backupList;
    }

    public void filterList(List<Cat> filter){
        list = filter;
        notifyDataSetChanged();
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cat cat = list.get(position);
        if(cat == null) return;
        holder.imageCat.setImageResource(cat.getImageCat());
        holder.name.setText(cat.getName());
        holder.description.setText(cat.getDescription());
        holder.price.setText(cat.getPrice()+"");
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co muon xoa meo nay khong?");
                builder.setIcon(R.drawable.download);
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(cat);
                        backupList.remove(cat);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.btnEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemListener != null){
                    onItemListener.CatClickLister(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        else return 0;
    }

    public void add(Cat cat) {
        backupList.add(cat);
        list.add(cat);
        notifyDataSetChanged();
    }

    public void edit(Cat cat, int positon) {
        backupList.set(positon, cat);
        list.set(positon, cat);
        notifyDataSetChanged();
    }

    public interface OnItemListener{
        void CatClickLister(View v, int position);
    }

    public class CatViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageCat;
        private TextView name, price, description;
        private Button btnDelete, btnEditItem;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCat = itemView.findViewById(R.id.imageCat);
            name = itemView.findViewById(R.id.textName);
            price = itemView.findViewById(R.id.textPrice);
            description = itemView.findViewById(R.id.textDescription);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEditItem = itemView.findViewById(R.id.btnEditItem);
        }
    }
}
