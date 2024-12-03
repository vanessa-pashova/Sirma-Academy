import java.util.Scanner;

public class Articles_8 {

    String title;
    String content;
    String author;

    public Articles_8(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void edit(String newContent) {
        this.content = newContent;
    }

    public void changeAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    public void rename(String newTitle) {
        this.title = newTitle;
    }

    @Override
    public String toString() {
        return this.title + " - " + this.content + ": " + this.author;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] articleData = scanner.nextLine().split(", ");
        String title = articleData[0];
        String content = articleData[1];
        String author = articleData[2];

        Articles_8 article = new Articles_8(title, content, author);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split(": ");
            String command = tokens[0];
            String argument = tokens[1];

            if (command.equals("Edit"))
                article.edit(argument);

            else if (command.equals("ChangeAuthor"))
                article.changeAuthor(argument);

            else if (command.equals("Rename"))
                article.rename(argument);
        }

        System.out.println(article);
    }
}
