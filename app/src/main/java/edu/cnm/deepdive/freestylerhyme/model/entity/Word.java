package edu.cnm.deepdive.freestylerhyme.model.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = @Index(value = "word", unique = true))
public class Word {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "word_id")
  private long id;

  @NonNull
  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String name = "";

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setWord(@NonNull String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return name.toLowerCase().hashCode();
  }

  @Override
  public boolean equals(@Nullable Object obj) {
    return (obj == this) || (obj instanceof Word && name.equalsIgnoreCase(((Word)obj).name));
  }

  @NonNull
  @Override
  public String toString() {
    return name;
  }

}

