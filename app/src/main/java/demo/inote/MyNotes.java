package demo.inote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyNotes extends AppCompatActivity {

    ListView listViewNotes;
    List<Notes> notesList;
    DatabaseReference databaseNotes;
    TextView category, notes, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        databaseNotes = FirebaseDatabase.getInstance().getReference("notes");

        listViewNotes = (ListView) findViewById(R.id.listViewNotes);
        category = (TextView) findViewById(R.id.textViewCategory);
        notesList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseNotes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notesList.clear();
                for(DataSnapshot noteSnapshot : dataSnapshot.getChildren()){
                //    Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                  //  Log.d("test", "map" + map);



                 //  String category2 = map.get(map);
                //   Log.d("LOOK HERE", "hola" +category2);
                   //category.setText(category2);
                   Notes notes = noteSnapshot.getValue(Notes.class);
                    notesList.add(notes);

                }

                NoteList adapter = new NoteList(MyNotes.this, notesList);
                listViewNotes.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
