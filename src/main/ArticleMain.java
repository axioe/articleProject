package main;

import view.ArticleView;

import java.util.Scanner;

public class ArticleMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArticleView view = new ArticleView();

        while (true) {
            System.out.println("\n--- 게시판 시스템 ---");
            System.out.println("0.전체보기 1.새글 2.자세히보기 3.게시글삭제 4.수정 5.종료");
            System.out.print("메뉴 선택 > ");

            int menu = sc.nextInt();

            if (menu == 5) {
                System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
                return;
            }
            switch (menu) {
                case 0:
                    view.showAll();
                    break;
                case 1:
                    view.showNewArticle();
                    break;
                case 2:
                    view.showDetail();
                    break;
                case 3:
                    view.showDelete();
                    break;
                case 4:
                    view.showUpdate();
                    break;
                default:
                    System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
                    break;
            }
        }
    }
}