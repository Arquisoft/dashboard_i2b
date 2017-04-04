package dbmanagement;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.Agrupations.ProposalCommented;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
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
    private CommentsRepositoryCustom commentsCustomRepo;

    @Autowired
    public DatabaseImpl(ProposalRepository proposalRepo
                        , CommentsRepository commentsRepo
                        , ParticipantsRepository participantsRepo
                        , ParticipantsRepositoryCustom participantsCustomRepo
                        , ProposalsRepositoryCustom proposalsCustomRepo
                        , CommentsRepositoryCustom commentsCustomRepo){
        this.proposalRepo=proposalRepo;
        this.commentsRepo=commentsRepo;
        this.participantsRepo=participantsRepo;
        this.participantsCustomRepo=participantsCustomRepo;
        this.proposalsCustomRepo = proposalsCustomRepo;
        this.commentsCustomRepo=commentsCustomRepo;
    }


    @Override
    public Proposal findPropByAuthorAndCategoryAndCreated(String author, String category, Date created) {
        return proposalRepo.findByAuthorAndCategoryAndCreated(author,category,created);
    }

    @Override
    public List<Participant> getParticipants() { return participantsRepo.findAll(); }
    @Override
    public List<Proposal> getProposals() {
        return proposalRepo.findAll();
    }

    @Override
    public List<Comment> getComments() {
        return commentsRepo.findAll();
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
    public Long countParticipants() {
        return participantsRepo.count();
    }

    @Override
    public Long countProposals() {
        return proposalRepo.count();
    }

    @Override
    public Long countComments() {
        return commentsRepo.count();
    }

    @Override
    public List<ParticipantLocalization> getParticipantsGroupByNationality() {
        return participantsCustomRepo.getParticipantsGroupByNationality();
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
