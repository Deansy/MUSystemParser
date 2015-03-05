import java.util.*;

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

    public List<List<String>> extendPath(List<String> inputStrings) {
        // Get the next states for the last element of the input
        List<String> nextStates = nextStates(inputStrings.get(inputStrings.size() - 1));


        // Create an arraylist to hold the paths that will be returned
        List<List<String>> paths = new ArrayList<List<String>>();

        for (String s: nextStates) {
            List<String> tempList = new ArrayList<String>();

            // Add the existing elements of the paths
            for (String x : inputStrings) {
                tempList.add(x);
            }

            // Add the new element of the path
            tempList.add(s);

            // Add this path to the paths array
            paths.add(tempList);
        }


        return paths;
    }

    public List<String> breadthFirstSearch(String goalString) {
        Queue<List<String>> agenda = new LinkedList<List<String>>();

        // Create the initial path
        List<String> initialPath = new ArrayList<String>();
        initialPath.add("MI");

        // Add the initial path to the agenda
        agenda.add(initialPath);



        int extendPathCount = 0;
        int extendPathLimit = 30;


        if (extendPathCount > extendPathLimit) {

            // Take the first path from the agenda
            List<String> currentPath = agenda.poll();

            // Does its last element == goal string
            if (currentPath.get(currentPath.size() - 1).equals(goalString)) {
                //  if so return stuff
                System.out.println("Length of path: " + currentPath.size());
                System.out.println("Extend path called: " + extendPathCount);
                System.out.println("Agenda size: " + agenda.size());

                return currentPath;
            } else {
                // Apply extend path
                List<List<String>> x = extendPath(currentPath);
                extendPathCount++;
                //  add new paths to end of agenda
                for (List<String> path : x) {
                    agenda.add(path);
                }

            }
        } else {
            return new ArrayList<String>();
        }

        // Should never reach here
        return null;
    }


    public List<String> iterativeDeepening(String goalString) {
        List<String> x = depthLimitedDFS(goalString, 2);




        return x;
    }

    public List<String> depthLimitedDFS(String goalString, int limit) {
        Stack<List<String>> agenda = new Stack<List<String>>();

        // Create the initial path
        List<String> initialPath = new ArrayList<String>();
        initialPath.add("MI");

        // Add the initial path to the agenda
        agenda.add(initialPath);


        int extendPathCount = 0;


        // Take the first path from the agenda
        List<String> currentPath = agenda.pop();

        while (currentPath.size() <= limit) {


            // Does its last element == goal string
            if (currentPath.contains(goalString)) {
                //  if so return stuff
                System.out.println("Length of path: " + currentPath.size());
                System.out.println("Extend path called: " + extendPathCount);
                System.out.println("Agenda size: " + agenda.size());

                return currentPath;
            } else {
                // Apply extend path
                List<List<String>> x = extendPath(currentPath);

                //  add new paths to end of agenda
                for (List<String> path : x) {
                    agenda.push(path);
                }

                extendPathCount++;
                currentPath = agenda.pop();

            }
        }
            return new ArrayList<String>();

    }

    // I -> IU
    private List<String> applyRuleOne(String string) {
        if (string.endsWith("I")) {
            // Append U to the end of the string
            string = string.substring(0, string.length() - 1);
            string = string + "IU";
        }

        List<String> list = new ArrayList<String>();
        list.add(string);
        return list;
    }

    // Mx -> Mxx
    private List<String> applyRuleTwo(String string) {
        string = string + string.substring(1);

        List<String> list = new ArrayList<String>();
        list.add(string);
        return list;
    }

    // xIIIy -> xUy
    private List<String> applyRuleThree(String string) {
        List<String> list = new ArrayList<String>();

        // Loop through all occurrences of the sub string
        for (int i = 1; i < subStringCount(string, "III") + 1; i++ ) {
            // index is the location of the first character of the substring within the string
            // i represents the ith occurrence of the sub string
            int index = string.indexOf("III", i);

            if (index > 0) {
                // Take all of the string before the occurrence
                String newString = string.substring(0, index);
                // Add a U
                newString = newString.concat("U");
                // and append all of the string after the occurence
                // index + 3 is after the occurrence due to the sub string being 3 characters long
                newString = newString.concat(string.substring(index + 3));

                list.add(newString);
            }
        }


        return list;
    }

    // xUUy -> xy
    private List<String> applyRuleFour(String string) {
        List<String> list = new ArrayList<String>();

        // Loop through all occurrences of the sub string
        for (int i = 1; i < subStringCount(string, "UU") + 1; i++) {
            // index is the location of the first character of the substring within the string
            // i represents the ith occurrence of the sub string
            int index = string.indexOf("UU", i);

            if (index > 0) {
                // Take all of the string before the occurrence
                String newString = string.substring(0, index);
                // and append all of the string after the occurrence
                // index + 2 is after the occurrence due to the sub string being 2 characters long
                newString = newString.concat(string.substring(index + 2));


                list.add(newString);
            }
        }



        return list;

    }

    // Counts the number of occurrences of a sub string within a string
    private int subStringCount(String string, String substring)
    {
        int count = 0;
        int idx = 0;

        while ((idx = string.indexOf(substring, idx)) != -1)
        {
            idx++;
            count++;
        }

        return count;
    }


}
