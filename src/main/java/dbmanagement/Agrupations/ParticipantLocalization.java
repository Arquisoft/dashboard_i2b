package dbmanagement.Agrupations;

/**
 * Created by Jorge on 01/04/2017.
 * Class used for aggrupation by zone in the ParticipantsRepositoryCustomImpl class to obtain participants grouped
 * by nationality
 * @see dbmanagement.ParticipantsRepositoryCustomImpl
 */
public class ParticipantLocalization {

    private String nationality;

    private long amount;

    public ParticipantLocalization(String nationality, long amount){
        this.nationality=nationality;
        this.amount=amount;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
