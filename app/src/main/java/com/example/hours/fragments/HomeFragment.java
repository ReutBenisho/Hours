package com.example.hours.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hours.R;
import com.example.hours.databinding.FragmentHomeBinding;
import com.example.hours.models.HomeViewModel;
import com.example.hours.utils.App;
import com.example.hours.utils.SharedPreferencesUtil;

public class HomeFragment extends Fragment {

    public static final String TAG = App.getStr(R.string.tag_home);
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(container != null)
            container.removeAllViews(); // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        TextView lblHello = view.findViewById(R.id.lbl_hello);
        String hello = App.getStr(R.string.hello);
        String name = "";
        lblHello.setText(hello + name + "!");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}