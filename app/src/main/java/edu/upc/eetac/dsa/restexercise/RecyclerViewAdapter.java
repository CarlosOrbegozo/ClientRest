package edu.upc.eetac.dsa.restexercise;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Track> data;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
       // public Button btn;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(android.R.id.text1);
           // btn = v.findViewById(android.R.id.button1);
        }
    }

    public RecyclerViewAdapter(List<Track> data) {
        this.data = data;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Track answer = ((Track) data.get(position));
        holder.text.setText(answer.getSinger());
        holder.itemView.setTag(answer.getTitle());
       // holder.btn.setText("edit");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}