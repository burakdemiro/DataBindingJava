package com.bydmr.databindingjava.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.FragmentSepetBinding;
import com.bydmr.databindingjava.interfaces.IMainActivity;

import androidx.fragment.app.Fragment;

public class SepetFragment extends Fragment {

    FragmentSepetBinding fragmentSepetBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentSepetBinding = FragmentSepetBinding.inflate(inflater);

        fragmentSepetBinding.setMainInterface((IMainActivity) getActivity());

        fragmentSepetBinding.getMainInterface().sepetGorunecekMi(true);

        return fragmentSepetBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        ((IMainActivity)getActivity()).sepetGorunecekMi(false);
        super.onDestroy();
    }
}
