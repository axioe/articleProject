package view;

import dto.ArticleDto;
import dto.CommentDto;
import service.ArticleService;
import service.CommentService;

import java.util.List;
import java.util.Scanner;

public class ArticleView {
    private Scanner sc = new Scanner(System.in);
    private ArticleService articleService = new ArticleService();
    private CommentService commentService = new CommentService();
    private List<ArticleDto> articles;

    public void showAll(){
        System.out.println("========================================");
        System.out.println("id\tname\title\t\t작성일");
        System.out.println("========================================");

        articles = articleService.all();
        if (articles.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        }else {
            for (ArticleDto a : articles){
                System.out.println(a);
                for (CommentDto c : a.getCommentDtoList()){
                    System.out.println(c);
                }
            }
        }
        System.out.println("========================================");
    }
    public void showNewArticle(){
        sc.nextLine();

        System.out.println("새글 입력창입니다.");
        System.out.print("작성자: "); String name = sc.next();
        System.out.print("제목: "); String title = sc.next();
        System.out.print("내용: "); String content = sc.next();
    }
    public void showDetail(){
        System.out.println("확인할 게시글의 아이디를 입력하세요 :");
        Long id = sc.nextLong();
        ArticleDto article = articleService.detail(id);

        if (articles == null){
            System.out.println("해당 게시글이 없습니다.");
            return;
        }
        System.out.println(" ID : " + article.getId());
        System.out.println(" Name : " + article.getName());
        System.out.println(" Title : " + article.getTitle());
        System.out.println(" Content : " + article.getContent());
        System.out.println(" 작성일 : " + article.getInsertedDate());
        System.out.println(" 수정일 : " + article.getUpdatedDate());

        System.out.println("\n 댓글 리스트");
        if (article.getCommentDtoList().isEmpty()){
            System.out.println("해당 게시글에는 댓글이 없습니다.");
        }else {
            for (CommentDto c : article.getCommentDtoList()){
                System.out.println(c);
            }
        }
        System.out.println("\n1.댓글입력 2.댓글수정 3.댓글삭제 4.돌아가기");
        System.out.print("> ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> { // 댓글 입력
                System.out.print("등록자 이름: "); String cName = sc.next();
                System.out.print("내용: "); String cContent = sc.next();
                // [설계 조건] 신규 댓글 생성 시 null commentId 전달
                commentService.commentAdd(new CommentDto(null, id, cName, cContent));
            }
            case 2 -> { // 댓글 수정
                System.out.print("수정할 댓글 번호(commentId): "); Long cId = sc.nextLong();
                System.out.print("수정 내용: "); String uContent = sc.next();
                // [설계 조건] 수정 시 실제 id 전달
                commentService.commentUpdate(new CommentDto(cId, id, "", uContent));
            }
            case 3 -> { // 댓글 삭제
                System.out.print("삭제할 댓글 번호(commentId): "); Long dId = sc.nextLong();
                commentService.commentDelete(dId);
            }
        }
    }
    public void showDelete(){
        System.out.println("삭제할 게시글 ID 입력 : ");
        Long id = sc.nextLong();
        if (articleService.delete(id)){
            System.out.println("삭제되었습니다");
        }else{
            System.out.println("실패했습니다.");
        }
    }
    public void showUpdate(){
        System.out.println("수정할 게시글 ID 입력");
        Long id = sc.nextLong();
        ArticleDto current = articleService.detail(id);

        if (current == null){
            System.out.println("게시글을 찾을 수 없습니다.");
            return;
        }
        System.out.println("현재 제목: " + current.getTitle());
        System.out.print("새 제목: "); String title = sc.next();
        System.out.println("현재 내용: " + current.getContent());
        System.out.print("새 내용: "); String content = sc.next();

        current.setTitle(title);
        current.setContent(content);
        articleService.update(current);
        System.out.println("수정이 완료되었습니다.");
    }
}
