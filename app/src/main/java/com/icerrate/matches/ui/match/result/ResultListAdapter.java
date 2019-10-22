package com.icerrate.matches.ui.match.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.icerrate.matches.R;
import com.icerrate.matches.ui.match.MatchListAdapter;
import com.icerrate.matches.ui.match.MatchHolder;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.data.model.Score;
import com.icerrate.matches.utils.DateUtils;

/**
 * @author icerrate
 */
public class ResultListAdapter extends MatchListAdapter<ResultListAdapter.ResultHolder> {

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ResultHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final Match match = mFilteredItems.get(position);
        Context context = holder.competitionTextView.getContext();

        String place = match.getVenue().getName();
        String date = DateUtils.formatStringDate(match.getDate(), DateUtils.FORMAT_ORIGINAL, DateUtils.FORMAT_DATE_TIME);
        String finalPlaceDate = context.getString(R.string.place_date, place, date);
        holder.placeDateTextView.setText(finalPlaceDate);

        holder.homeTeamScoreTextView.setText(String.valueOf(match.getScore().getHome()));
        holder.awayTeamScoreTextView.setText(String.valueOf(match.getScore().getAway()));
        if(Score.HOME.equals(match.getScore().getWinner())) {
            holder.homeTeamScoreTextView.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.awayTeamScoreTextView.setTextColor(context.getResources().getColor(R.color.gray));
        } else if(Score.AWAYS.equals(match.getScore().getWinner())) {
            holder.homeTeamScoreTextView.setTextColor(context.getResources().getColor(R.color.gray));
            holder.awayTeamScoreTextView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.homeTeamScoreTextView.setTextColor(context.getResources().getColor(R.color.gray));
            holder.awayTeamScoreTextView.setTextColor(context.getResources().getColor(R.color.gray));
        }
    }

    public static class ResultHolder extends MatchHolder {

        public TextView homeTeamScoreTextView;

        public TextView awayTeamScoreTextView;

        public ResultHolder(View v) {
            super(v);
            homeTeamScoreTextView = v.findViewById(R.id.home_team_score);
            awayTeamScoreTextView = v.findViewById(R.id.away_team_score);
        }
    }
}
