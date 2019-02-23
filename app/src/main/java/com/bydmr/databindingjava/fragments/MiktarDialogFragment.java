package com.bydmr.databindingjava.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.FragmentMiktarDialogBinding;

import androidx.fragment.app.DialogFragment;

public class MiktarDialogFragment extends DialogFragment {

    FragmentMiktarDialogBinding miktarDialogBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        miktarDialogBinding = FragmentMiktarDialogBinding.inflate(inflater);

        return miktarDialogBinding.getRoot();
    }
}
