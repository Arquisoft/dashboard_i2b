package statisticsCalculator;

import dbmanagement.ParticipantsRepository;
import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
@Scope(scopeName = "singleton")
public class CommentsProcessor implements Processor{

    @Autowired
    private ParticipantsRepository dat;

    long amount;

    @Override
    public void Update() {
        amount++;
    }

    @Autowired
    public CommentsProcessor( ParticipantsRepository dat){
        this.dat=dat;
        amount = dat.count();
    }

    public long getAmount() {
        return amount;
    }

    /*
         Ideas::

         CComments with most length
     */
}
