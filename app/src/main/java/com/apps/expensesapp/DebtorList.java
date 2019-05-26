package com.apps.expensesapp;

import android.content.Context;
import android.graphics.Color;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DebtorList {

    private final DebtorAdapter mAdapter;

    public DebtorList(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        mAdapter = new DebtorAdapter();
        rv.setAdapter(mAdapter);
    }

    public void setDebtors (List<Debtor> data) {
        mAdapter.setData(data);
    }

    class DebtorAdapter extends RecyclerView.Adapter<DebtorAdapter.Vh> {

        private Context context;
        private List<Debtor> mData;

        DebtorAdapter() {
            setHasStableIds(true);
        }

        void setData(List<Debtor> data) {
            mData = data;
            notifyDataSetChanged();
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            context = recyclerView.getContext();
        }

        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup parent, int type) {
            return new Vh(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull Vh vh, int position) {
            vh.bind(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public long getItemId(int position) {
            return mData.get(position).id;
        }

        class Vh extends RecyclerView.ViewHolder {

            private final TextView mName;
            private final TextView mUserDebt;
            private final TextView mDebt;
            private final TextView mGap;

            public Vh(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.i_debtor, parent, false));
                mName = itemView.findViewById(R.id.i_debtor_name);
                mUserDebt = itemView.findViewById(R.id.i_debtor_userDebt);
                mDebt = itemView.findViewById(R.id.i_debtor_debt);
                mGap = itemView.findViewById(R.id.i_debtor_gap);
            }

            void bind(Debtor debtor) {

                double dGap = debtor.gap;
                double dGapNegative = dGap * (-1);
                String gapValuePositive = "+" + dGap;
                String gapValueNegative = "−" + dGapNegative;
                int red = ContextCompat.getColor(context , R.color.colorRed);
                int green = ContextCompat.getColor(context, R.color.colorGreen);
                int defaultTextColor = ContextCompat.getColor(context, R.color.colorPrimaryText);


                mName.setText(debtor.name);
                mUserDebt.setText(String.valueOf(debtor.userDebt));
                mDebt.setText(String.valueOf(debtor.debt));


                if (debtor.userDebt > 0) {
                    mUserDebt.setTextColor(red);
                } else mUserDebt.setTextColor(defaultTextColor);

                if (debtor.debt > 0) {
                    mDebt.setTextColor(green);
                } else mDebt.setTextColor(defaultTextColor);

                if (debtor.gap> 0) {
                    mGap.setText(gapValuePositive);
                    mGap.setBackgroundResource(R.drawable.gap_background_positive);
                } else if (debtor.gap == 0) {
                    mGap.setText(String.valueOf(dGap));
                    mGap.setBackgroundResource(R.drawable.gap_background_default);
                } else {
                    mGap.setText(gapValueNegative);
                    mGap.setBackgroundResource(R.drawable.gap_background_negative);
                }

            }
        }

    }


}
