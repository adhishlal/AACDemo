package lal.adhish.aacdemo.repository.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM word_table order by word asc")
    LiveData<List<Word>> getWords();

    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("Delete from word_table")
    void deleteAll();
}
