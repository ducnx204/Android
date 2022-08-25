package com.pmwsteam.expendituremanagement.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.pmwsteam.expendituremanagement.R;
import com.pmwsteam.expendituremanagement.models.Introduce;

import java.util.List;

public class IntroAdapter extends PagerAdapter {
    private Context mContext;
    private List<Introduce> mListScreen;
    private ImageView imgSlice;
    private TextView title, description;
    private View layoutScreen;
    public IntroAdapter(Context mContext, List<Introduce> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            layoutScreen = inflater.inflate(R.layout.intro_item, null);
            initAdapter(layoutScreen);
            title.setText(mListScreen.get(position).getTitle());
            description.setText(mListScreen.get(position).getDescription());
            imgSlice.setImageResource(mListScreen.get(position).getImage());
            container.addView(layoutScreen);
        }
        return layoutScreen;
    }

    private void initAdapter(View layoutScreen) {
        imgSlice = layoutScreen.findViewById(R.id.intro_img);
        title = layoutScreen.findViewById(R.id.intro_title);
        description = layoutScreen.findViewById(R.id.intro_description);
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
