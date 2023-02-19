import java.util.ArrayList;
import java.util.List;

public class QualityOfLifeMethods {
    statgen stats = new statgen();
    List statobj = new ArrayList<>();

    public QualityOfLifeMethods (String rolltype){
        if(rolltype.equals("User")){
            for (int i = 0; i < 6; i++){
                statobj.add(stats.statscreate1().get(i));
            }
        } else if (rolltype.equals("Stand")) {
            for (int i = 0; i < 6; i++){
                statobj.add(stats.statscreate2().get(i));
            }
        }

    }
}
