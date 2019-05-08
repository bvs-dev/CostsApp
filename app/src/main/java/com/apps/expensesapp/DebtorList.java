package com.apps.expensesapp;

import android.support.annotation.NonNull;
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

        private List<Debtor> mData;

        DebtorAdapter() {
            setHasStableIds(true);
        }

        void setData(List<Debtor> data) {
            mData = data;
            notifyDataSetChanged();
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
                mName.setText(debtor.name);
                mUserDebt.setText(String.valueOf(debtor.userDebt));
                mDebt.setText(String.valueOf(debtor.debt));
                mGap.setText(String.valueOf(debtor.gap));

            }
        }

    }


}
