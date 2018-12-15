package com.umeng.soexample.week3moni;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by W on 2018/12/15.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private final int ONE_ITEM = 1;
    private final int TWO_ITEM = 2;
    private final int THREE_ITEM = 3;


    private List<User.DataBean> list;
    private Context context;

    public MyAdapter(List<User.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case ONE_ITEM:
                view = View.inflate(parent.getContext(),R.layout.item_news,null);
                holder = new ViewHolde1(view);
                break;
            case TWO_ITEM:
                view = View.inflate(parent.getContext(),R.layout.item_news02,null);
                holder = new ViewHolde2(view);
                break;
            case THREE_ITEM:
                view = View.inflate(parent.getContext(),R.layout.item_news03,null);
                holder = new ViewHolde3(view);
                break;

        }
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolde1){
            ((ViewHolde1) holder).txtTitle.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(((ViewHolde1) holder).imgLogo);
        }else if (holder instanceof ViewHolde2){
            ((ViewHolde2) holder).txtTitle.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(((ViewHolde2) holder).imgLogo);
            Glide.with(context).load(list.get(position).getThumbnail_pic_s02()).into(((ViewHolde2) holder).imgLogo2);

        }else {
            ((ViewHolde3) holder).txtTitle.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(((ViewHolde3) holder).imgLogo);
            Glide.with(context).load(list.get(position).getThumbnail_pic_s02()).into(((ViewHolde3) holder).imgLogo2);
            Glide.with(context).load(list.get(position).getThumbnail_pic_s03()).into(((ViewHolde3) holder).imgLogo3);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        User.DataBean bean = list.get(position);
        if (!TextUtils.isEmpty(bean.getThumbnail_pic_s03())){
            return THREE_ITEM;
        }else if (!TextUtils.isEmpty(bean.getThumbnail_pic_s02())){
            return TWO_ITEM;
        }else{
            return ONE_ITEM;
        }
    }

    class ViewHolde1 extends RecyclerView.ViewHolder{

        private TextView txtTitle;
        private ImageView imgLogo;
        public ViewHolde1(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            imgLogo = itemView.findViewById(R.id.img_logo);

        }
    }
    class ViewHolde2 extends RecyclerView.ViewHolder{

        private TextView txtTitle;
        private ImageView imgLogo;
        private ImageView imgLogo2;
        public ViewHolde2(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            imgLogo = itemView.findViewById(R.id.img_logo);
            imgLogo2 = itemView.findViewById(R.id.img_logo2);

        }
    }
    class ViewHolde3 extends RecyclerView.ViewHolder{

        private TextView txtTitle;
        private ImageView imgLogo;
        private ImageView imgLogo2;
        private ImageView imgLogo3;

        public ViewHolde3(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            imgLogo = itemView.findViewById(R.id.img_logo);
            imgLogo2 = itemView.findViewById(R.id.img_logo2);
            imgLogo3 = itemView.findViewById(R.id.img_logo3);


        }

    }
    public interface ItemClick{
        void setOnItem(View v,int position);
    }
    private ItemClick itemClick;
    public void setOnItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }
    @Override
    public void onClick(View view) {
        if (itemClick!= null){
            itemClick.setOnItem(view, (Integer) view.getTag());
        }
    }


}
