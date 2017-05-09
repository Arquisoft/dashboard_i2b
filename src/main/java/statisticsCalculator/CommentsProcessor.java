package statisticsCalculator;

import dbmanagement.Database;
import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
public class CommentsProcessor{

    @Autowired
    private Database dat;
    @Autowired
    private ProposalsProcessor propPorc;

    private Long amount;

    @Autowired
    public CommentsProcessor( Database dat){
        this.dat=dat;
        amount=dat.countComments();
    }

    public void update(Comment data){
        amount++;
    }

    public Long getAmount() {
        return amount;
    }

    public void updateCreate(String data){
        amount++;
    }
    public void updateVote(String data){}
}
