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

    private Long amount;


    @Autowired
    private Database dat;

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

    /*
         Ideas:

         Comments with most length

     */
}
