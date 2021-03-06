package statisticsCalculator;

import dbmanagement.Agrupations.ProposalCommented;
import dbmanagement.Database;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
public class ProposalsProcessor{

    public static boolean TESTING = false;

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
        topCommented = new ArrayList<>();
        updateTopCommented();
    }

    public Long getAmount() {
        return amount;
    }

    public List<Proposal> getTopVotes() {
        return topVotes;
    }

    public List<ProposalCommented> getTopCommented(){ return topCommented; }

    public void updateCreate(String data){
        String[] elements = data.split(":");
        String id = elements[1].replace("}", "").replace("\"", "").trim();
        Proposal prop = dat.findProposal(id);
        amount++;
        updateTopVotes(prop);

    }

    public void updateVote(String data){
        String[]  parsedData = data.replaceAll("\\{","").replaceAll("}","").split(":");
        String id = parsedData[1].replaceAll("\"", "").trim();
        Proposal prop = dat.findProposal(id);
        updateTopVotes(prop);
    }

    private void updateTopVotes(Proposal data) {
        Proposal inside = topVotes.stream().filter(e -> e.getTitle().equals(data.getTitle())).findFirst().orElse(null);
        if (inside != null) {
             inside.setVotes(data.getVotes());
        } else  {
            int position =(int) (topVotes.size() - topVotes.stream().filter(element-> element.getVotes()<data.getVotes()).count());
            if(position<5) {
                topVotes.add(position, data);
                if(topVotes.size()>=6)
                    topVotes.remove(topVotes.size() - 1); //Take out the last one
            }
        }
    }

    protected void updateTopCommented(){
        //topCommented = dat.findTop5MostCommentedProposal();
    }

}


