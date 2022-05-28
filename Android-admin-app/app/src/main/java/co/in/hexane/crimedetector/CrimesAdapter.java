package co.in.hexane.crimedetector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CrimesAdapter extends RecyclerView.Adapter<CrimesAdapter.CrimeViewHolder> {

    ArrayList<Crime> crimes;

    public CrimesAdapter(ArrayList<Crime> crimes){
        this.crimes = crimes;
    }

    public void updateDataset(ArrayList<Crime> crimes){
        this.crimes = crimes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrimeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alert, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeViewHolder holder, int position) {
        holder.location.setText("Loaction: " + crimes.get(position).getLocation());
        holder.accuracy.setText("Accuracy: " + crimes.get(position).getAccuracy());
        holder.timestamp.setText(crimes.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    class CrimeViewHolder extends RecyclerView.ViewHolder{

        TextView accuracy, location, timestamp;

        public CrimeViewHolder(@NonNull View itemView) {
            super(itemView);

            accuracy = itemView.findViewById(R.id.accuracy);
            location = itemView.findViewById(R.id.location);
            timestamp = itemView.findViewById(R.id.timestamp);
        }
    }
}
