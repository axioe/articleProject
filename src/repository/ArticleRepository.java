package repository;

import crudInterface.CrudInterface;
import dto.ArticleDto;
import dto.CommentDto;

import javax.xml.stream.events.Comment;
import java.util.List;

public class ArticleRepository implements CrudInterface {

    @Override
    public List<ArticleDto> all() {
        return List.of();
    }

    @Override
    public void newArticle(ArticleDto articleDto) {

    }

    @Override
    public ArticleDto detail(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(ArticleDto articleDto) {

    }

    @Override
    public void insertComment(CommentDto commentDto) {

    }

    @Override
    public void updateComment(CommentDto commentDto) {

    }

    @Override
    public void deleteComment(Long deleteCommentId) {

    }
}
