package co.in.hexane.crimedetector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<Crime> crimes = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.alerts_container);
        CrimesAdapter crimesAdapter = new CrimesAdapter(crimes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(crimesAdapter);

        db = FirebaseFirestore.getInstance();

        db.collection("crimes")
                .orderBy("date", Query.Direction.ASCENDING)
                .addSnapshotListener((value, e) -> {
                    if (e != null) {
                        Log.d("HomeActivityError", e.getMessage());
                        return;
                    }
                    crimes.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        if (doc.get("Location") != null) {
                            Crime crime = new Crime();
                            crime.setLocation(doc.getString("Location"));
                            crime.setAccuracy(doc.getString("Accuracy"));

                            crime.setTimestamp(doc.getTimestamp("date").toDate().toString());
                            if(!crimes.contains(crime)){
                                crimes.add(0, crime);
                            }
                        }
                    }
                    crimesAdapter.updateDataset(crimes);
                    if(crimes.size() > 0){
                        findViewById(R.id.nothing_detected).setVisibility(View.GONE);
                    }
                });
    }
}