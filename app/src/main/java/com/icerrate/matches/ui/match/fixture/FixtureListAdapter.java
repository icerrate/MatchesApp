package com.icerrate.matches.ui.match.fixture;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.icerrate.matches.R;
import com.icerrate.matches.ui.match.MatchListAdapter;
import com.icerrate.matches.ui.match.MatchHolder;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.utils.DateUtils;

/**
 * @author icerrate
 */
public class FixtureListAdapter extends MatchListAdapter<FixtureListAdapter.FixtureHolder> {

    @NonNull
    @Override
    public FixtureListAdapter.FixtureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fixture, parent, false);
        return new FixtureHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FixtureListAdapter.FixtureHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final Match match = mFilteredItems.get(position);
        Context context = holder.competitionTextView.getContext();

        String dayNumber = DateUtils.formatStringDate(match.getDate(), DateUtils.FORMAT_ORIGINAL, DateUtils.FORMAT_DAY_NUMBER);
        String dayName = DateUtils.formatStringDate(match.getDate(), DateUtils.FORMAT_ORIGINAL, DateUtils.FORMAT_DAY_NAME);
        holder.dayNumberTextView.setText(dayNumber);
        holder.dayNameTextView.setText(dayName);

        String place = match.getVenue().getName();
        String date = DateUtils.formatStringDate(match.getDate(), DateUtils.FORMAT_ORIGINAL, DateUtils.FORMAT_DATE_TIME);
        String finalPlaceDate = context.getString(R.string.place_date, place, date);
        if(Match.POSTPONED_STATE.equals(match.getState())) {
            holder.stateTextView.setVisibility(View.VISIBLE);
            holder.stateTextView.setText(match.getState());

            Spannable sb = new SpannableString(finalPlaceDate);
            sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.red)), finalPlaceDate.indexOf(date), finalPlaceDate.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.placeDateTextView.setText(sb);
        } else {
            holder.stateTextView.setVisibility(View.GONE);
            holder.placeDateTextView.setText(finalPlaceDate);
        }
    }

    public class FixtureHolder extends MatchHolder {

        public TextView stateTextView;

        public TextView dayNumberTextView;

        public TextView dayNameTextView;

        public FixtureHolder(View v) {
            super(v);
            stateTextView = v.findViewById(R.id.state);
            dayNumberTextView = v.findViewById(R.id.day_number);
            dayNameTextView = v.findViewById(R.id.day_name);
        }
    }
}
