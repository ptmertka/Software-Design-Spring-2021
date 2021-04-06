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
        String [] lowercase = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l","m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v"};

        String [] uppercase = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V"};

        String[] digitsPast9 = {"10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};

    }
}
