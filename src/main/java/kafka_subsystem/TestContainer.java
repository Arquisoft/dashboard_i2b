package kafka_subsystem;

import domain.UserLoginData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolás on 29/03/2017.
 */
public class TestContainer {

    private static List<UserLoginData> dataList = new ArrayList<>();

    public static void add(UserLoginData data){
        dataList.add(data);
    }

    public static List<UserLoginData> getList(){
        return dataList;
    }
}
