package kafka_random_producer;

import kafka_subsystem.MessageListener;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolás on 26/03/2017.
 */
@ManagedBean
public class TestListener extends MessageListener{

    private List<String> dataList;

    public TestListener(){
        this.dataList = new ArrayList<>();
    }

    public void listen(String data) {
        super.listen(data);
        dataList.add(data);
    }

    public List<String> getDataList() {
        return dataList;
    }
}
