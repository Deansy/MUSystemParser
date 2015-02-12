import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MIUParser miu = new MIUParser();

        List<String> states = new ArrayList<String>();
        states = miu.nextStates("MI");
        printStates(states, "MI");

        states = miu.nextStates("MIU");
        printStates(states, "MIU");

        states = miu.nextStates("MUI");
        printStates(states, "MUI");

        states = miu.nextStates("MIIII");
        printStates(states, "MIIII");

        states = miu.nextStates("MUUII");
        printStates(states, "MUUII");

        states = miu.nextStates("MUUUI");
        printStates(states, "MUUUI");


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
