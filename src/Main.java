import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        MIUParser miu = new MIUParser();

        //MIIIUII
        //MIIIUIIIIIUIIIIIUIIIIIUIIIIIUIIIIIUII
        List<String> nodeCheck = miu.iterativeDeepening("MIIIUIIIIIUIIIIIUIIIIIUIIIIIUIIIIIUII", false, true);
        List<String> noNodeCheck = miu.iterativeDeepening("MIIIUIIIIIUIIIIIUIIIIIUIIIIIUIIIIIUII", false, false);

        System.out.println(nodeCheck);
        System.out.println(noNodeCheck);


    }
}
