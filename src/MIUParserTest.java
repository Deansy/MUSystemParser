import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MIUParserTest {

    @org.junit.Test
    public void testNextStates() throws Exception {
        MIUParser parser = new MIUParser();

        List<String> miList = new ArrayList<String>();
        miList.add("MIU");
        miList.add("MII");
        assertEquals(miList, parser.nextStates("MI"));


        List<String> miuList = new ArrayList<String>();
        miuList.add("MIUIU");
        assertEquals(miuList, parser.nextStates("MIU"));


        List<String> muiList = new ArrayList<String>();
        muiList.add("MUIU");
        muiList.add("MUIUI");
        assertEquals(muiList, parser.nextStates("MUI"));


        List<String> miiiiList = new ArrayList<String>();
        miiiiList.add("MIIIIU");
        miiiiList.add("MIIIIIIII");
        miiiiList.add("MUI");
        miiiiList.add("MIU");
        assertEquals(miiiiList, parser.nextStates("MIIII"));


        List<String> muuiiList = new ArrayList<String>();
        muuiiList.add("MUUIIU");
        muuiiList.add("MUUIIUUII");
        muuiiList.add("MII");
        assertEquals(muuiiList, parser.nextStates("MUUII"));

        List<String> muuuiList = new ArrayList<String>();
        muuuiList.add("MUUUIU");
        muuuiList.add("MUUUIUUUI");
        muuuiList.add("MUI");
        assertEquals(muuuiList, parser.nextStates("MUUUI"));

        List<String> muuiuuList = new ArrayList<String>();
        muuiuuList.add("MUUIUUUUIUU");
        muuiuuList.add("MIUU");
        muuiuuList.add("MUUI");
        assertEquals(muuiuuList, parser.nextStates("MUUIUU"));


    }
}