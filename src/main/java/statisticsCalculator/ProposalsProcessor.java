package statisticsCalculator;

import dbmanagement.Agrupations.ProposalCommented;
import dbmanagement.Database;
import dbmanagement.ProposalRepository;
import dbmanagement.ProposalsRepositoryCustom;
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

    public void updateCommentRecieved(){
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

    //Really strange that the update is going to change anything
    //As a proposal can't be created with comments in it.
    /*private void updateTopCommented(Proposal data){
        int position = (int) (topCommented.size() - topCommented.stream().filter(element-> element.getAmountComments()<data.getComments().size()).count());
        if(position<5) {
            topCommented.add(position, new ProposalCommented(data.getAuthor(),data.getComments().size()+1));
            if(topCommented.size()==5)
                topCommented.remove(topCommented.size()); //Quitamos el ultimo
        }
    }*/

    //Be carefull, it may cause overhead in the db system.
    private void updateTopCommented() {

        topCommented = dat.findTop5MostCommentedProposal();
    }

}


