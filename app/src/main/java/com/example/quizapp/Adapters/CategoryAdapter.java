package com.example.quizapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizapp.Model.Category;
import com.example.quizapp.R;
import com.example.quizapp.SetActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

   private List<Category> categoryList;

    public CategoryAdapter( List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Holder holder, int position) {
       holder.setData(categoryList.get(position).getUrl(),categoryList.get(position).getName());
//        final Category category=categoryList.get(position);
//        holder.title.setText(category.getTitle());
//        Glide.with(context).load(category.getImageUrl()).into(holder.imageView);
//        holder.title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context,SetActivity.class);
//                intent.putExtra("title",category.getTitle());
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView title;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_catogory);
            title=itemView.findViewById(R.id.title_category);
        }
        private void setData(String imageurl,final String title)
        {
            Glide.with(itemView.getContext()).load(imageurl).into(imageView);
            this.title.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(),SetActivity.class);
                    intent.putExtra("title",title);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
   }

}
