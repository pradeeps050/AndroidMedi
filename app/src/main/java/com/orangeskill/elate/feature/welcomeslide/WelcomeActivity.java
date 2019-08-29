package com.orangeskill.elate.feature.welcomeslide;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orangeskill.elate.R;
import com.orangeskill.elate.feature.home.HomeActivity;
import com.orangeskill.elate.framework.application.AppInstance;
import com.orangeskill.elate.framework.logger.Logger;
import com.orangeskill.elate.framework.preference.PreferenceHelper;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private WelcomePagerAdapter adapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelper = PreferenceHelper.getAppPrefs(AppInstance.getInstance().getContext());
        if (! preferenceHelper.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_welcome);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnSkip.setText(Html.fromHtml(getResources().getString(R.string.skip)));
        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setVisibility(View.VISIBLE);
        layouts = new int[]{R.layout.welcome_slide_1, R.layout.welcome_slide_2, R.layout.welcome_slide_3,
                R.layout.welcome_slide_4};

        //addBottomDots(0);

        adapter = new WelcomePagerAdapter(WelcomeActivity.this, layouts);
        viewPager.setAdapter(adapter);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == layouts.length - 1) {
                    // last page. make button text to GOT IT
                    btnNext.setText(getString(R.string.next));
                    btnNext.setVisibility(View.VISIBLE);
                    btnSkip.setVisibility(View.GONE);
                } else {
                    // still pages are left
                    btnNext.setVisibility(View.GONE);
                    btnNext.setText(getString(R.string.next));
                    btnSkip.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        ViewPager.OnPageChangeListener vChangeListener  = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Logger.d("welcome", ">> onPageScrolled");


            }

            @Override
            public void onPageSelected(int position) {
                Logger.d("welcome", ">> onPageSelected");
                addBottomDots(position);

                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == layouts.length - 1) {
                    // last page. make button text to GOT IT
                    btnNext.setVisibility(View.VISIBLE);
                    btnSkip.setVisibility(View.GONE);
                } else {
                    // still pages are left
                    //btnNext.setText(getString(R.string.next));
                    btnSkip.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int position) {
                addBottomDots(position);

                Logger.d("welcome", ">> onPageScrollStateChanged");
                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == layouts.length - 1) {
                    // last page. make button text to GOT IT
                    btnNext.setVisibility(View.VISIBLE);
                    btnSkip.setVisibility(View.GONE);
                } else {
                    // still pages are left
                    //btnNext.setText(getString(R.string.next));
                    btnSkip.setVisibility(View.VISIBLE);
                }

            }
        };
    }

    private void addBottomDots(int currentPage) {
        Logger.d("welcome", ">> addBottomDots");
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(25);
            dots[i].setTextColor(colorsInactive[i]);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);

    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        preferenceHelper.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        finish();
    }

    /*ViewPager.OnPageChangeListener vChangeListener  = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            Logger.d("welcome", ">> onPageScrolled");


        }

        @Override
        public void onPageSelected(int position) {
            Logger.d("welcome", ">> onPageSelected");
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setVisibility(View.VISIBLE);
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                //btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int position) {
            addBottomDots(position);

            Logger.d("welcome", ">> onPageScrollStateChanged");
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setVisibility(View.VISIBLE);
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                //btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }

        }
    };*/
}
