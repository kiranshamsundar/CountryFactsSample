package com.facts.country.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facts.country.R;
import com.facts.country.base.BaseFragment;
import com.facts.country.util.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;


public class CountryFactsListFragment extends BaseFragment{

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;
    private CountryFactsViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.country_facts_list_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CountryFactsViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new CountryFactsAdapter(viewModel, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        observableViewModel();

        setSwipeToRefreshListener();
    }


    private void observableViewModel() {
        viewModel.getCountryFactsList().observe(this, facts -> {
            if(facts != null) {
                listView.setVisibility(View.VISIBLE);
            if(swipeContainer.isRefreshing() ){
                swipeContainer.setRefreshing(false);
            }
            }
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText(getString(R.string.msg_error_while_loading));
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });

        viewModel.getCountryName().observe(this, countryName -> {
            if (countryName != null) {
                    getActivity().setTitle(countryName);
                }

        });
    }

    /**
     * Initialize swipe to refresh listener
     */
    private void setSwipeToRefreshListener() {

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.fetchCountryFacts();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }
}
