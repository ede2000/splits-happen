
// -------------------------------------------------------------------------
/**
 *  This program calculates a bowling game score from an input String
 *  containing the various roll results.
 *
 *  @author Ed Ekpoudom
 *  @version Apr 17, 2016
 */

public class BowlingLine
{
    private String inputString;
    private int frame;
    private int turn;
    private int score;

    // ----------------------------------
    /**
     * Create a new BowlingLine object.
     * @param input
     */
    public BowlingLine(String input)
    {
        inputString = input;
        frame = 0;
        turn = 0;
        score = 0;
    }

    // ----------------------------------------------------------
    /**
     * @return the score
     */
    public int getScore()
    {
        return score;
    }
    // ----------------------------------------------------------
    /**
     * Calculate Bowling Game score
     */
    public void calculateScore()
    {

        while (turn < inputString.length() && frame < 10)
        {

            while (frame < 10)
            {
                //Array bounds checks
                if(turn+1 < inputString.length() && turn+2
                    <= inputString.length())
                {
                    char roll1 = inputString.charAt(turn);
                    char roll2 = inputString.charAt(turn+1);

                    //Account for a strike
                    if (roll1 == 'X')
                    {
                        char roll3 = inputString.charAt(turn+2);
                        if (roll3 == '/')
                        {
                            score = score + charConvertor(roll1)+
                                charConvertor(roll2) +
                                charConvertor(roll3,roll2);
                        }
                        else
                            score = score + charConvertor(roll1)+
                            charConvertor(roll2)+
                            charConvertor(roll3);
                            frame = frame+1;
                            turn = turn+1;
                            break;
                    }

                    //Account for a spare
                    else if(roll2=='/' && roll1 != 'X')
                    {
                        char roll3 = inputString.charAt(turn+2);
                        score = score + (charConvertor(roll2) -
                            charConvertor(roll1))+ charConvertor(roll1)+
                            charConvertor(roll3);
                        frame = frame + 1;
                        turn = turn + 2;
                        break;
                    }

                    //Account for neither a strike or a spare
                    else if ((roll1 != '/' && roll2 != '/') &&
                        (roll1 != 'X' && roll2 != '/') &&
                        (roll1 != '/' && roll2 != 'X')&&
                        (roll1 != 'X' && roll2 != 'X'))
                    {
                        score =score + (charConvertor(roll1) +
                            charConvertor(roll2));
                        frame = frame + 1;
                        turn = turn + 2;
                        break;
                    }
                }
            }
        }
    }

    /**
     * @param input
     * @return the decimal value of current input string char
     */
    public static int charConvertor(char input)
    {
        int value = 0;
        if (input == 'X')
            value = 10;
        else if (input == '-')
            value = 0;
        else if (input == '/')
            value = 10;         //used as something of placeholder
        else
            value = Character.getNumericValue(input);
        return value;
    }

    /**
     * Overloaded function that handles the spare char '/'
     * @param input1
     * @param input2
     * @return The appropriate decimal value given the immediately
     * preceding char before '/'
     */
    public static int charConvertor(char input1, char input2)
    {
       int value = 0;

        //Just an extra check
        if (input1 == '/')
            value = 10 - charConvertor(input2);
        return value;
    }

    // ----------------------------------------------------------
    /**
     * The driver method the produces the output.
     * @param args
     */
    public static void main(String[] args)
    {
        String testInput1 = "XXXXXXXXXXXX";
        String testInput2 = "9-9-9-9-9-9-9-9-9-9-";
        String testInput3 = "5/5/5/5/5/5/5/5/5/5/5";
        String testInput4 = "X7/9-X-88/-6XXX81";
        BowlingLine game1 = new BowlingLine(testInput1);
        BowlingLine game2 = new BowlingLine(testInput2);
        BowlingLine game3 = new BowlingLine(testInput3);
        BowlingLine game4 = new BowlingLine(testInput4);
        System.out.println("Below is the output for the given test cases:"+
        "\n");
        game1.calculateScore();
        System.out.println(game1.getScore());
        game2.calculateScore();
        System.out.println(game2.getScore());
        game3.calculateScore();
        System.out.println(game3.getScore());
        game4.calculateScore();
        System.out.println(game4.getScore());
    }
}


