import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        MIUParser miu = new MIUParser();


        List<String> nodeCheck = miu.depthLimitedDFS("MIIIUII", 15, false);
        System.out.println(nodeCheck);

    }
}
