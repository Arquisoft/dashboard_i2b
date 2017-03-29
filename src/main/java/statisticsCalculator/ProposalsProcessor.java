package statisticsCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jorge on 28/03/2017.
 */
public class ProposalsProcessor implements Processor{


    public Map<String, Object> statistics = new HashMap<String, Object>();

    @Override
    public void Update() {

    }

    /*
        Ideas:
            Amount of Proposals
            TOP 5 Proposals (nº of votes)
            TOP 5 commented (controversial proposals)
     */

    public void getAmountProposals(){
        int amount;

    }

    public void getMostVotedProposals(){
        //Proposals[5]
    }

    public void getMostControversialProposals(){
        //Proposals[5]
    }


}
