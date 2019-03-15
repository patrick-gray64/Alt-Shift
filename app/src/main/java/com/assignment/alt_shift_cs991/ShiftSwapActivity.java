package com.assignment.alt_shift_cs991;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.databinding.SwapLayoutBinding;

import androidx.databinding.DataBindingUtil;

public class ShiftSwapActivity extends Toolbar_activity {

    private Shift shift;
    private ObjectAnimator shiftWorkerCardAnimation;
    private ObjectAnimator userCardAnimation;
    private ImageButton swapButton;
    protected AltShift_Application model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getExtras() != null) {
            shift = getIntent().getExtras().getParcelable("SHIFT");

        }
        SwapLayoutBinding shiftSwapLayoutBinding = DataBindingUtil.setContentView(this, R.layout.swap_layout);
        shiftSwapLayoutBinding.setShift(shift);
        initToolbar();
        swapButton =  findViewById(R.id.shift_button);
        model = (AltShift_Application)getApplication();
    }


    public void switchShifts(final View v){
        final AnimatorSet animationSet = new AnimatorSet();
        View userCard = findViewById(R.id.user_card);
        View shiftWorkerCard = findViewById(R.id.current_shift_worker_card);

        shiftWorkerCardAnimation = ObjectAnimator.ofFloat(shiftWorkerCard, "y", userCard.getY());
        userCardAnimation = ObjectAnimator.ofFloat(userCard, "y", shiftWorkerCard.getY());

        if(userCard.getY() < shiftWorkerCard.getY()){
            shiftWorkerCardAnimation = ObjectAnimator.ofFloat(shiftWorkerCard, "y", userCard.getY() );
            userCardAnimation = ObjectAnimator.ofFloat(userCard, "y",  shiftWorkerCard.getY());
        }
        userCardAnimation.setDuration(500);
        shiftWorkerCardAnimation.setDuration(500);
        animationSet.playTogether(shiftWorkerCardAnimation, userCardAnimation);

        v.animate().rotation(v.getRotation()-180).setDuration(500).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                v.setEnabled(false);
                swapButton.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setEnabled(true);
                Button confirmButton = new Button(v.getContext());
                confirmButton.setBackgroundResource(R.drawable.button_layout);
                confirmButton.setText("Confirm Swap");
                confirmButton.setTextColor(Color.parseColor("#ffffff"));
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Shift swap request sent!", Toast.LENGTH_SHORT).show();
                    }
                });
                RelativeLayout layout = findViewById(R.id.rlayout);
                RelativeLayout.LayoutParams laypram = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                laypram.addRule(RelativeLayout.BELOW, R.id.cardSwapHolder);
                laypram.addRule(RelativeLayout.CENTER_IN_PARENT, R.id.cardSwapHolder);
                laypram.setMargins(10, 10, 30, 10);
                layout.addView(confirmButton, laypram);

                //(new Handler()).postDelayed(this::returnToHome, 500);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).withStartAction(new Runnable() {
            @Override
            public void run() {
                animationSet.start();
            }
        }).start();
    }



}

