package com.assignment.alt_shift_cs991;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.databinding.SwapLayoutBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ShiftSwapActivity extends AppCompatActivity {

    ShiftModel shiftModel;
    private ObjectAnimator shiftWorkerCardAnimation;
    private ObjectAnimator userCardAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getExtras() != null) {
            shiftModel = getIntent().getExtras().getParcelable("SHIFT_MODEL");
        }
        SwapLayoutBinding shiftSwapLayoutBinding = DataBindingUtil.setContentView(this, R.layout.swap_layout);
        shiftSwapLayoutBinding.setShiftModel(shiftModel);
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
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setEnabled(true);
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
        Toast.makeText(getApplicationContext(),"Shift swap request sent!", Toast.LENGTH_SHORT).show();
    }



}

