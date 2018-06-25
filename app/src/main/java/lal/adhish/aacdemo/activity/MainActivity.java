package lal.adhish.aacdemo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import lal.adhish.aacdemo.R;
import lal.adhish.aacdemo.adapter.WordListAdapter;
import lal.adhish.aacdemo.repository.room.Word;
import lal.adhish.aacdemo.viewmodel.WordViewModel;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        adapter.setWords(mWordViewModel.getAllWords().getValue());

        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });


        Button btnInsert = findViewById(R.id.btnInsert);
        final EditText etWord = findViewById(R.id.etWord);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(etWord.getText().toString().trim().length() > 0) {
                    Word word = new Word();
                    word.setWord(etWord.getText().toString().trim());
                    mWordViewModel.insert(word);

                    Toast.makeText(MainActivity.this, "Inserted!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    etWord.setError("Write a word to insert!");
                }
            }
        });
    }


}
