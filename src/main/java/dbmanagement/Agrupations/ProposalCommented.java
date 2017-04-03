package dbmanagement.Agrupations;

/**
 * Created by Jorge on 03/04/2017.
 */
public class ProposalCommented {

    private String author;
    private int amountComments;

    public ProposalCommented(String author, int amountComments){
        this.author=author;
        this.amountComments=amountComments;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAmountComments(int amountComments) {
        this.amountComments = amountComments;
    }

    public String getAuthor() {
        return author;
    }

    public int getAmountComments() {
        return amountComments;
    }
}
