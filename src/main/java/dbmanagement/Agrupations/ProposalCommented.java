package dbmanagement.Agrupations;

/**
 * Created by Jorge on 03/04/2017.
 */
public class ProposalCommented {

    private String title;
    private int amountComments;

    public ProposalCommented(String title, int amountComments){
        this.title=title;
        this.amountComments=amountComments;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmountComments(int amountComments) {
        this.amountComments = amountComments;
    }


    public int getAmountComments() {
        return amountComments;
    }
}
