package statisticsCalculator;

import dbmanagement.ParticipantsRepository;
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
    public CommentsProcessor( ParticipantsRepository dat){
        amount = 0L;
    }


    public void update(Comment data){
        amount++;
    }

    public Long getAmount() {
        return amount;
    }

    /*
         Ideas::

         CComments with most length
     */
}
