package DiaryDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = Constants.TABLE_NAME_ENTRY)
public class Entry {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "entry_content")
    private String content;

    @ColumnInfo(name = "date")
    private String date;

    public Entry(String date, String content) {
        this.date = date;
        this.content = content;
    }
    @Ignore
    public Entry() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
