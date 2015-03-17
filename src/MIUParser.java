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

        // Remove duplicates
        Set<String> hs = new LinkedHashSet<String>(states);
        states.clear();
        states.addAll(hs);

        return states;
    }

    public List<List<String>> extendPath(List<String> inputStrings) {
        // Get the next states for the last element of the input
        List<String> nextStates = nextStates(inputStrings.get(inputStrings.size() - 1));


        // Create an array list to hold the paths that will be returned
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

    public List<String> breadthFirstSearch(String goalString, boolean pathCheck) {
        // Creates the agenda
        Queue<List<String>> agenda = new LinkedList<List<String>>();

        // Create the initial path
        List<String> initialPath = new ArrayList<String>();
        initialPath.add("MI");

        // Add the initial path to the agenda
        agenda.add(initialPath);


        // For keeping track of how many times extend path is being called
        int extendPathCount = 0;

        // This is to prevent infinite searching if the goal cannot be found
        int maxDepth = 20;


        // Loop until a reason is found to stop
        // eg: Finding the goal, Reaching the max depth
        while (true) {


            // Take the first path from the agenda
            List<String> currentPath = agenda.poll();

            //System.out.println("Current depth:" + currentPath.size());
            //System.out.println("Agenda size: " + agenda.size());
            // Does its last element == goal string
            if (currentPath.contains(goalString)) {
                //  if so return stuff
                System.out.println("Length of path: " + currentPath.size());
                System.out.println("Extend path called: " + extendPathCount);
                System.out.println("Agenda size: " + agenda.size());

                return currentPath;
            } else {

                if (pathCheck) {

                    // Get the last node of currentPath
                    String lastNode = currentPath.get(currentPath.size() - 1);

                    // A list which will contain all of currentPath except the last node
                    List<String> tempList = currentPath.subList(0, currentPath.size() - 1);

                    // If something equal to lastNode is still in the path then it has already been expanded
                    if (tempList.contains(lastNode)) {
                        System.out.println("Don't expand");

                    } else {
                        // Expand the path
                        List<List<String>> x = extendPath(currentPath);
                        extendPathCount++;


                        if (x.get(0).size() >= maxDepth) {
                            // Stop the search when the maximum depth is reached
                            System.out.println("Reached max depth");
                            return new ArrayList<String>();
                        } else {

                            //  add new paths to end of agenda
                            for (List<String> path : x) {
                                agenda.add(path);
                            }
                        }
                    }
                }
                else {
                    // No path checking is being done


                    List<List<String>> x = extendPath(currentPath);
                    extendPathCount++;


                    if (x.get(0).size() >= maxDepth) {
                        // Stop the search when the maximum depth is reached
                        System.out.println("Reached max depth");
                        return new ArrayList<String>();
                    } else {

                        //  add new paths to end of agenda
                        for (List<String> path : x) {
                            agenda.add(path);
                        }
                    }
                }
            }
        }
    }


    public List<String> depthLimitedDFS(String goalString, int limit, boolean pathCheck) {
        // Create the agenda
        Stack<List<String>> agenda = new Stack<List<String>>();

        // Create the initial path
        List<String> initialPath = new ArrayList<String>();
        initialPath.add("MI");

        // Add the initial path to the agenda
        agenda.add(initialPath);

        int extendPathCount = 0;


        while (!agenda.isEmpty()) {
            List<String> currentPath = agenda.pop();

            // Does the path contain the goal?
            if (currentPath.contains(goalString)) {
                //  if so return stuff
                System.out.println("Length of path: " + currentPath.size());
                System.out.println("Agenda size: " + agenda.size());
                System.out.println("Extend path called: " + extendPathCount);

                return currentPath;
            } else {
                // We don't want to go deeper than limit
                if (currentPath.size() < limit) {


                    if (pathCheck) {

                        // Get the last node of currentPath
                        String lastNode = currentPath.get(currentPath.size() - 1);

                        // A list which will contain all of currentPath except the last node
                        List<String> tempList = currentPath.subList(0, currentPath.size() - 1);

                        // If something equal to lastNode is still in the path then it has already been expanded
                        if (tempList.contains(lastNode)) {
                            System.out.println("Don't expand");
                        }
                        else {
                            // Apply extend path
                            List<List<String>> x = extendPath(currentPath);
                            extendPathCount++;

                            //  add new paths to end of agenda
                            for (List<String> path : x) {

                                agenda.push(path);
                            }
                        }


                    }
                    else {
                        // Apply extend path
                        List<List<String>> x = extendPath(currentPath);
                        extendPathCount++;

                        //  add new paths to end of agenda
                        for (List<String> path : x) {

                            agenda.push(path);
                        }
                    }

                }
            }
        }


        //System.out.println("Goal String was not found at this depth");
        return new ArrayList<String>();

    }

    public List<String> iterativeDeepening(String goalString, boolean pathCheck) {
        // Initial depth to search is 2
        int depth = 2;
        boolean found = false;


        // Do a search with the initial depth
        List<String> x = depthLimitedDFS(goalString, depth, pathCheck);

        // Loop till the goal is found
        while (!found) {


            // We didn't find it
            if (x.equals(new ArrayList<String>()))   {
                // Increment the depth and search again
                depth++;
                x = depthLimitedDFS(goalString, depth, pathCheck);

            }
            else {
                found = true;
            }
        }

        if (found) {
            // If it was found then print the depth it was found at
            System.out.println("Depth found at:" + depth);
        }


        //  Return the path to the goal
        return x;
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
