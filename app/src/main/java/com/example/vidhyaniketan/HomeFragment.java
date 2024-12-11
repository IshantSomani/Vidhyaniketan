package com.example.vidhyaniketan;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private Handler handler;
    private Runnable runnable;
    private int currentPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewPager);

        int[] images = {
                R.drawable.kutty,
                R.drawable.annualday,
                R.drawable.christmas,
                R.drawable.stayhungry,
                R.drawable.excellence
        };

        String[] descriptions = {
                "Safeguarding Tomorrow: 'Kutty Cops' Initiative at Vidhya Niketan Public School",
                "Vidhya Niketan Public School Celebrates a Spectacular Annual Day Extravaganza!",
                "Joyful Jingles: Christmas Celebrations at Vidhya Niketan Public School",
                "Stay Hungry, Stay Foolish: Embrace of Declamation Competitions",
                "A Triumph of Excellence"
        };

        ImageAdapter adapter = new ImageAdapter(images, descriptions);
        viewPager.setAdapter(adapter);

        setupAutoScroll();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get screen width
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        // Find your MaterialCardViews and set their width
        MaterialCardView cardView1 = view.findViewById(R.id.cardView1);
        MaterialCardView cardView2 = view.findViewById(R.id.cardView2);

        if (cardView1 != null) {
            ViewGroup.LayoutParams params1 = cardView1.getLayoutParams();
            params1.width = screenWidth;
            cardView1.setLayoutParams(params1);
        }

        if (cardView2 != null) {
            ViewGroup.LayoutParams params2 = cardView2.getLayoutParams();
            params2.width = screenWidth;
            cardView2.setLayoutParams(params2);
        }
    }

    private void setupAutoScroll() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == 5) { // Updated to match the number of images (5 images)
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 4000); // 4 seconds delay
            }
        };
        handler.postDelayed(runnable, 4000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}