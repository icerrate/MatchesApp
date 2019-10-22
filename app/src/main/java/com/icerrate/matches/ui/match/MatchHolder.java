package com.icerrate.matches.ui.match;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.icerrate.matches.R;

/**
 * @author icerrate
 */
public class MatchHolder extends RecyclerView.ViewHolder {

    public TextView monthYearTextView;

    public TextView competitionTextView;

    public TextView placeDateTextView;

    public TextView homeTeamTextView;

    public TextView awayTeamTextView;

    public MatchHolder(View v) {
        super(v);
        monthYearTextView = v.findViewById(R.id.month_year);
        competitionTextView = v.findViewById(R.id.competition);
        placeDateTextView = v.findViewById(R.id.place_date);
        homeTeamTextView = v.findViewById(R.id.home_team);
        awayTeamTextView = v.findViewById(R.id.away_team);
    }
}
