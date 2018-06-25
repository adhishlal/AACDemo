package lal.adhish.aacdemo.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import lal.adhish.aacdemo.repository.room.Word;
import lal.adhish.aacdemo.repository.room.WordDao;
import lal.adhish.aacdemo.repository.room.WordRoomDatabase;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {


        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getWords();


    }


    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }


    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }




    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }




}
