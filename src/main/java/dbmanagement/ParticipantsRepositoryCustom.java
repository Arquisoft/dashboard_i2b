package dbmanagement;

import dbmanagement.Agrupations.ParticipantLocalization;

import java.util.List;

/**
 * Created by Jorge on 01/04/2017.
 */
public interface ParticipantsRepositoryCustom {

    List<ParticipantLocalization> getParticipantsGroupByNationality();
}
