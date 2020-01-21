package com.example.a9assigment.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a9assigment.R;

import java.util.List;

public class RwAdapterLoginLog extends RecyclerView.Adapter<RwAdapterLoginLog.HolderLoginLog> {

    public RwAdapterLoginLog(Context context) {
        inflater = LayoutInflater.from(context);
    }

    private final LayoutInflater inflater;
    private List<EntityLoginLog> nameDate;

    @Override
    public void onBindViewHolder(@NonNull HolderLoginLog holder, int position) {
        if (nameDate != null) {
            EntityLoginLog current = nameDate.get(position);
            holder.nameItemView.setText(current.username);
            holder.dateItemView.setText(current.timestamp.toString());
            holder.successItemView.setText(current.success.toString());

        } else {
            // In case data is not ready yet.
            holder.nameItemView.setText(R.string.nodata);
            holder.dateItemView.setText(R.string.nodata);
            holder.successItemView.setText(R.string.nodata);
        }
    }

    @NonNull
    @Override
    public HolderLoginLog onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.login_list_item, parent, false);
        return new HolderLoginLog(itemView);
    }

    class HolderLoginLog extends RecyclerView.ViewHolder {
        private final TextView nameItemView;
        private final TextView dateItemView;
        private final TextView successItemView;


        private HolderLoginLog(View itemView) {
            super(itemView);
            nameItemView = itemView.findViewById(R.id.username);
            dateItemView = itemView.findViewById(R.id.timestamp);
            successItemView = itemView.findViewById(R.id.success);
        }
    }

    void setNameDates(List<EntityLoginLog> nameDates) {
        nameDate = nameDates;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (nameDate != null)
            return nameDate.size();
        else return 0;
    }
}

