package com.pmwsteam.expendituremanagement.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pmwsteam.expendituremanagement.R;
import com.pmwsteam.expendituremanagement.views.fragments.BillFragment;
import com.pmwsteam.expendituremanagement.views.fragments.HomeFragment;
import com.pmwsteam.expendituremanagement.views.fragments.RevExpFragment;
import com.pmwsteam.expendituremanagement.views.fragments.StatisticalFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView id_add, view4, view2, view3, view1;

    @Override
    protected void onStart() {
        setActive(
                R.drawable.ic_home_24dp,
                R.drawable.ic_contract_2,
                R.drawable.ic_house_2,
                R.drawable.ic_statistical_24dp_2
        );
        loadFragment(new HomeFragment());
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setOnclickView();
    }

    private void initView() {
        view4 = findViewById(R.id.view4);
        view3 = findViewById(R.id.view3);
        view2 = findViewById(R.id.view2);
        view1 = findViewById(R.id.view1);
        id_add = findViewById(R.id.id_add);
    }

    private void setOnclickView() {
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);
        id_add.setOnClickListener(this);
    }

    public void setActive(int dr1, int dr2, int dr3, int dr4) {
        view1.setBackgroundResource(dr1);
        view2.setBackgroundResource(dr2);
        view3.setBackgroundResource(dr3);
        view4.setBackgroundResource(dr4);
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_view, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view1:
                setActive(
                        R.drawable.ic_home_24dp,
                        R.drawable.ic_contract_2,
                        R.drawable.ic_house_2,
                        R.drawable.ic_statistical_24dp_2
                );
                loadFragment(new HomeFragment());
                break;
            case R.id.view2:
                setActive(
                        R.drawable.ic_home_24dp_2,
                        R.drawable.ic_contract,
                        R.drawable.ic_house_2,
                        R.drawable.ic_statistical_24dp_2
                );
                loadFragment(new RevExpFragment());
                break;
            case R.id.view3:
                setActive(
                        R.drawable.ic_home_24dp_2,
                        R.drawable.ic_contract_2,
                        R.drawable.ic_house,
                        R.drawable.ic_statistical_24dp_2
                );
                loadFragment(new BillFragment());
                break;
            case R.id.view4:
                setActive(
                        R.drawable.ic_home_24dp_2,
                        R.drawable.ic_contract_2,
                        R.drawable.ic_house_2,
                        R.drawable.ic_statistical_24dp
                );
                loadFragment(new StatisticalFragment());
                break;
        }
    }
}
