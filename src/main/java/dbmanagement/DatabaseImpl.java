package dbmanagement;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.Agrupations.ProposalCommented;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public DatabaseImpl(ProposalRepository proposalRepo, CommentsRepository commentsRepo, ParticipantsRepository participantsRepo,
                        ParticipantsRepositoryCustom participantsCustomRepo, ProposalsRepositoryCustom proposalsCustomRepo, CommentsRepositoryCustom commentsCustomRepo){
        this.proposalRepo=proposalRepo;
        this.commentsRepo=commentsRepo;
        this.participantsRepo=participantsRepo;
        this.participantsCustomRepo=participantsCustomRepo;
        this.participantsCustomRepo=participantsCustomRepo;
        this.commentsCustomRepo=commentsCustomRepo;
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
