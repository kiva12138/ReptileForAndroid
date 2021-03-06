package com.example.testpicturereptile.uiclass;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testpicturereptile.MainActivity;
import com.example.testpicturereptile.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private MainActivity context;

    private List<SinglePicture> pictureList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView singleImageView;

        public ViewHolder(View view) {
            super(view);
            singleImageView = view.findViewById(R.id.single_image_view);
        }
    }

    public ImageAdapter(List<SinglePicture> pictureList, MainActivity context){
        this.pictureList = pictureList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleimagelayout,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.singleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String name = pictureList.get(position).getName();
                context.handlePictureClick(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SinglePicture singlePicture = pictureList.get(position);
        // holder.singleImageView.setImageBitmap(singlePicture.getImage());
        holder.singleImageView.setImageDrawable(new BitmapDrawable(singlePicture.getImage()));
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
