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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class DebtorList {

    public interface Listener {

        void onOpen(Debtor debtor);

        void onDebt(Debtor debtor);

        void onUserDebt(Debtor debtor);

    }

    private final DebtorAdapter mAdapter;
    private final Listener mListener;

    public DebtorList(RecyclerView rv, Listener listener) {
        mListener = listener;
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
                mUserDebt.setOnClickListener(v -> mListener.onUserDebt(mData.get(getAdapterPosition())));
                mDebt.setOnClickListener(v -> mListener.onDebt(mData.get(getAdapterPosition())));
                mName.setOnClickListener(v -> mListener.onOpen(mData.get(getAdapterPosition())));
                mGap.setOnClickListener(v -> mListener.onOpen(mData.get(getAdapterPosition())));
            }

            void bind(Debtor debtor) {

                int dGap = debtor.gap;
                int dGapNegative = dGap * (-1);
                String gapValuePositive = "+" + numberReduction(dGap);
                String gapValueNegative = "−" + numberReduction(dGapNegative);
                int red = ContextCompat.getColor(context , R.color.colorRed);
                int green = ContextCompat.getColor(context, R.color.colorGreen);
                int defaultTextColor = ContextCompat.getColor(context, R.color.colorPrimaryText);


                mName.setText(debtor.name);
                mUserDebt.setText(numberReduction(debtor.userDebt));
                mDebt.setText(numberReduction(debtor.debt));

                // choosing text color depending on number
                if (debtor.userDebt > 0) {
                    mUserDebt.setTextColor(red);
                } else mUserDebt.setTextColor(defaultTextColor);

                if (debtor.debt > 0) {
                    mDebt.setTextColor(green);
                } else mDebt.setTextColor(defaultTextColor);

                // choosing background depending on number
                if (debtor.gap> 0) {
                    mGap.setText(gapValuePositive);
                    mGap.setBackgroundResource(R.drawable.gap_background_positive);
                } else if (debtor.gap == 0) {
                    mGap.setText(numberReduction(dGap));
                    mGap.setBackgroundResource(R.drawable.gap_background_default);
                } else {
                    mGap.setText(gapValueNegative);
                    mGap.setBackgroundResource(R.drawable.gap_background_negative);
                }

            }

            public String numberReduction(int number) {

                if (number < 0) number = number * (-1);

                double value = number/100d;
                String result = String.valueOf((int)value);
                int numberLength = integerLength(value);

                if (numberLength >= 10) {
                    result = round(value/1000000000d) + "kkk";
                } else if (numberLength >= 7) {
                    result = round(value/1000000d) + "kk";
                } else if (numberLength == 6) result = ((int)Math.round(value/1000)) + "k";


                return result;
            }

            public int integerLength(double decimal) {

                double number = (double) (int) decimal;
                int length = 1;

                while ((int) number > 9) {

                    number = number / 10;
                    length++;

                }
                return length;
            }

            public double round(double decimal) {

                return Math.round(decimal*100)/100d;

            }
        }

    }


}
