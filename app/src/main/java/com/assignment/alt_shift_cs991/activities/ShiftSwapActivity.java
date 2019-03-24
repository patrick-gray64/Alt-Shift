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
import com.assignment.alt_shift_cs991.databinding.SwapLayoutBinding;
import com.assignment.alt_shift_cs991.model.Shift;
import com.assignment.alt_shift_cs991.model.ShiftSwap;

import androidx.databinding.DataBindingUtil;

/**
 * Activity for requesting a shiftSwap.
 */
public class ShiftSwapActivity extends ToolbarActivity {

    private Shift shift, userSwapShift, nonUserSwapShift;
    private ImageButton swapButton;
    protected Application model;

    /**
     * Initialises activity with two shifts and shifters to be swapped.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            shift = getIntent().getExtras().getParcelable("SHIFT");
        }
        SwapLayoutBinding shiftSwapLayoutBinding = DataBindingUtil.setContentView(this, R.layout.swap_layout);
        shiftSwapLayoutBinding.setShift(shift);
        initToolbar();
        swapButton = findViewById(R.id.shift_button);
        model = (Application) getApplication();
        nonUserSwapShift = model.shiftManager.getShift(model.shiftManager.getShifter(shift.getUserName(), shift.getPassword()), shift.getDate());
        userSwapShift = model.shiftManager.getShift(model.shiftManager.getShifter(shift.getSwapUserName(), shift.getSwapPassword()), shift.getSwapDate());

        TextView userName = findViewById(R.id.user_name_field);
        TextView surname = findViewById(R.id.user_description_field);

        userName.setText(model.getLoggedInShifter().getFirstName());
        surname.setText(model.getLoggedInShifter().getSurname());
    }

    /**
     * Animates the swapping of shifts and adds the shift swap to the pending shiftswaps.
     *
     * @param v
     */
    public void switchShifts(final View v) {
        final AnimatorSet animationSet = new AnimatorSet();
        View userCard = findViewById(R.id.user_card);
        View shiftWorkerCard = findViewById(R.id.current_shift_worker_card);

        ObjectAnimator shiftWorkerCardAnimation = ObjectAnimator.ofFloat(shiftWorkerCard, "y", userCard.getY());
        ObjectAnimator userCardAnimation = ObjectAnimator.ofFloat(userCard, "y", shiftWorkerCard.getY());

        if (userCard.getY() < shiftWorkerCard.getY()) {
            shiftWorkerCardAnimation = ObjectAnimator.ofFloat(shiftWorkerCard, "y", userCard.getY());
            userCardAnimation = ObjectAnimator.ofFloat(userCard, "y", shiftWorkerCard.getY());
        }
        userCardAnimation.setDuration(500);
        shiftWorkerCardAnimation.setDuration(500);
        animationSet.playTogether(shiftWorkerCardAnimation, userCardAnimation);

        v.animate().rotation(v.getRotation() - 180).setDuration(500).setListener(new Animator.AnimatorListener() {

            /**
             * Deactivates the swap button.
             * @param animation
             */
            @Override
            public void onAnimationStart(Animator animation) {
                v.setEnabled(false);
                swapButton.setClickable(false);
            }

            /**
             * Makes confirm button visible.
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
                v.setEnabled(true);
                Button confirmButton = new Button(v.getContext());
                confirmButton.setBackgroundResource(R.drawable.button_layout);
                confirmButton.setText(R.string.confirm_swap_request);
                confirmButton.setTextColor(Color.parseColor("#ffffff"));
                confirmButton.setOnClickListener(v -> {
                    ShiftSwap shiftSwap = new ShiftSwap(userSwapShift, nonUserSwapShift);
                    model.shiftManager.addShiftSwap(shiftSwap);
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Shift swap request sent!", Toast.LENGTH_SHORT).show();
                });

                RelativeLayout layout = findViewById(R.id.rlayout);
                RelativeLayout.LayoutParams layPram = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layPram.addRule(RelativeLayout.BELOW, R.id.cardSwapHolder);
                layPram.addRule(RelativeLayout.CENTER_IN_PARENT, R.id.cardSwapHolder);
                layPram.setMargins(10, 10, 30, 10);
                layout.addView(confirmButton, layPram);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }


            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).withStartAction(animationSet::start).start();
    }


}

