package dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ArticleDto {

    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;
    private long id;
    private String name;
    private String title;
    private String content;
    private List<CommentDto> commentDtoList = new ArrayList<>();

    public ArticleDto(Long id, String name, String title, String content){
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
    }

    public ArticleDto(Long id, String name, String title, String content, LocalDateTime insertedDate) {
        this(id, name, title, content);
        this.insertedDate = insertedDate;
    }

    public ArticleDto(Long id, String name, String title, String content, LocalDateTime insertedDate, LocalDateTime updatedDate) {
        this(id, name, title, content, insertedDate);
        this.updatedDate = updatedDate;
    }

    public ArticleDto(Long id, String name, String title, String content, LocalDateTime insertedDate, LocalDateTime updatedDate, List<CommentDto> commentDtoList) {
        this(id, name, title, content, insertedDate, updatedDate);
        this.commentDtoList = commentDtoList;
    }


    public static ArticleDto makeArticleDto(Long id, String name, String title, String content, LocalDateTime insertedDate) {
        return new ArticleDto(id, name, title, content, insertedDate);
    }

    // Getter & Setter (날짜 관련 Getter/Setter도 추가해야 함)
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public List<CommentDto> getCommentDtoList() { return commentDtoList; }
    public void setCommentDtoList(List<CommentDto> commentDtoList) { this.commentDtoList = commentDtoList; }
    public LocalDateTime getInsertedDate() { return insertedDate; }
    public void setInsertedDate(LocalDateTime insertedDate) { this.insertedDate = insertedDate; }
    public LocalDateTime getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(LocalDateTime updatedDate) { this.updatedDate = updatedDate; }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", insertedDate=" + insertedDate +
                ", commentCount=" + (commentDtoList != null ? commentDtoList.size() : 0) +
                '}';
    }
}