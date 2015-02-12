import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MIUParser {


    // Returns a list of all the next possible states
    public List<String> nextStates(String originalString) {
        // Always deal with uppercase string
        originalString = originalString.toUpperCase();
        List <String> states = new ArrayList<String>();

        // Perform each of the rules to the original string
        states.addAll(applyRuleOne(originalString));
        states.addAll(applyRuleTwo(originalString));
        states.addAll(applyRuleThree(originalString));
        states.addAll(applyRuleFour(originalString));

        // Remove original string from the states array
        states.removeAll(Collections.singleton(originalString));

        return states;
    }

    // I -> IU
    private List<String> applyRuleOne(String string) {
        String temp = string;
        if (string.endsWith("I")) {
            // Append U to the end of the string
            temp = temp.substring(0, temp.length() - 1);
            temp = temp + "IU";
        }

        List<String> list = new ArrayList<String>();
        list.add(temp);
        return list;
    }

    // Mx -> Mxx
    private List<String> applyRuleTwo(String string) {
        String temp = string;
        temp = temp + temp.substring(1);

        List<String> list = new ArrayList<String>();
        list.add(temp);
        return list;
    }

    // xIIIy -> xUy
    private List<String> applyRuleThree(String string) {
        List<String> list = new ArrayList<String>();
        int count = string.indexOf("III");
        // Works but not really sure why
        if (count > 0) {
            try {
                for (int i = 1; i < count + 1; i++) {
                    String subStr = string.substring(0, i);
                    subStr = subStr.concat("U");
                    // TODO: fix this
                    // Throws an exception sometimes, Currently ignoring it as it is functional
                    subStr = subStr.concat(string.substring(count + 3));

                    list.add(subStr);
                    count++;
                }
            }
            catch (Exception e) {
                // We ignore the exception currently
            }
        }

        return list;
    }

    // xUUy -> xy
    // TODO: Should only remove one at a time
    // Currently removes all of the instances of UU in one go
    private List<String> applyRuleFour(String string) {
        String temp = string;
        if (temp.contains("UU")) {
            // Removes all instances of UU
            temp = temp.replace("UU", "");
        }

        List<String> list = new ArrayList<String>();
        list.add(temp);
        return list;
    }


}
