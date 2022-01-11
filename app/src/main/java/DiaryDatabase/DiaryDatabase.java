package DiaryDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Entry.class},version = 1,exportSchema = false)
public abstract class DiaryDatabase extends RoomDatabase {
    public abstract EntryDao getEntryDao();
    private static DiaryDatabase diaryDB;

    public static /*synchronied*/ DiaryDatabase getInstance(Context context){
        if(diaryDB==null){
            diaryDB=buildDatabaseInstance(context);
        }
        return diaryDB;
    }

    private static DiaryDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                DiaryDatabase.class,
                Constants.DB_NAME).allowMainThreadQueries().build();
    }
    public void cleanUp() {
        diaryDB=null;
    }
}
