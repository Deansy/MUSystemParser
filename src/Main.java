import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        MIUParser miu = new MIUParser();

        //MIIIUII
        //MIIIUIIIIIUIIIIIUIIIIIUIIIIIUIIIIIUII
        List<String> nodeCheck = miu.breadthFirstSearch("MIIIUII", false, true);
        List<String> noNodeCheck = miu.breadthFirstSearch("MIIIUII", false, false);

        System.out.println(nodeCheck);
        System.out.println(noNodeCheck);


    }
}
