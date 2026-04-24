package service;

import crudInterface.CrudInterface;
import dao.ArticleDAO;
import dto.ArticleDto;
import dto.CommentDto;

import java.time.LocalDateTime;
import java.util.List;

public class CommentService {
    private  CrudInterface dao = new ArticleDAO();

    public void commentAdd(CommentDto comment){
        dao.insertComment(comment);
    }
    public  void  commentUpdate(CommentDto comment){
        dao.updateComment(comment);
    }
    public void commentDelete(Long deleteCommentId){
        dao.deleteComment(deleteCommentId);
    }

}
