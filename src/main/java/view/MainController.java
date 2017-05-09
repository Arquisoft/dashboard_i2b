package view;


import com.esotericsoftware.minlog.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import statisticsCalculator.Processor;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);

    //References to the different processors.
    @Autowired
    private Processor proc;

    @RequestMapping("/")
    public String landing(Model model) {
        model.addAttribute("proc", proc);
        return "dashboard";
    }

    @ModelAttribute(name = "proc")
    public Processor getProccesor(){
        return proc;
    }

    @PatchMapping("/")
    public String update(Model model){
        Log.info("Patch request received, updating...");
        model.asMap().replace("proc", proc);
        return "dashboard";
    }
}