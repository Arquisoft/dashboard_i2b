package statisticsCalculator;

import dbmanagement.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
@Scope("singleton")
public class ProposalsProcessor implements Processor{


    //public Map<String, Object> statistics = new HashMap<String, Object>();

    @Autowired
    private ProposalRepository dat;

    private Long amount;

    @Override
    public void Update() {
        amount++;
    }

    @Autowired
    public ProposalsProcessor(ProposalRepository dat){
        this.dat=dat;
        amount = dat.count();
    }

    public Long getAmount() {
        return amount;
    }



    /*
        Ideas:
            Amount of Proposals
            TOP 5 Proposals (nº of votes)
            TOP 5 commented (controversial proposals)
     */

    /*public void getAmountProposals(){
        int amount;

    }

    public void getMostVotedProposals(){
        //Proposals[5]
    }

    public void getMostControversialProposals(){
        //Proposals[5]
    }
    */

}
