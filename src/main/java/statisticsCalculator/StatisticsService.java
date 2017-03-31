package statisticsCalculator;

import java.util.List;
import java.util.Map;

/**
 * Created by Jorge on 28/03/2017.
 *
 * Interface to provide Services to the other components.
 */
public interface StatisticsService {

    //Map<String, List<Double>> o Map<String, Map<String,Double>>
    Map<String, List<Double>> getParticipantsInfo();

    Map<String, List<Double>> getProposalsInfo();

    Map<String, List<Double>> getCommentsInfo();

}
