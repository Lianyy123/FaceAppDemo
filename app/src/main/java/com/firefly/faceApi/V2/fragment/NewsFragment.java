package com.firefly.faceApi.V2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firefly.faceApi.V2.R;


public class NewsFragment extends Fragment {

    ToggleButton _Button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // View view=inflater.inflate(R.layout.fragment_news,parent ,false);
        View view1=LayoutInflater.from(getContext()).inflate(R.layout.fragment_news,container,false);

        return view1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _Button=getActivity().findViewById(R.id.button_);

        _Button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                _Button.setSelected(b);

            }
        });
        /*(_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"huj",Toast.LENGTH_LONG).show();
                if (_Button.isSelected()){
                    _Button.setBackgroundResource(R.drawable.button_yes);

                }
                else {

                    _Button.setBackgroundResource(R.drawable.button_no);
                }



            }
        });*/

    }
}

















