package com.example.front;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.domain.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    //뷰홀더 생성자로 전달되는 뷰 객체 참조

    ArrayList<User> items = new ArrayList<User>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout1, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        User item = items.get(position);
        viewHolder.setItem(item,position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(User item) {
        items.add(item);
    }

    public void setItems(ArrayList<User> items) {
        this.items = items;
    }

    public User getItem(int postion) {
        return items.get(postion);
    }

    public void setItem(int position, User item) {
        items.set(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView point;
        TextView rank;

        //뷰객체에 들어있는 텍스트뷰 참조
        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            point = itemView.findViewById(R.id.point);
            rank = itemView.findViewById(R.id.rank);
        }

        public void setItem(User item,int ranking) {
            name.setText(item.getName());
            point.setText(Integer.toString(item.getPoint()));
            int tempRanking = 0;
            //랭킹 부분
            //현재는 랭킹 숫자가 맞지 않으나 서버에서 데이터를 받을때 정렬이 되어 내려 올것입니다.
            if(item.getPoint()>0){
                tempRanking = ranking+1;
            }
            else{
                tempRanking=0;
            }

            rank.setText(Integer.toString(tempRanking));
        }

    }
}