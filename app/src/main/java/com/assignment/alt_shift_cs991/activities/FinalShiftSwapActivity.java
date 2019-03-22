package com.assignment.alt_shift_cs991.activities;

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
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.databinding.FinalSwapLayoutBinding;
import com.assignment.alt_shift_cs991.model.Application;
import com.assignment.alt_shift_cs991.model.Shift;
import com.assignment.alt_shift_cs991.model.ShiftSwap;

import androidx.databinding.DataBindingUtil;

public class FinalShiftSwapActivity extends ToolbarActivity {

    private Shift unwantedShift, wantedShift;
    private ObjectAnimator shiftWorkerCardAnimation, userCardAnimation;
    private ImageButton swapButton;
    private ShiftSwap shiftSwap;
    protected Application model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            shiftSwap = getIntent().getExtras().getParcelable("SHIFTSWAP");
        }
        model = (Application) getApplication();
        FinalSwapLayoutBinding shiftSwapLayoutBinding = DataBindingUtil.setContentView(this, R.layout.final_swap_layout);
        shiftSwapLayoutBinding.setShift1(shiftSwap.getWantedShift());
        initToolbar();
        swapButton = findViewById(R.id.shift_button);




        TextView userName = findViewById(R.id.user_name_field);
        TextView surname = findViewById(R.id.user_description_field);

        userName.setText(shiftSwap.getUnwantedShift().getName());
        surname.setText(shiftSwap.getUnwantedShift().getSurname());
    }


    public void switchShifts(final View v) {
        final AnimatorSet animationSet = new AnimatorSet();
        View userCard = findViewById(R.id.user_card);
        View shiftWorkerCard = findViewById(R.id.current_shift_worker_card);

        shiftWorkerCardAnimation = ObjectAnimator.ofFloat(shiftWorkerCard, "y", userCard.getY());
        userCardAnimation = ObjectAnimator.ofFloat(userCard, "y", shiftWorkerCard.getY());

        if (userCard.getY() < shiftWorkerCard.getY()) {
            shiftWorkerCardAnimation = ObjectAnimator.ofFloat(shiftWorkerCard, "y", userCard.getY());
            userCardAnimation = ObjectAnimator.ofFloat(userCard, "y", shiftWorkerCard.getY());
        }
        userCardAnimation.setDuration(500);
        shiftWorkerCardAnimation.setDuration(500);
        animationSet.playTogether(shiftWorkerCardAnimation, userCardAnimation);

        v.animate().rotation(v.getRotation() - 180).setDuration(500).setListener(new Animator.AnimatorListener() {
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
                        model.shiftManager.swapShifts(model.shiftManager.getShiftSwaps().get(0));
                        model.shiftManager.getShiftSwaps().remove(0);

                        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Shift has been swapped!", Toast.LENGTH_SHORT).show();
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
