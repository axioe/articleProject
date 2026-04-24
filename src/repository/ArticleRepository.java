package repository;

import crudInterface.CrudInterface;

import javax.xml.stream.events.Comment;
import java.util.List;

public class ArticleRepository implements CrudInterface {


    @Override
    public List<Article> all() {
        return List.of();
    }

    @Override
    public void newArticle(Article article) {

    }

    @Override
    public Article detail(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Article article) {

    }

    @Override
    public void insertComment(Comment comment) {

    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public void deleteComment(Long deleteCommentId) {

    }
}
