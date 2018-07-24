package com.example.hs.progressbar;

import android.databinding.ObservableField;

import com.hs.progressbutton.ProgressImageView;

public  class Model {
    public ObservableField<Integer> state= new ObservableField<>(ProgressImageView.ProgressState.START.value());
    public ObservableField<Integer> progress= new ObservableField<>(0);
}


