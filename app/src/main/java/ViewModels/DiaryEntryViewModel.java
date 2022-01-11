package ViewModels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import DiaryDatabase.Entry;
import Repository.DiaryRepository;
import Views.MainActivity;

public class DiaryEntryViewModel extends AndroidViewModel {
    private LiveData<List<Entry>> diaryEntries;
    private DiaryRepository diaryRepository;
    private Entry sEntry;

    public DiaryEntryViewModel(@NonNull Application application) {
        super(application);
        diaryRepository=new DiaryRepository(application);
        diaryEntries=diaryRepository.getAllDiaryEntries();
    }

    public LiveData<List<Entry>> getAllEntries(){
        return diaryEntries;
    }

    public void insertEntry(String date,String content){

        diaryRepository.insert(new Entry(date,content));

    }

    public void updateEntry(String content){
        sEntry.setContent(content);
        diaryRepository.update(sEntry);

    }
    public Entry getEntryByDate(String sDate){
        if ( diaryEntries.getValue()!=null) {
            for (Entry e : diaryEntries.getValue()) {
                if (e.getDate().equals(sDate)) {
                    sEntry=e;
                    return sEntry;
                }
            }
        }
        return null;
    }
    public Entry getsEntry(){
        return sEntry;
    }

}
