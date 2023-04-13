package com.example.lightsout;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lightsout.databinding.FragmentGameBoardBinding;
import com.example.lightsout.databinding.FragmentGameOverBinding;

public class GameOver extends Fragment {
    private FragmentGameOverBinding binding;
    MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameOverBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.cheers);
        mediaPlayer.setLooping(true);
        // Inflate the layout for this fragment
        String message = GameOverArgs.fromBundle(requireArguments()).getMessage();
        System.out.println(message);
        binding.textViewMoveNumber.setText(message);
        return view;

    }


    //music related
    private void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        stopPlaying();
    }
}