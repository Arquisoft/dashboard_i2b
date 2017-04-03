package statisticsCalculator;

import dbmanagement.Agrupations.ProposalCommented;
import dbmanagement.ProposalRepository;
import dbmanagement.ProposalsRepositoryCustom;
import dbmanagement.ProposalsRepositoryCustomImpl;
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
    private ProposalRepository dat;
    @Autowired
    private ProposalsRepositoryCustom datCust;

    private Long amount;
    private List<Proposal> topVotes;
    private List<ProposalCommented> topCommented;

    @Autowired
    public ProposalsProcessor(ProposalRepository dat, ProposalsRepositoryCustom datCust){
        this.dat = dat;
        this.datCust=datCust;
        amount = dat.count();
        topVotes = dat.findTop5ByOrderByVotesDesc();
        topCommented = datCust.getProposalsMonstCommented();
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
        //updateTopCommented(data);
    }

    private void updateTopVotes(Proposal data) {
        int position =(int) (topVotes.size() - topVotes.stream().filter(element-> element.getVotes()<data.getVotes()).count());
        if(position<5) {
            topVotes.add(position, data);
            if(topVotes.size()==5)
                topVotes.remove(topVotes.size()); //Quitamos el ultimo
        }
    }

    //Really strange that the update is going to change anything
    //As a proposal can't be created with comments in it.
    private void updateTopCommented(Proposal data){
        int position =(int) (topCommented.size() - topCommented.stream().filter(element-> element.getAmountComments()<data.getComments().size()).count());
        if(position<5) {
            topCommented.add(position, new ProposalCommented(data.getAuthor(),data.getComments().size()+1));
            if(topCommented.size()==5)
                topCommented.remove(topCommented.size()); //Quitamos el ultimo
        }
    }
}
    /*
        Ideas:
            Amount of Proposals
            TOP 5 Proposals (nº of votes)
            TOP 5 commented (controversial proposals)
     */


