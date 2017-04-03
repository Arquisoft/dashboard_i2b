package dbmanagement;

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
}
