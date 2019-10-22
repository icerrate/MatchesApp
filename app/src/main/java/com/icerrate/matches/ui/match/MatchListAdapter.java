package com.icerrate.matches.ui.match;

import android.content.Context;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icerrate.matches.R;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icerrate
 */
public abstract class MatchListAdapter<BH extends MatchHolder> extends RecyclerView.Adapter<BH> implements Filterable {

    private final static int FIRST_ITEM = 0;

    private List<Match> mItems;

    protected List<Match> mFilteredItems;

    private FilterListener mFilterListener;

    public MatchListAdapter() {
        mItems = new ArrayList<>();
        mFilteredItems = mItems;
    }

    public void setFilterListener(FilterListener filterListener) {
        mFilterListener = filterListener;
    }

    @Override
    public void onBindViewHolder(@NonNull BH holder, int position) {
        final Match match = mFilteredItems.get(position);
        Context context = holder.competitionTextView.getContext();
        String competitionStage = match.getCompetitionStage().getCompetition().getName();

        holder.competitionTextView.setText(competitionStage);
        holder.homeTeamTextView.setText(match.getHomeTeam().getName());
        holder.awayTeamTextView.setText(match.getAwayTeam().getName());

        String place = match.getVenue().getName();
        String date = DateUtils.formatStringDate(match.getDate(), DateUtils.FORMAT_ORIGINAL, DateUtils.FORMAT_DATE_TIME);
        holder.placeDateTextView.setText(context.getString(R.string.place_date, place, date));

        String currentMonthYear = DateUtils.formatStringDate(match.getDate(), DateUtils.FORMAT_ORIGINAL, DateUtils.FORMAT_MONTH_YEAR);
        if (position == FIRST_ITEM) {
            holder.monthYearTextView.setVisibility(View.VISIBLE);
        } else {
            String lastMonthYear = DateUtils.formatStringDate(mFilteredItems.get(position-1).getDate(), DateUtils.FORMAT_ORIGINAL, DateUtils.FORMAT_MONTH_YEAR);
            boolean sameMonthYear = lastMonthYear.equals(currentMonthYear);
            holder.monthYearTextView.setVisibility(sameMonthYear ? View.GONE: View.VISIBLE);
        }
        holder.monthYearTextView.setText(currentMonthYear);
    }

    @Override
    public int getItemCount() {
        return mFilteredItems.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredItems = mItems;
                } else {
                    List<Match> filteredList = new ArrayList<>();
                    for (Match match : mItems) {
                        String competitionName = match.getCompetitionStage().getCompetition().getName().toLowerCase();
                        if (competitionName.contains(charString.toLowerCase())) {
                            filteredList.add(match);
                        }
                    }
                    mFilteredItems = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredItems;
                filterResults.count = mFilteredItems.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredItems = (List<Match>) results.values;
                notifyDataSetChanged();
                if (mFilterListener != null) {
                    boolean isEmpty = results.count == 0;
                    mFilterListener.onResults(isEmpty);
                }
            }
        };
    }

    public void setItems(List<Match> matches) {
        mItems = matches;
        mFilteredItems = mItems;
        notifyDataSetChanged();
    }

    public interface FilterListener {

        void onResults(boolean isEmpty);
    }
}
