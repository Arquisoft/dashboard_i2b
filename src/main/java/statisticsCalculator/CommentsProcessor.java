package statisticsCalculator;

import dbmanagement.ParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
public class CommentsProcessor implements Processor{

    private Long amount;

    @Override
    public void Update() {
        amount++;
    }

    @Autowired
    public CommentsProcessor( ParticipantsRepository dat){
        amount = 0L;
    }

    public Long getAmount() {
        return amount;
    }

    /*
         Ideas::

         CComments with most length
     */
}
