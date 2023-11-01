package com.example.scannerqr.DTO;

import android.os.Parcel;
        import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_code")

public class CodeDTO implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;
    private String content;

    public CodeDTO() {
    }

    public CodeDTO(String type, String content) {
        this.type = type;
        this.content = content;
    }

    protected CodeDTO(Parcel in) {
        id = in.readInt();
        type = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(type);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CodeDTO> CREATOR = new Creator<CodeDTO>() {
        @Override
        public CodeDTO createFromParcel(Parcel in) {
            return new CodeDTO(in);
        }

        @Override
        public CodeDTO[] newArray(int size) {
            return new CodeDTO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
