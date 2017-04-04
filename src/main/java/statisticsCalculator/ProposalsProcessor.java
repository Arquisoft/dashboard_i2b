package statisticsCalculator;

import dbmanagement.Agrupations.ProposalCommented;
import dbmanagement.Database;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
public class ProposalsProcessor{

    @Autowired
    private Database dat;

    private Long amount;
    private List<Proposal> topVotes;
    private List<ProposalCommented> topCommented;

    @Autowired
    public ProposalsProcessor(Database dat){
        this.dat = dat;
        amount = dat.countProposals();
        topVotes = dat.findTop5ProposalsByVotes();
        topCommented = dat.findTop5MostCommentedProposal();
    }

    public Long getAmount() {
        return amount;
    }

    public List<Proposal> getTopVotes() {
        return topVotes;
    }

    public List<ProposalCommented> getTopCommented(){ return topCommented; }

    public void update(Proposal data){
        amount++;
        updateTopVotes(data);
    }

    public void updateCommentReceived(){
        updateTopCommented();
    }

    private void updateTopVotes(Proposal data) {
        int position =(int) (topVotes.size() - topVotes.stream().filter(element-> element.getVotes()<data.getVotes()).count());
        if(position<5) {
            topVotes.add(position, data);
            if(topVotes.size()>=6)
                topVotes.remove(topVotes.size() - 1); //Quitamos el ultimo
        }
    }
    //Be carefull, it may cause overhead in the db system.
    private void updateTopCommented() {
        topCommented = dat.findTop5MostCommentedProposal();
    }

}


