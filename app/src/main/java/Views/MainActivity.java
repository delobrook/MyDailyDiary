package Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.mydailydiary.R;
import com.example.mydailydiary.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Observer;

import DiaryDatabase.Entry;
import ViewModels.DiaryEntryViewModel;

//https://medium.com/swlh/storing-data-locally-with-room-database-and-mvvm-architecture-9a707fd16aee
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    List<Entry> allEntries;
    DiaryEntryViewModel diaryEntryViewModel;
    String sDate;
    boolean update;
    Entry sEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);

        diaryEntryViewModel=new ViewModelProvider(this).get(DiaryEntryViewModel.class);
        allEntries=diaryEntryViewModel.getAllEntries().getValue();

        diaryEntryViewModel.getAllEntries().observe(this, new androidx.lifecycle.Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                allEntries=entries;
            }
        });

        activityMainBinding.calendarView.setDate(System.currentTimeMillis());
        sDate=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        activityMainBinding.setTheDate(sDate);

        update=false;
        sEntry=diaryEntryViewModel.getEntryByDate(sDate);
        if ( sEntry==null) {

        }else{
            update=true;
            activityMainBinding.entryContent.setText(sEntry.getContent());
        }
        changeButtonText();



        activityMainBinding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                sDate=i+"/"+i1+"/"+i2;
                activityMainBinding.setTheDate(sDate);
                update=false;
                activityMainBinding.entryContent.setText("");
                sEntry=diaryEntryViewModel.getEntryByDate(sDate);
                if ( sEntry==null) {

                }else{
                    update=true;
                    activityMainBinding.entryContent.setText(sEntry.getContent());
                }

                changeButtonText();
            }
        });

        activityMainBinding.saveDiaryEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update==true){
                    diaryEntryViewModel.updateEntry(activityMainBinding.entryContent.getText().toString());
                    Toast.makeText(MainActivity.this, "Diary Entry Updated", Toast.LENGTH_SHORT).show();
                }else {
                    diaryEntryViewModel.insertEntry(activityMainBinding.getTheDate(), activityMainBinding.entryContent.getText().toString());
                    Toast.makeText(MainActivity.this, "Diary Entry Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void changeButtonText( ){
        if (update==true){
            activityMainBinding.saveDiaryEntryButton.setText(" Update Entry");
        }else{
            activityMainBinding.saveDiaryEntryButton.setText("Save Entry");
        }
    }

}