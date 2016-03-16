//SETH, it's a program that figures out solution to the card game 'Sets'.
//Written by Benjamin Clark
//Version 1.0.0
//Version comments: Feature complete, finds solutions (though not very efficiently). Can input a set of a custom number of cards.
//Future additions: Make solutions-finding algorithm more efficient, sort out comments, generally have a tidy-up.
//15/09/2015

package standard;

import java.util.ArrayList;
import java.util.Random;

public class Seth
{
    //Some global variables. The version number, ArrayLists for the cards that are in play and the sets of solutions.
    static String version = "1.0.0";
    private ArrayList<Card> inPlay = new ArrayList<>();
    private ArrayList<Card> solutionSets = new ArrayList<>();
    private GreenyInput input = new GreenyInput();

    //Generates a psuedorandom integer up to the argument limit.
    int randomInt(int limit)
    {
        int r;
        Random randomGen = new Random();
        r = randomGen.nextInt(limit);
        return r;
    }

    //-----------------------------------//
    //PROPERTY COMPARISON METHODS
    //The following four methods compare the values of the a property of three argument cards; returning TRUE if the Sets rule is satisfied, FALSE otherwise.
    boolean compareNumber(Card A, Card B, Card C)
    {
        if ((A.getNum() == B.getNum()) && (B.getNum() == C.getNum()))
        {
            return true;
        }
        if ((A.getNum() != B.getNum()) && (B.getNum() != C.getNum()) && (A.getNum() != C.getNum()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    boolean compareShape(Card A, Card B, Card C)
    {
        if ((A.getShape() == B.getShape()) && (B.getShape() == C.getShape()))
        {
            return true;
        }
        if ((A.getShape() != B.getShape()) && (B.getShape() != C.getShape()) && (A.getShape() != C.getShape()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    boolean compareColour(Card A, Card B, Card C)
    {
        if ((A.getColour() == B.getColour()) && (B.getColour() == C.getColour()))
        {
            return true;
        }
        if ((A.getColour() != B.getColour()) && (B.getColour() != C.getColour()) && (A.getColour() != C.getColour()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    boolean compareShade(Card A, Card B, Card C)
    {
        if ((A.getShade() == B.getShade()) && (B.getShade() == C.getShade())) {
            return true;
        }
        if ((A.getShade() != B.getShade()) && (B.getShade() != C.getShade()) && (A.getShade() != C.getShade()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //-----------------------------------//


    //-----------------------------------//
    //CARD GENERATION AND VIEWING METHODS
    //Generates a card with random attributes determined by a psuedorandom number generator.
    Card randomCard()
    {
        Card card = new Card(randomInt(3) + 1, "", "", "");
        card.shapeGen(randomInt(3));
        card.colourGen(randomInt(3));
        card.shadeGen(randomInt(3));
        //System.out.println("Card Added!");
        return card;
    }

    //Generates a 'blank' card. These are used as 'spacers' for the solution set, keeping things neat and tidy.
    Card blankCard()
    {
        Card card = new Card(0, "", "", "");
        return card;
    }

    //Prints the details of a single card.
    void printCard(Card Card)
    {
        System.out.printf("%d", Card.getNum());
        System.out.print(" | ");
        System.out.print(Card.getShape());
        System.out.print(" | ");
        System.out.print(Card.getColour());
        System.out.print(" | ");
        System.out.print(Card.getShade());
        System.out.println("");
    }

    //Prints the details of all cards currently in play.
    void viewCards()
    {
        int i;
        for (i = 0; i < inPlay.size(); i++)
        {
            printCard(inPlay.get(i));
        }
    }

    //Calculates valid solutions for all cards currently in play.
    void calcSolutions()
    {
        solutionSets.clear();

        //Card iteration variables
        int i;
        int j;
        int k;
        int solutions = 0;
        int size = inPlay.size();

        //Counts number of combinations checked for analytics purposes.
        int count = 0;

        System.out.print("Finding solutions");

        //Solution finding nested loops. (Brute force solution, can be made more efficient. How? Try later.
        for (i = 0; i < size; i++)
        {
            for (j = i+1; j < size; j++)
            {
                for (k = j+1; k < size; k++)
                {
                    Card CardA = inPlay.get(i);
                    Card CardB = inPlay.get(j);
                    Card CardC = inPlay.get(k);
                    if (compareColour(CardA, CardB, CardC) && compareShade(CardA, CardB, CardC) && compareNumber(CardA, CardB, CardC) && compareShape(CardA, CardB, CardC))
                    {
                        int l = solutions*4;
                        solutionSets.add(l, CardA);
                        solutionSets.add(l+1, CardB);
                        solutionSets.add(l+2, CardC);
                        solutionSets.add(l+3, blankCard());
                        solutions++;
                    }
                    count++;
                }
            }
        }
        System.out.printf("\n\nCards in play: %d\n", size);
        System.out.printf("Total combinations checked: %d\n", count);
        System.out.printf("Solutions found: %d\n", solutions);
        if (input.limitIntInput("Show solutions? (1/0)", 0, 1) == 1)
        {
            System.out.println("-------------------------------");

            for (i = 0; i < solutionSets.size(); i++)
            {
                printCard(solutionSets.get(i));
            }
        }
    }

    //Clears all existing cards from the inPlay ArrayList, and adds a new set of 16 randomly generated cards.
    void fullSet()
    {
        int i = 0;
        inPlay.clear();
        for (i=0; i < 16; i++)
        {
            inPlay.add(randomCard());
        }
        System.out.println("Full set of 16 cards in play!");
    }
    //-----------------------------------//

    //Prints the main menu.
    public void printMenu()
    {
        System.out.printf("\nCards in play: %d\n\n", inPlay.size());
        System.out.println("1. Add random cards");
        System.out.println("2. Fresh set of 16 random cards");
        System.out.println("3. View cards in play");
        System.out.println("4. Calculate solutions");
        System.out.println("5. Exit");
    }

    //'Root' method, the user can select options from the menu here.
    void seth()
    {
        int i;
            printMenu();
            i = (input.limitIntInput(" ", 1, 5));
            while((i <= 4) && (i >= 1))
            {
                if (i == 1)
                {
                    int n = input.limitIntInput("How many cards?", 1, 1000);
                    int k = 0;
                    for (k = 0; k < n; k++)
                    {
                        inPlay.add(randomCard());
                    }
                    printMenu();
                    i = (input.limitIntInput(" ", 1, 5));
                }

                if (i == 2)
                {
                    fullSet();
                    printMenu();
                    i = (input.limitIntInput(" ", 1, 5));
                }

                if (i == 3)
                {
                    viewCards();
                    printMenu();
                    i = (input.limitIntInput(" ", 1, 5));
                }

                if (i == 4)
                {
                    calcSolutions();
                    printMenu();
                    i = (input.limitIntInput(" ", 1, 5));
                }
            }
            if (i == 5)
            {
                System.exit(0);
            }
        }



    //Runnable main method. Where it all starts.
    public static void main(String[] args)
    {
        System.out.print("Welcome to SETH version ");
        System.out.println(version);
        new Seth().seth();
    }
}