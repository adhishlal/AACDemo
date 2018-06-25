package lal.adhish.aacdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import lal.adhish.aacdemo.repository.WordRepository;
import lal.adhish.aacdemo.repository.room.Word;

public class WordViewModel extends AndroidViewModel {

    WordRepository mWordRepository;
    LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);


        mWordRepository = new WordRepository(application);
        mAllWords = mWordRepository.getmAllWords();
    }


    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }


    public void insert(Word word) { mWordRepository.insert(word); }

}
