package statisticsCalculator;

import dbmanagement.ProposalRepository;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
public class ProposalsProcessor implements Processor{

    @Autowired
    private ProposalRepository dat;

    private Long amount;
    private List<Proposal> topVotes;

    @Autowired
    public ProposalsProcessor(ProposalRepository dat){
        this.dat = dat;
        amount = dat.count();
        topVotes = dat.findTop5ByOrderByVotesDesc();
    }

    public Long getAmount() {
        return amount;
    }

    public List<Proposal> getTopVotes() {
        return topVotes;
    }

    @Override
    public void Update() {
        amount++;
    }

    public void Update(Proposal data){
        amount++;
        updateTopVotes(data);
    }

    private void updateTopVotes(Proposal data) {
        int position =(int) (topVotes.size() - topVotes.stream().filter(element-> element.getVotes()<data.getVotes()).count());
        if(position<5) {
            topVotes.add(position, data);
            if(topVotes.size()==5)
                topVotes.remove(topVotes.size()); //Quitamos el ultimo
        }
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
