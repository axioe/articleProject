package dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleDto {
    private Long id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;
    private List<CommentDto> commentDtoList = new ArrayList<>();

    // 생성자 4종 (기존과 동일하게 유지)
    public ArticleDto(Long id, String name, String title, String content) {
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

    // 팩토리 메서드
    public static ArticleDto makeArticleDto(Long id, String name, String title, String content, LocalDateTime insertedDate) {
        return new ArticleDto(id, name, title, content, insertedDate);
    }

    // 댓글 추가 메서드 (엔티티 대신 DTO 리스트에 바로 추가)
    public void addComments(CommentDto dto) {
        this.commentDtoList.add(dto);
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getInsertedDate() { return insertedDate; }
    public void setInsertedDate(LocalDateTime insertedDate) { this.insertedDate = insertedDate; }
    public LocalDateTime getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(LocalDateTime updatedDate) { this.updatedDate = updatedDate; }
    public List<CommentDto> getCommentDtoList() { return commentDtoList; }
    public void setCommentDtoList(List<CommentDto> commentDtoList) { this.commentDtoList = commentDtoList; }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%s\t%s", id, name, title, insertedDate);
    }
}