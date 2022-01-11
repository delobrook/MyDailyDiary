package Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import DiaryDatabase.DiaryDatabase;
import DiaryDatabase.Entry;
import DiaryDatabase.EntryDao;

public class DiaryRepository {
    private EntryDao entryDao;
    private LiveData<List<Entry>> allDiaryEntries;
    private DiaryDatabase diaryDatabase;

    public DiaryRepository(Context c) {
        diaryDatabase=DiaryDatabase.getInstance(c);
        entryDao=diaryDatabase.getEntryDao();
        allDiaryEntries=entryDao.getEntries();
    }
    public void insert(Entry entry){
        new InsertEntryAsyncTask(entryDao).execute(entry);
    }
    public void update(Entry entry){
        new UpdateEntryAsyncTask(entryDao).execute(entry);
    }

    public LiveData<List<Entry>> getAllDiaryEntries() {
        return allDiaryEntries;
    }


    private static class InsertEntryAsyncTask extends AsyncTask<Entry,Void,Void>{
        private EntryDao entryDao;

        public InsertEntryAsyncTask(EntryDao entryDao) {
            this.entryDao = entryDao;
        }

        @Override
        protected Void doInBackground(Entry... entries) {
            entryDao.insertEntry(entries[0]);
            return null;
        }
    }
    private static class UpdateEntryAsyncTask extends AsyncTask<Entry,Void,Void>{

        private EntryDao entryDao;

        public UpdateEntryAsyncTask(EntryDao entryDao) {
            this.entryDao = entryDao;
        }

        @Override
        protected Void doInBackground(Entry... entries) {
            entryDao.updateEntry(entries[0]);
            return null;
        }
    }
}
