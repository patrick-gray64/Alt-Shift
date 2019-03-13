package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Intent.EXTRA_TEXT;

public class SwapActivity extends Toolbar_activity  {

    public RecyclerView recyclerView;
    protected AltShift_Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        initToolbar();
        model = (AltShift_Application) getApplication();

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString(EXTRA_TEXT);

        recyclerView = findViewById(R.id.recycler_view);
        Adapter adapter = new Adapter();

        List<Shifter> filteredList = new ArrayList<>();

        for (int i = 0; i < model.getShiftList().size(); i++) {
          if (!model.getShiftList().get(i).getDate().equals(message)) {
             filteredList.add(model.getShiftList().get(i).getShifter());
            }
         }
        adapter.setShifterList(filteredList);



        adapter.setStartActivityCallback(new Adapter.StartActivityCallback() {
            @Override
            public void startActivityIntent(View v, Shifter shifter) {
                animateIntent(v, shifter);
            }
        });
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }


    public void animateIntent(View view, Shifter shifter) {

        // Ordinary Intent for launching a new activity
        Intent intent = new Intent(this, ShiftSwapActivity.class);
        //Bundle bundle = new Bundle();


        intent.putExtra("SHIFTER", shifter);
        // Get the transition name from the string
        String transitionName = "usercard";

        // Define the view that the animation will start from

        ActivityOptionsCompat options =

                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        view,   // Starting view
                        transitionName    // The String
                );
        //Start the Intent
        ActivityCompat.startActivity(this, intent, options.toBundle());

    }
}

