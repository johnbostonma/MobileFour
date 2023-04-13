package com.example.lightsout;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.lightsout.databinding.FragmentColorSettingBinding;

public class colorSetting extends Fragment {
    private FragmentColorSettingBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentColorSettingBinding.inflate(inflater, container, false);
        ;
        //setContentView(R.layout.activity_main);
        View view = binding.getRoot();


        //Setting the radio group listeners
        binding.radioGroupForSettings.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //change the Drawable resources dynamically in this block of code
                view.setBackgroundResource(R.drawable.custom_button_on_green);
                GradientDrawable gd = (GradientDrawable) view.getBackground().getCurrent();

                //added a button listener inside of this button listener
                binding.changeColorButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (binding.radioGroupForSettings.getCheckedRadioButtonId()) {
                            case 2131230727:
                                System.out.println("one");
                                gd.setColor(Color.RED);
                                break;
                            case 2131230729:
                                gd.setColor(Color.YELLOW);
                                break;
                            case 2131230728:
                                System.out.println("three");
                                gd.setColor(Color.GREEN);
                                break;
                            default:
                                break;
                        }
                        Navigation.findNavController(view).navigate(R.id.action_colorSetting_to_gameBoard);
                    }
                });
            }
        });

        return view;
    }

}
