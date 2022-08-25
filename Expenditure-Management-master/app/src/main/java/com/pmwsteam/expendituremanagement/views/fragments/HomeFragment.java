package com.pmwsteam.expendituremanagement.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pmwsteam.expendituremanagement.R;
import com.pmwsteam.expendituremanagement.controllers.BillAdapter;
import com.pmwsteam.expendituremanagement.models.Bill;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener{
    private LinearLayout view1, view2, view3, view4;
    private RecyclerView rc_bill;
    private List<Bill> bills = new ArrayList<>();

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addBill();
        initView(view);
        onClickView();
        setAdapterView();
        return view;
    }

    private void setAdapterView() {
        BillAdapter billAdapter = new BillAdapter(getContext(), bills);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rc_bill.setLayoutManager(layoutManager);
        rc_bill.setLayoutManager(HorizontalLayout);
        rc_bill.setAdapter(billAdapter);
    }

    private void onClickView() {
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);
    }

    private void initView(View view) {
        view1 = view.findViewById(R.id.view1);
        view2 = view.findViewById(R.id.view2);
        view3 = view.findViewById(R.id.view3);
        view4 = view.findViewById(R.id.view4);
        rc_bill = view.findViewById(R.id.rc_bill);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view1:
                Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.view2:
                Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.view3:
                Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.view4:
                Toast.makeText(getContext(), "4", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void addBill() {
        bills.add(new Bill(1, "Tiền điện", "20/03/2020", "12330000"));
        bills.add(new Bill(1, "Tiền nước", "21/04/2020", "120000"));
        bills.add(new Bill(1, "Tiền nhà", "20/06/2020", "2300000"));
        bills.add(new Bill(1, "Trả góp", "23/06/2020", "900000"));
    }
}