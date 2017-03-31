package statisticsCalculator;

import dbmanagement.ParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jorge on 28/03/2017.
 */
@Service
public class ParticipantsProcessor implements Processor{

    @Autowired
    private ParticipantsRepository dat;

    //public Map<String, List<Double>> statistics = new HashMap<String, List<Double>>();
    //public Map<String, Object> statistics = new HashMap<String, Object>(); //Esto fuerza a un cast quizas mejor pensar otra cosa...

    private Long amount;
    private Map<String,Long> ageAgrupation;

    //Quizas es mejor cambiar la estructura de forma que cad método devuelva lo que tiene que devolver y se use en el dashboard,
    //Sino el usuario tiene que saber como castear las cosas.
    @Override
    public void Update() {
        //Updates all the info
        amount++;
    }


    @Autowired
    public ParticipantsProcessor(ParticipantsRepository dat){
        this.dat=dat;
        amount= dat.count();
        Map<String,Long> ageAgrupation = new HashMap<>(); //Aqui con consulta
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /*
    Ideas:
        Amount of registered Users
        Amount of users per nationality
        Amount of users by ranges of age
        Users with the same name (useless for Council)

     */

    /*public void calculateTotalUsers(){
        int amount=0;
        //Consulta para conseguir dato
        statistics.put("totalUsers",amount);
    }

    public void calculateUsersByRange(){
        //Quizas demasiado complicada
    }

    public void calculateUsersByNationality(){
        Map<String,Long> agrupation = new HashMap<String,Long>();
        //Consulta para conseguir dato
        statistics.put("UsersByNationality",agrupation);
    }
    */


}
