import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



//An arrayList was used in this assignment instead of an array
//so the list has flexibility to grow and shrink in size

//A method to produce an arrayList of random numbers was used
//The random list helps to prove each tasks without hardcoding each arrayList
//which is more time efficient and requires less code when at scale
//original numbers from arrays were used as proof of concept

public class Controller {
    public static void main(String[] args) {

        ArrayList<Integer> dominantList = new ArrayList<>(Arrays.asList(16,17,4,3,5,2));
        System.out.println(dominantNumberOrganizer(dominantList));
        
        //dominantListOrganizer using aRandomArrList for scalability, code efficiency, usability
        System.out.println(dominantNumberOrganizer(aRandomArrList(6,2,17)));

        ArrayList<Integer> positiveNegativeArrList = new ArrayList<>(Arrays.asList(1,-1,3,2,-7,-5,11,6));
        System.out.println(positiveNegativeOrganizer(positiveNegativeArrList));

        //positiveNegativeOrganizer using aRandomArrList for scalability, code efficiency, usability
        System.out.println(positiveNegativeOrganizer(aRandomArrList(8,-7,11)));

        System.out.println("The word saba appears " + sabaFinder() + " times");

    }//end main
    //A method that produces a random arrayList by size, max, min
    public static ArrayList<Integer> aRandomArrList(int size, int min, int max){

        ArrayList<Integer> randomArrList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++){
            int randomArrListElement = random.nextInt(max - min + 1) + min;
            randomArrList.add(randomArrListElement);
        }//end for loop

        return randomArrList;
    }// end random arrList method
    public static ArrayList<Integer> dominantNumberOrganizer(ArrayList<Integer> arrList){

        ArrayList<Integer> resultList = new ArrayList<>();
        //Initializes maxElement as the rightmost element
        int maxElement = arrList.get(arrList.size() - 1);

        //adds rightmost element to resultList since it's always dominant
        resultList.add(maxElement);

        //Iteration from right to left through arrayList
        for(int i = arrList.size() - 2; i >= 0; i--){
            //checks to see if current element is greater than maxElement
            if (arrList.get(i) > maxElement){
                //update max element with current element
                maxElement = arrList.get(i);
                //add dominant element to beginning of resultList
                resultList.add(0, maxElement);
            }
        }
        return resultList;
    }//end of method
    public static ArrayList<Integer> positiveNegativeOrganizer(ArrayList<Integer> arrList){

        ArrayList<Integer> resultArrList = new ArrayList<>();
        int left = 0;
        int right = arrList.size() - 1;

        for (int i = 0; i < arrList.size(); i++) {
            int element = arrList.get(i);
            if (element < 0) {
                resultArrList.add(left, element);
                left++;
            } else {
                resultArrList.add(element);
            }
        }
        return resultArrList;
    }//end for loop

    public static int sabaFinder(){
        //note: a try catch block was not used for scanner variables.
        //InputMismatchException will occur or StringIndexOutOfBounds
        Scanner sc = new Scanner(System.in);
        int sabaCount = 0;

        System.out.print("Enter n value: ");
        int nValue = sc.nextInt();

        System.out.print("Enter m value: ");
        int mValue = sc.nextInt();

        System.out.println("Enter " + nValue +  " lines of " + mValue + " characters: ");
        String[][] grid = new String[nValue][mValue];

        for (int n = 0; n < nValue; n++){
            //creates a scnnaer line for amount of nValues
            String linesofNValue = sc.next();
            for(int m = 0; m < mValue; m++){
                //adds n & m values to grid
                grid[n][m] = String.valueOf(linesofNValue.charAt(m));
            }
        }

        //All nested for loops with if statements below go through the grid and the if statements are used
        // to find the sequential order of saba. The number variation(EX: nValue - 3 || grid[n+1]) ensures the
        //loop stays within a valid range and does not cause an outOfBounds error

        //checks for saba vertically top to bottom
        for (int n = 0; n < nValue - 3; n++){
            for (int m = 0; m < mValue ; m++){
                if (grid[n][m].equalsIgnoreCase("s") &&
                        grid[n + 1][m].equalsIgnoreCase("a") &&
                        grid[n + 2][m].equalsIgnoreCase("b") &&
                        grid[n + 3][m].equalsIgnoreCase("a")){
                    sabaCount++;
                }//end if
            }//end nested for
        }//end
        //checks for saba vertically top to bottom
        for (int n = nValue - 1; n >= 3; n--){
            for (int m = 0; m < mValue ; m++){
                if (grid[n][m].equalsIgnoreCase("s") &&
                        grid[n - 1][m].equalsIgnoreCase("a") &&
                        grid[n - 2][m].equalsIgnoreCase("b") &&
                        grid[n - 3][m].equalsIgnoreCase("a")){
                    sabaCount++;
                }//end if
            }//end nested for
        }//end

        //checks for saba count horizontally
        for (int n = 0; n < nValue; n++){
            for (int m = 0; m < mValue - 3 ; m++){
                if (grid[n][m].equalsIgnoreCase("s") &&
                        grid[n][m + 1].equalsIgnoreCase("a") &&
                        grid[n][m + 2].equalsIgnoreCase("b") &&
                        grid[n][m + 3].equalsIgnoreCase("a")){
                    sabaCount++;
                }//end if
            }//end nested for
        }//end for

        //checks for saba count down diagonally
        for (int n = 0; n < nValue - 3; n++){
            for (int m = 0; m < mValue - 3 ; m++){
                if (grid[n][m].equalsIgnoreCase("s") &&
                        grid[n + 1][m + 1].equalsIgnoreCase("a") &&
                        grid[n + 2][m + 2].equalsIgnoreCase("b") &&
                        grid[n + 3][m + 3].equalsIgnoreCase("a")){
                    sabaCount++;
                }//end if
            }//end nested for
        }//end for

        //checks for saba count up diagonally
        for (int n = 3; n < nValue ; n++){
            for (int m = 0; m < mValue - 3; m++){
                if (grid[n][m].equalsIgnoreCase("s") &&
                        grid[n - 1][m + 1].equalsIgnoreCase("a") &&
                        grid[n - 2][m + 2].equalsIgnoreCase("b") &&
                        grid[n - 3][m + 3].equalsIgnoreCase("a")){
                    sabaCount++;
                }//end if
            }//end nested for
        }//end for

        return sabaCount;
    }//end method

}//end class
