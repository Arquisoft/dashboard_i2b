package dbmanagement;

import domain.Proposal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
/**
 * Created by Antonio Nicolas on 30/03/2017.
 * Repository class for Proposal related queries
 */
@Repository
public interface ProposalRepository extends MongoRepository<Proposal, String> {

    Proposal findByAuthorAndCategoryAndCreated(String author, String category, Date created);

    List<Proposal> findTop5ByOrderByVotesDesc();

    long count();
}
