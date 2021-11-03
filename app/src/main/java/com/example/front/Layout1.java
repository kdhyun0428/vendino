package com.example.front;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Layout1 extends LinearLayout {
    ImageView imageView;
    TextView name;
    TextView point;
    TextView rank;
    //생성자1
    public Layout1(Context context) {
        super(context);
        init(context);
    }
    //생성자2
    public Layout1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }
    //함수 - 인플레이
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        inflater.inflate(R.layout.layout1, this, true);

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        point = findViewById(R.id.point);
        rank = findViewById(R.id.rank);
    }

    public void setImageView(int resId){
        imageView.setImageResource(resId);;
    }

    public void setPoint(TextView point) {
        this.point = point;
    }

    public void setNickname(TextView name) {
        this.name = name;
    }

    public void setRank(TextView rank) {
        this.rank = rank;
    }
}