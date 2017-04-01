package statisticsCalculator;

import dbmanagement.ProposalRepository;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<Proposal> topVotes;

    @Override
    public void Update() {
        amount++;
    }

    public void Update(Proposal data){
        amount++;
        updateTopVotes(data);
    }

    private void updateTopVotes(Proposal data) {
        int position =(int) (5 - topVotes.stream().filter(element-> element.getVotes()<data.getVotes()).count());
        if(position<5) {
            topVotes.add(position, data);
            topVotes.remove(5); //Quitamos el ultimo
        }

    }

    ;

    @Autowired
    public ProposalsProcessor(ProposalRepository dat){
        this.dat=dat;
        amount = dat.count();
        topVotes = dat.findTop5ByOrderByVotesDesc();
    }

    public Long getAmount() {
        return amount;
    }

    public List<Proposal> getTopVotes() {
        return topVotes;
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
