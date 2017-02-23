package greendao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "TDATABASE".
 */
@Entity
public class TDatabase {

    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String content;

    @Generated
    public TDatabase() {
    }

    public TDatabase(Long id) {
        this.id = id;
    }

    @Generated
    public TDatabase(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}