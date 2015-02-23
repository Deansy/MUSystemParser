import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MIUParser miu = new MIUParser();


        // Next states
//        List<String> states = new ArrayList<String>();
//        states = miu.nextStates("MI");
//        printStates(states, "MI");
//
//        states = miu.nextStates("MIU");
//        printStates(states, "MIU");
//
//        states = miu.nextStates("MUI");
//        printStates(states, "MUI");
//
//        states = miu.nextStates("MIIII");
//        printStates(states, "MIIII");
//
//        states = miu.nextStates("MUUII");
//        printStates(states, "MUUII");
//
//        states = miu.nextStates("MUUUI");
//        printStates(states, "MUUUI");
//
//        states = miu.nextStates("MUUIUU");
//        printStates(states, "MUUIUU");



        // Extend Path
        List<String> inputPath = new ArrayList<String>();
        inputPath.add("MI");
        inputPath.add("MII");
        List<List<String>> x = miu.extendPath(inputPath);



        for (List<String> list : x) {
           // printStates(list, " ");
        }



        List<String> bfs = miu.breadthFirstSearch("MIIUIIUIIUIIU");

        printStates(bfs, "MI");


    }

    static void printStates(List<String> states, String originalString) {
        System.out.println(originalString);
        System.out.print("[");
        for (int i = 0; i < states.size(); i++) {
            String s = states.get(i);
            if (i == states.size() - 1) {
                System.out.print(s);
            }
            else {
                System.out.print(s + ", ");
            }
        }
        System.out.print("]");
        System.out.println("");
    }
}
