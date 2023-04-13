package com.example.lightsout;


import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lightsout.databinding.FragmentGameBoardBinding;

public class gameBoard extends Fragment {

    //private TableLayout tableLayout;
    private final ButtonInfo[][] arr = new ButtonInfo[3][3];
    private FragmentGameBoardBinding binding;
    private int moveCount = 0;
    private NavDirections action;
    MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_game_board, container, false);
        binding = FragmentGameBoardBinding.inflate(inflater, container, false);
        //setContentView(R.layout.activity_main);
        View view = binding.getRoot();
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.spook);
        mediaPlayer.setLooping(true);
        arr[0][0] = new ButtonInfo(0, false, binding.buttonOne);
        arr[0][1] = new ButtonInfo(1, false, binding.buttonTwo);
        arr[0][2] = new ButtonInfo(2, false, binding.buttonThree);
        arr[1][0] = new ButtonInfo(3, false, binding.buttonFour);
        arr[1][1] = new ButtonInfo(4, false, binding.buttonFive);
        arr[1][2] = new ButtonInfo(5, false, binding.buttonSix);
        arr[2][0] = new ButtonInfo(6, false, binding.buttonSeven);
        arr[2][1] = new ButtonInfo(7, false, binding.buttonHeight);
        arr[2][2] = new ButtonInfo(0, false, binding.buttonNine);

        //Added event listener in this button
        binding.gameBoardButton.setOnClickListener(view1 -> {
            binding.tableLayout.setVisibility(View.VISIBLE);
            binding.gameBoardButton.setText(R.string.StartOver);
            binding.textCount.setVisibility(View.VISIBLE);
            binding.textCountLabel.setVisibility(View.VISIBLE);
            binding.welcomeTextview.setVisibility(View.INVISIBLE);
            binding.mario.setVisibility(View.INVISIBLE);
            gamePlay(arr);
            randomised();
            moveCount = 0;
        });
        // Inflate the layout for this fragment
        return view;
    }

    //this method is to the brain of the game..
    @SuppressLint("SetTextI18n")
    public void gamePlay(ButtonInfo[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int finalI = i;
                int finalJ = j;

                arr[i][j].getButton().setOnClickListener(view -> {
                    //Making the logic for the buttons
                    if (!arr[finalI][finalJ].isState()) {
                        adjacent(finalI, finalJ);
                        moveCount++;
                        hasWon(arr);
                    } else if (arr[finalI][finalJ].isState()) {
                        adjacent(finalI, finalJ);
                        moveCount++;
                        hasWon(arr);
                    }
                    if (hasWon(arr)) {
                        action = gameBoardDirections.actionGameBoardToGameOver(moveCount + "");
                        Navigation.findNavController(view).navigate(action);
                    }
                    binding.textCount.setText(moveCount + "");
                });
            }
        }

    }

    //this method toggle the buttons
    @SuppressLint("UseCompatLoadingForDrawables")
    public void toggle(ButtonInfo buttonInfo) {
        if (!buttonInfo.isState()) {
            buttonInfo.setState(true);
            buttonInfo.getButton().setBackground(getResources().getDrawable(R.drawable.custom_button_on_green));
        } else if (buttonInfo.isState()) {
            buttonInfo.setState(false);
            buttonInfo.getButton().setBackground(getResources().getDrawable(R.drawable.custom_button_off));
        }
    }

    //determine wich button will toggle or not.
    //did not have time to make this more efficient.
    public void adjacent(int intOne, int intTwo) {
        if (intOne == 0 && intTwo == 0) {
            toggle(arr[0][0]);
            toggle(arr[0][1]);
            toggle(arr[1][0]);
        } else if (intOne == 0 && intTwo == 1) {
            toggle(arr[0][0]);
            toggle(arr[0][1]);
            toggle(arr[0][2]);
            toggle(arr[1][1]);
        } else if (intOne == 0 && intTwo == 2) {
            toggle(arr[0][1]);
            toggle(arr[0][2]);
            toggle(arr[1][2]);
        } else if (intOne == 1 && intTwo == 0) {
            toggle(arr[0][0]);
            toggle(arr[1][0]);
            toggle(arr[1][1]);
            toggle(arr[2][0]);
        } else if (intOne == 1 && intTwo == 1) {
            toggle(arr[1][1]);
            toggle(arr[1][0]);
            toggle(arr[1][2]);
            toggle(arr[0][1]);
            toggle(arr[2][1]);
        } else if (intOne == 1 && intTwo == 2) {
            toggle(arr[1][2]);
            toggle(arr[1][1]);
            toggle(arr[0][2]);
            toggle(arr[2][2]);
        } else if (intOne == 2 && intTwo == 0) {
            toggle(arr[2][0]);
            toggle(arr[2][1]);
            toggle(arr[1][0]);
        } else if (intOne == 2 && intTwo == 1) {
            toggle(arr[2][1]);
            toggle(arr[2][0]);
            toggle(arr[2][2]);
            toggle(arr[1][1]);
        } else if (intOne == 2 && intTwo == 2) {
            toggle(arr[2][2]);
            toggle(arr[2][1]);
            toggle(arr[1][2]);
        }
    }

    //This method randomise the game from the start
    public void randomised() {
        int randOne = (int) (Math.random() * ((2) + 1));
        int ranTwo = (int) (Math.random() * ((2) + 1));
        adjacent(randOne, ranTwo);
        int randThree = (int) (Math.random() * ((2) + 1));
        int ranFour = (int) (Math.random() * ((2) + 1));
        adjacent(randThree, ranFour);
    }

    //Check if all of the button status are false
    public boolean hasWon(ButtonInfo[][] buttonInfo) {
        int count = 0;
        for (ButtonInfo[] buttonInfos : buttonInfo) {
            for (int j = 0; j < buttonInfo.length; j++) {
                if (!buttonInfos[j].isState()) {
                    count = count + 1;
                    System.out.println(buttonInfos[j].isState());
                }
            }
        }
        if (count == 9) {
            System.out.println(count);
            return true;
        }

        return false;
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

        //mediaPlayer=MediaPlayer.create(getContext(), R.raw.spook);


    }

    @Override
    public void onPause() {
        super.onPause();
        moveCount = 0;
        stopPlaying();
    }


}