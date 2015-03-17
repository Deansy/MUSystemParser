import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MIUParser miu = new MIUParser();

        int practical = 1;

        if (practical == 1) {
            // Next states
            System.out.println(miu.nextStates("MUUUUUU"));
            System.out.println(miu.nextStates("MIIIIII"));
        }


        if (practical == 2) {
            // Extend Path
            List<String> inputPath = new ArrayList<String>();
            inputPath.add("MI");
            inputPath.add("MII");
            List<List<String>> x = miu.extendPath(inputPath);

            for (List<String> list : x) {
                System.out.println(list);
            }

            List<String> bfs = miu.breadthFirstSearch("MIU", false);

            System.out.println(bfs);



            List<String> id = miu.iterativeDeepening("MIUIUIUIUIUIUIUIU", false);

            System.out.println(id);

        }
    }
}
