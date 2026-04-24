package crudInterface;

import dto.ArticleDto;
import dto.CommentDto;
import java.util.List;

public interface CrudInterface {
    // 게시글
    List<ArticleDto> all();
    void    newArticle(ArticleDto articleDto);
    ArticleDto detail(Long id);
    boolean delete(Long id);
    void    update(ArticleDto articleDto);

    // 댓글
    void insertComment(CommentDto commentDto);
    void updateComment(CommentDto commentDto);
    void deleteComment(Long deleteCommentId);
}