package dao;

import crudInterface.CrudInterface;
import db.DBConn;
import dto.ArticleDto;
import dto.CommentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO implements CrudInterface {

    private Connection conn = DBConn.getConnection();

    @Override
    public List<ArticleDto> all() {
        List<ArticleDto> articles = new ArrayList<>();
        String sql = "SELECT * FROM article ORDER BY id DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ArticleDto dto = new ArticleDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("inserted_date").toLocalDateTime(),
                        rs.getTimestamp("updated_date") != null ? rs.getTimestamp("updated_date").toLocalDateTime() : null
                );
                articles.add(dto);
            }
            getArticleComments(articles);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    private void getArticleComments(List<ArticleDto> articles) {
        String sql = "SELECT * FROM comments WHERE article_id = ?";

        for (ArticleDto article : articles) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, article.getId());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        CommentDto cDto = new CommentDto(
                                rs.getLong("comment_id"),
                                rs.getLong("article_id"),
                                rs.getString("name"),
                                rs.getString("content")
                        );
                        article.addComments(cDto);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void newArticle(ArticleDto articleDto) {
        String sql = "INSERT INTO article(name, title, content, inserted_date, updated_date) VALUES(?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, articleDto.getName());
            pstmt.setString(2, articleDto.getTitle());
            pstmt.setString(3, articleDto.getContent());
            pstmt.setTimestamp(4, Timestamp.valueOf(articleDto.getInsertedDate()));
            pstmt.setTimestamp(5, articleDto.getUpdatedDate() != null ? Timestamp.valueOf(articleDto.getUpdatedDate()) : null);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArticleDto detail(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        ArticleDto dto = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dto = new ArticleDto(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getTimestamp("inserted_date").toLocalDateTime(),
                            rs.getTimestamp("updated_date") != null ? rs.getTimestamp("updated_date").toLocalDateTime() : null
                    );
                    List<ArticleDto> temp = new ArrayList<>();
                    temp.add(dto);
                    getArticleComments(temp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(ArticleDto articleDto) {
        String sql = "UPDATE article SET name=?, title=?, content=?, updated_date=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, articleDto.getName());
            pstmt.setString(2, articleDto.getTitle());
            pstmt.setString(3, articleDto.getContent());
            pstmt.setTimestamp(4, Timestamp.valueOf(articleDto.getUpdatedDate()));
            pstmt.setLong(5, articleDto.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertComment(CommentDto commentDto) {
        String sql = "INSERT INTO comments(name, content, article_id) VALUES(?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, commentDto.getName());
            pstmt.setString(2, commentDto.getContent());
            pstmt.setLong(3, commentDto.getArticleId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        String sql = "UPDATE comments SET content=? WHERE comment_id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, commentDto.getContent());
            pstmt.setLong(2, commentDto.getCommentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComment(Long deleteCommentId) {
        String sql = "DELETE FROM comments WHERE comment_id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, deleteCommentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}