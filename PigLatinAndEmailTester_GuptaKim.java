// Aditya Gupta and Jay Kim (absent)
// TypesA4: String Prog (PP)

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class PigLatinAndEmailTester_GuptaKim {
   public static void main(String[] args) {
      PigLatinTranslator pigLatinTranslator = new PigLatinTranslator();
      AddressGenerator addressGenerator = new AddressGenerator();
      
      System.out.println("-----------------------------------------------");
      System.out.println("English to Pig Latin translator");
      System.out.println("-----------------------------------------------");
      System.out.println();
      
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter English text to translate to Pig Latin: ");
      String englishText = scanner.nextLine();
      System.out.println("Translated: " 
         + pigLatinTranslator.translate(englishText));
      System.out.println();
         
      System.out.println("-----------------------------------------------");
      System.out.println("@bcp.org email generator");
      System.out.println("-----------------------------------------------");
      System.out.println();
         
      System.out.print("Enter a name to convert into an email address: ");
      String[] name = scanner.nextLine().split(" ");
      System.out.println("Email address: " 
         + addressGenerator.generate(name[0], name[1]));
   }
}

/**
*
* Translates English sentences into Pig Latin
* Doesn't work with punctuation (yet)
*
*/
class PigLatinTranslator {
   private static List<Character> vowels = Arrays.asList(new Character[]{'a', 
      'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'});

   public String translate(String englishText) {
      String[] words = englishText.split(" ");
      String pigLatinText = "";
      
      for (String word : words) {
         pigLatinText += translateWord(word) + " ";
      }
      
      return pigLatinText;
   }
   
   private String translateWord(String word) {
      if (vowels.contains(word.charAt(0))) {
         return word + "way";
      }
      
      String endString = "";
      
      for (char letter : word.toCharArray()) {
         if (!vowels.contains(letter)) {
            endString += letter;
            word = word.substring(1);
         } else {
            break;
         }
      }
      
      return word + endString + "ay";
   }
}

/**
*
* Creates an @bcp.org email from a first and last name
*
*/
class AddressGenerator
{
   public String generate(String firstName, String lastName)
   {
      String email = "";
      
      if (firstName.length() != 0) {
         email += firstName.charAt(0);
      }
      
      email += lastName;
      
      return email.toLowerCase() + "@bcp.org";
   }
}