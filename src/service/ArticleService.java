package service;

import crudInterface.CrudInterface;
import dao.ArticleDAO;
import dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleService {
    private CrudInterface repository = new ArticleDAO();

    public List<ArticleDto> all(){
        return repository.all();
    }
    public  void newArticle(ArticleDto dto){
        dto.setInsertedDate(LocalDateTime.now());
        repository.newArticle(dto);
    }
    public ArticleDto detail(Long id){
        return repository.detail(id);
    }
    public boolean delete(long id){
        return repository.delete(id);
    }
    public void update(ArticleDto dto){
        dto.setUpdatedDate(LocalDateTime.now());
        repository.update(dto);
    }
}
