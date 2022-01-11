package DiaryDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDao {
    @Query("SELECT * FROM " + Constants.TABLE_NAME_ENTRY)
    LiveData<List<Entry>> getEntries();

    @Query("SELECT * FROM " + Constants.TABLE_NAME_ENTRY +" WHERE date LIKE :date LIMIT 1 ")
    Entry getByDate(String date);

    //insert data
    @Insert
    void insertEntry(Entry entry);

    @Update
    void updateEntry(Entry repos);

    @Delete
    void deleteEntry(Entry entry);

    @Delete
    void deleteEntries(Entry...entries);


}
