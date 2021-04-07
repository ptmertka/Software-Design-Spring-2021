import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class BaseConverter is used to handle the actual math and input validation for the Base Conversion
 * Made into its own class for code neatness and usability later. Converts any number from base 2 to 32
 * to any base 2 to 32
 */
public class BaseConverter {
    /**
     * String: used to store the base of the input number, in string form for use with hashmap
     */
    private String inputBase;

    /**
     * Int: the value of the base of the outputBase, used in conversion
     */
    private int outputBase;

    /**
     * String: the number being converted, a string to handle characters past base 10
     */
    private String number;

    /**
     * HashMap: Used to store the Regexs for each base to help with input validation.
     */
    private HashMap<String, String> baseRegexs = new HashMap<String, String>();

    /**
     * HashMap: Used to store letter digits for each base to help with conversion
     */
    private HashMap<String, String> baseLetters = new HashMap<String, String>();

    /**
     * Constructor, creates the new Base Converter object with the input and output bases
     * along with the number to convert
     * @param input String: the base of the input number
     * @param output int: The base of the output number
     * @param numberToChange String: The number being converted
     */
    public BaseConverter(String input, int output, String numberToChange){
        inputBase = input; //sets parameters to the instance variables of the class
        outputBase = output;
        number = numberToChange;

        //next for arrays are used to make the HashMaps used for input Validation and translation

        String[] bases = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};

        String[] regexes = {"^[0-1]+$", "^[0-2]+$", "^[0-3]+$", "^[0-4]+$", "^[0-5]+$", "^[0-6]+$", "^[0-7]+$",
        "^[0-8]+$", "^[0-9]+$", "^[0-9A-Aa-a]+$", "^[0-9A-Ba-b]+$", "^[0-9A-Ca-c]+$", "^[0-9A-Da-d]+$",
                "^[0-9A-Ea-e]+$","^[0-9A-Fa-f]+$","^[0-9A-Ga-g]+$", "^[0-9A-Ha-h]+$", "^[0-9A-Ia-i]+$", "^[0-9A-Ja-j]+$",
                "^[0-9A-Ka-k]+$", "^[0-9A-La-l]+$", "^[0-9A-Ma-m]+$","^[0-9A-Na-n]+$", "^[0-9A-Oa-o]+$","^[0-9A-Pa-p]+$",
                "^[0-9A-Qa-q]+$", "^[0-9A-Ra-r]+$", "^[0-9A-Sa-s]+$", "^[0-9A-Ta-t]+$", "^[0-9A-Ua-u]+$", "^[0-9A-Va-v]+$"
                };

        String [] uppercase = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V"};
        String [] lowercase = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l","m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        String[] digitsPast9 = {"10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};

        for(int i = 0 ; i< bases.length; i++){ //creates both hashmaps for the regexes of each base, as well as the way to convert from digits to letters
            baseRegexs.put(bases[i], regexes[i]);

        }
        for(int j = 0; j < uppercase.length ; j++){
            baseLetters.put(digitsPast9[j], uppercase[j]);
            baseLetters.put(uppercase[j], digitsPast9[j]);
            baseLetters.put(lowercase[j], digitsPast9[j]);
        }
    }

    /**
     * Function that takes the input base and the number being converted, and makes sure it is
     * in the proper form, returns true if it is, false if not
     * @return Boolean: whether or not the inputted number is valid
     */
    public boolean checkIfValid(){

       return number.matches(baseRegexs.get(inputBase)); //compares the number to the corresponding regex for each base
        //returns true if the number only contains the valid characters for that base
    }

    /**
     * Translates the number, assuming its valid, from the inputted base to base 10
     * and then from base 10 to the desired base
     * @return String: the new base representation of the inputted number to the object
     */
    public String translate(){
        int numInBase10 = 0;
        int inputBaseAsInt = Integer.valueOf(inputBase);
        String numberReversed = new StringBuilder(number).reverse().toString(); //revereses the string so that the x^0 place is at the front, to help with calculations
        char[] numberByDigits = numberReversed.toCharArray(); //breaks the number into a character by character representation



        for(int i = 0; i< numberByDigits.length ; i++) { //converts the number from its input base to base 10
            if (Character.isAlphabetic(numberByDigits[i])) {
                numInBase10 = (int) (numInBase10 + (Math.pow(inputBaseAsInt, i) * Integer.valueOf(baseLetters.get(Character.toString(numberByDigits[i]))))); //handles the conversion to base 10 if a letter digit is encountered
            } else {
                numInBase10 = (int) (numInBase10 + (Math.pow(inputBaseAsInt, i) * Character.getNumericValue(numberByDigits[i]))); //handles the conversion if a numeric digit is enountered

            }
        }

        ArrayList<Integer> digits = new ArrayList<Integer>(); //creates an arrayList to store the new digits of the number in the output base

        while (numInBase10 > 0){ //while the numberInBase10 is non zero
            digits.add(numInBase10 % outputBase); //follows the algorithm of the number's remainder by dividing the new base is the first
            numInBase10 = Math.floorDiv(numInBase10, outputBase);
        }
        ArrayList<String> digitsInNewBase = new ArrayList<String>(); //converts any digits greater than 9 to their letter output
        for(int digit : digits){
            if(digit > 9){ //once translated, adds the new string digits to a new array List
                digitsInNewBase.add(baseLetters.get(Integer.toString(digit)));
            }
            else{
                digitsInNewBase.add(Integer.toString(digit));
            }
        }

        Collections.reverse(digitsInNewBase); //reverses list becuase when doing the initial add, adds digits right to left

        String finalOutput = "";

        for (String digit: digitsInNewBase){ //turns the array list into an output string
            finalOutput = finalOutput + digit;
        }
        return finalOutput; //returns the new string
    }

}
