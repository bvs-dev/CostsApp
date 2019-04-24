package com.apps.costsapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class DebitorList {

    private final DebitorAdapter mAdapter;

    public DebitorList (RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        mAdapter = new DebitorAdapter();
        rv.setAdapter(mAdapter);
    }

    public void setDebitors (List<Debitor> data) {
        mAdapter.setData(data);
    }

    class DebitorAdapter extends RecyclerView.Adapter<DebitorAdapter.Vh> {

        private List<Debitor> mData;

        DebitorAdapter() {
            setHasStableIds(true);
        }

        void setData(List<Debitor> data) {
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
                        .inflate(R.layout.i_debitor, parent, false));
                mName = itemView.findViewById(R.id.i_debitor_name);
                mUserDebt = itemView.findViewById(R.id.i_debitor_userDebt);
                mDebt = itemView.findViewById(R.id.i_debitor_debt);
                mGap = itemView.findViewById(R.id.i_debitor_gap);
            }

            void bind(Debitor debitor) {
                mName.setText(debitor.name);
                mUserDebt.setText(String.valueOf(debitor.userDebt));
                mDebt.setText(String.valueOf(debitor.debt));
                mGap.setText(String.valueOf(debitor.gap));

            }
        }

    }


}
