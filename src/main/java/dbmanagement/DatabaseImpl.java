package dbmanagement;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.Agrupations.ProposalCommented;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */
@Service
public class DatabaseImpl implements Database {

    @Autowired
    private ProposalRepository proposalRepo;
    @Autowired
    private CommentsRepository commentsRepo;
    @Autowired
    private ParticipantsRepository participantsRepo;
    @Autowired
    private ParticipantsRepositoryCustom participantsCustomRepo;
    @Autowired
    private ProposalsRepositoryCustom proposalsCustomRepo;

    @Autowired
    public DatabaseImpl(ProposalRepository proposalRepo
                        , CommentsRepository commentsRepo
                        , ParticipantsRepository participantsRepo
                        , ParticipantsRepositoryCustom participantsCustomRepo
                        , ProposalsRepositoryCustom proposalsCustomRepo){
        this.proposalRepo=proposalRepo;
        this.commentsRepo=commentsRepo;
        this.participantsRepo=participantsRepo;
        this.participantsCustomRepo=participantsCustomRepo;
        this.proposalsCustomRepo = proposalsCustomRepo;
    }


    @Override
    public Proposal findProposal(String author, String category, Date created) {
        return proposalRepo.findByAuthorAndCategoryAndCreated(author,category,created);
    }

    @Override
    public Proposal findProposal(String id) {
        return proposalRepo.findOne(new ObjectId(id));
    }

    @Override
    public Proposal insert(Proposal proposal) {
        return proposalRepo.insert(proposal);
    }

    @Override
    public Participant save(Participant participant) {
        return participantsRepo.save(participant);
    }

    @Override
    public Proposal save(Proposal proposal) {
        return proposalRepo.save(proposal);
    }

    @Override
    public Comment save(Comment comment) {
        return commentsRepo.save(comment);
    }


    @Override
    public void reset() {
        proposalRepo.deleteAll();
        participantsRepo.deleteAll();
        commentsRepo.deleteAll();
    }

    @Override
    public void delete(Proposal proposal) {
        proposalRepo.delete(proposal);
    }


    @Override
    public Long countProposals() {
        return proposalRepo.count();
    }

    @Override
    public Long countComments() {
        return proposalRepo.countByComments();
    }


    @Override
    public List<Proposal> findTop5ProposalsByVotes() {
        return proposalRepo.findTop5ByOrderByVotesDesc();
    }

    @Override
    public List<ProposalCommented> findTop5MostCommentedProposal() {
        return proposalsCustomRepo.getProposalsMostCommented();
    }
}
