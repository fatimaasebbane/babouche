package com.example.base_de_donnees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.List;

public class CarouselActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        String imageType = "amazigh";
        new LoadImagesTask(this, imageType).execute();
    }

    public void setupViewPager(List<ImageEntity> imageEntities) {
        // Configurer le ViewPager2
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(imageEntities);
        viewPager.setAdapter(adapter);
    }
}