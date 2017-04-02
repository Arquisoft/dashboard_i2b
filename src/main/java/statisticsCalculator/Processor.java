package statisticsCalculator;

import domain.Comment;
import domain.Participant;
import domain.Proposal;

/**
 * Created by Jorge on 28/03/2017.
 *
 * Interface that defines the processors that manage the data
 */
public interface Processor {

    void update(Participant data);
    void update(Proposal data);
    void update(Comment data);

}
