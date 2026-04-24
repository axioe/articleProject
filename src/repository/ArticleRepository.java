package repository;

import crudInterface.CrudInterface;
import dto.ArticleDto;
import dto.CommentDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository implements CrudInterface {

    private static Long ariticlId = 1L;
    private static Long commentId = 1L;
    private static List<ArticleDto> articleList = new ArrayList<>();



    @Override
    public List<ArticleDto> all() {
        return articleList;
    }

    @Override
    public void newArticle(ArticleDto articleDto) {
        articleDto.setId(ariticlId++);
        articleList.add(articleDto);
    }

    @Override
    public ArticleDto detail(Long id) {
        for (ArticleDto article : articleList) {
            if (article.getId().equals(id)) {
                return article;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return articleList.removeIf(article -> article.getId().equals(id));
    }

    @Override
    public void update(ArticleDto articleDto) {
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getId().equals(articleDto.getId())) {
                articleList.set(i, articleDto);
                return;
            }
        }
    }

    @Override
    public void insertComment(CommentDto commentDto) {
        ArticleDto article = detail(commentDto.getArticleId());
        if (article != null) {
            commentDto.setCommentId(commentId++);
            article.addComments(commentDto);
        }
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        for (ArticleDto article : articleList) {
            for (CommentDto comment : article.getCommentDtoList()) {
                if (comment.getCommentId().equals(commentDto.getCommentId())) {
                    comment.setContent(commentDto.getContent());
                    return;
                }
            }
        }
    }

    @Override
    public void deleteComment(Long deleteCommentId) {
        for (ArticleDto article : articleList) {
            article.getCommentDtoList().removeIf(c -> c.getCommentId().equals(deleteCommentId));
        }
    }
}