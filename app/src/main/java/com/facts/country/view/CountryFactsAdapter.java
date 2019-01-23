package com.facts.country.view;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facts.country.R;
import com.facts.country.data.model.CountryFact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CountryFactsAdapter extends RecyclerView.Adapter<CountryFactsAdapter.CountryFactsViewHolder> {

    private List<CountryFact> countryFacts = new ArrayList<>();

    CountryFactsAdapter(CountryFactsViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getCountryFactsList().observe(lifecycleOwner, countryFactList -> {
            countryFacts.clear();
            if (countryFactList != null ) {
                countryFacts.addAll(countryFactList);
                notifyDataSetChanged();
            }
        });

    }

    @NonNull
    @Override
    public CountryFactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_fact_list_item, parent, false);
        return new CountryFactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryFactsViewHolder holder, int position) {
        holder.bind(countryFacts.get(position));
    }

    @Override
    public int getItemCount() {
        return countryFacts.size();
    }

    static final class CountryFactsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView factTitle;
        @BindView(R.id.description)
        public TextView factDescription;
        @BindView(R.id.item_img)
        public ImageView factPicture;

        private CountryFact countryFact;

        CountryFactsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void bind(CountryFact countryFact) {
            this.countryFact = countryFact;

            if (this.countryFact.getTitle() != null) {
                factTitle.setText(this.countryFact.getTitle());
            } else {
                /**
                 * Handle if fact's title is unavailable
                 */
                factTitle.setText(R.string.msg_no_title);
            }
            if (this.countryFact.getDescription() != null) {
                factDescription.setText(this.countryFact.getDescription());
            } else {
                /**
                 * Handle if fact description is unavailable
                 */
                factDescription.setText(R.string.msg_no_description);
            }
            if (this.countryFact.getImageHref() != null && !this.countryFact.getImageHref().isEmpty()) {
                Picasso.with(factPicture.getContext()).load(this.countryFact.getImageHref())
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image)
                        .into(factPicture);
            } else {
                /**
                 * Handle if fact's image url is unavailable
                 */
                Picasso.with(factPicture.getContext()).load(R.drawable.no_image)
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image)
                        .into(factPicture);

            }


        }
    }
}
