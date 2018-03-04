package com.mylocation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.mylocation.fragments.Airport;
import com.mylocation.fragments.ATM;
import com.mylocation.fragments.Bank;
import com.mylocation.fragments.Bus_station;
import com.mylocation.fragments.Church;
import com.mylocation.fragments.Doctor;
import com.mylocation.fragments.Hindu_temple;
import com.mylocation.fragments.Hospital;
import com.mylocation.fragments.Mosque;
import com.mylocation.fragments.Movie_theater;
import com.mylocation.fragments.Police_station;
import com.mylocation.fragments.Restaurant;


public class NavigationDrawerMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private String[] colors ;
    private Context context;
    private TextView txtName, txtWebsite;
    private View navHeader;
    private Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manu);

        bundle=new Bundle();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);





        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int colorFrom = ((ColorDrawable) toolbar.getBackground()).getColor();
                int colorTo = getColorForTab(tab.getPosition());

                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                colorAnimation.setDuration(1000);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int color = (int) animator.getAnimatedValue();

                        toolbar.setBackgroundColor(color);
                        tabLayout.setBackgroundColor(color);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(color);

                        }
                    }

                });
                colorAnimation.start();
                toolbar.setTitle(colors[tab.getPosition()].toUpperCase());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });





        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);



        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        txtWebsite.setText("karunkumar.in");
        txtName.setText("Karun Kumar");





        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        colors = new String[]{"Airport", "Bus Station","ATM", "Bank", "Police Station","Hospital","Doctor","Movie Theater","Restaurant","Hindu Temple",
                "Church","Mosque"};
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        bundle.putSerializable("Airport","airport");
        Fragment airport = Airport.newInstance();
        airport.setArguments(bundle);
        adapter.addFrag(airport, "Airport");


        bundle.putSerializable("Bus_station","bus_station");
        Fragment bus = Bus_station.newInstance();
        bus.setArguments(bundle);
        adapter.addFrag(bus, "Bus Station");


        bundle.putSerializable("ATM","atm");
        Fragment atm = ATM.newInstance();
        atm.setArguments(bundle);
        adapter.addFrag(atm, "ATM");


        bundle.putSerializable("Bank","bank");
        Fragment bank = Bank.newInstance();
        bank.setArguments(bundle);
        adapter.addFrag(bank, "Bank");


        bundle.putSerializable("Police_station","police_station");
        Fragment polic = Police_station.newInstance();
        polic.setArguments(bundle);
        adapter.addFrag(polic, "Police Station");


        bundle.putSerializable("Hospital","hospital");
        Fragment host = Hospital.newInstance();
        host.setArguments(bundle);
        adapter.addFrag(host, "Hospital");


        bundle.putSerializable("Doctor","doctor");
        Fragment doc = Doctor.newInstance();
        doc.setArguments(bundle);
        adapter.addFrag(doc, "Doctor");


        bundle.putSerializable("Movie_theater","movie_theater");
        Fragment movie = Movie_theater.newInstance();
        movie.setArguments(bundle);
        adapter.addFrag(movie, "Movie Theater");


        bundle.putSerializable("Restaurant","restaurant");
        Fragment rest = Restaurant.newInstance();
        rest.setArguments(bundle);
        adapter.addFrag(rest, "Restaurant");


        bundle.putSerializable("Hindu_temple","hindu_temple");
        Fragment hindu = Hindu_temple.newInstance();
        hindu.setArguments(bundle);
        adapter.addFrag(hindu, "Hindu Temple");


        bundle.putSerializable("Church","church");
        Fragment church = Church.newInstance();
        church.setArguments(bundle);
        adapter.addFrag(church, "Church");

        bundle.putSerializable("Mosque","mosque");
        Fragment mosque = Mosque.newInstance();
        mosque.setArguments(bundle);
        adapter.addFrag(mosque, "Mosque");



        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    public int getColorForTab(int position) {
        if (position == 0) return ContextCompat.getColor(this, R.color.test0);
        else if (position == 1) return ContextCompat.getColor(this, R.color.test1);
        else if (position == 2) return ContextCompat.getColor(this, R.color.test2);

        else if (position == 3) return ContextCompat.getColor(this, R.color.test3);
        else if (position == 4) return ContextCompat.getColor(this, R.color.test4);

        else if (position == 5) return ContextCompat.getColor(this, R.color.test5);
        else if (position == 6) return ContextCompat.getColor(this, R.color.test6);

        else if (position == 7) return ContextCompat.getColor(this, R.color.test7);
        else if (position == 8) return ContextCompat.getColor(this, R.color.test8);

        else if (position == 9) return ContextCompat.getColor(this, R.color.test9);
        else if (position == 10)return ContextCompat.getColor(this, R.color.test10);

        else return ContextCompat.getColor(this, R.color.colorPrimary);
    }





    /*********** tab menu start************************/

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }

/*********** tab menu end************************/






    /*********** menu option   right toolbar************************/

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    /*********** side menu bar  ************************/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

           /* tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            colors = new String[]{"Airport", "TOP RATED", "TOP RATED", "TOP RATED"};

            adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFrag(new Airport(), "Airport");
            adapter.addFrag(new ATM(), "Tab2");
            adapter.addFrag(new Bank(), "Tab3");
            adapter.addFrag(new Bus_station(), "Tab4");
            viewPager.setAdapter(adapter);*/



        } else if (id == R.id.nav_change_pass) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*********** side bar close draws ************************/

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /***********  side bar close end************************/


}