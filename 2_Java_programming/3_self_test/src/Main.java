import java.util.*;

public class Main {

  public static void main(String[] args) {

    /* CRUD operations #1-7 ligger på G-nivå */

    /* ********************************************************************************************************* */

    /** Create Operation #1
     * Skapa en arraylist
     * Fyll den med 3 element av valfri datatyp
     */

    List<Integer> numbers = new ArrayList<>(); // Använd denna lista!

    /* ****** Studerande kod till Create operation #1 ****** */

    numbers.add(420);
    numbers.add(42);
    numbers.add(4200);

    /* ****** Slut på kod ****** */

    hasElements(3, "Create operation #1", numbers); // Verifierar lösning (DO NOT TOUCH THIS LINE)

    /* ********************************************************************************************************* */


    /** Create operation #2
     *  Skapa en lista med namnen Casper, Lisa och David (I den givna ordningen)
     */
    List<String> names  = new ArrayList<>();  // Använd denna lista!

    /* ****** Studerande kod till Create operation #2 ****** */

    names.add("Casper");
    names.add("Lisa");
    names.add("David");

    /* ****** Slut på kod ****** */

    hasStructure("[Casper, Lisa, David]", "Create operation #2", names); // Verifierar lösning (DO NOT TOUCH THIS LINE)

    /* ********************************************************************************************************* */


    /** Read operation #3, 4 & 5
     * Skapa en lista av karaktärer
     * Listan ska innehålla
     *  A på index 0
     *  C på index 2
     *  E på index 4
     */
    List<Character> chars  = new ArrayList<>();  // Använd denna lista!

    /* ****** Studerande kod till Read Operation #3, 4 & 5 ****** */

    chars.addAll(Arrays.asList('A','x','C','x','E'));

    /* ****** Slut på kod ****** */

    verifyIndex('A', 0, "Read operation #3", chars); // Verifierar lösning (DO NOT TOUCH THIS LINE)
    verifyIndex('C', 2, "Read operation #4", chars); // Verifierar lösning (DO NOT TOUCH THIS LINE)
    verifyIndex('E', 4, "Read operation #5", chars); // Verifierar lösning (DO NOT TOUCH THIS LINE)

    /* ********************************************************************************************************* */

    /** Update operation #6
     * Använd listan av märken och updatera värdena så att samtliga märken stavas rätt
     *
     */
    List<String> brands = Arrays.asList("SAABB", "volvo", "Ford", "nisan", "scania");  // Använd denna lista!

    hasStructure("[SAABB, volvo, Ford, nisan, scania]", "Silent operation", brands, true); // Verifierar struktur (DO NOT TOUCH THIS LINE)


    /* ****** Studerande kod till Update Operation #6 ****** */

    for(int i=0; i<brands.size(); ++i) {
      switch(brands.get(i)) {
        case "SAABB" -> brands.set(i, "SAAB");
        case "volvo" -> brands.set(i, "Volvo");
        case "Ford" -> brands.set(i, "Ford");
        case "nisan" -> brands.set(i, "Nissan");
        case "scania" -> brands.set(i, "Scania");
      }
    }

    /* ****** Slut på kod ****** */

    hasStructure("[SAAB, Volvo, Ford, Nissan, Scania]", "Update operation #6", brands); // Verifierar lösning (DO NOT TOUCH THIS LINE)

    /* ********************************************************************************************************* */


    /** Delete operation #7
     * Använd listan av temperaturer och ta bort värdena 18.5 och 13.2
     *
     */
    List<Double> tempatures = Arrays.asList(17.9, 18.5, 19.0, 19.1, 18.5, 13.2);  // Använd denna lista!

    hasStructure("[17.9, 18.5, 19.0, 19.1, 18.5, 13.2]", "Silent operation", tempatures, true); // Verifierar struktur (DO NOT TOUCH THIS LINE)

    /* ****** Studerande kod till Delete Operation #7 ****** */

    

    /* ****** Slut på kod ****** */

    hasStructure("[17.9, 19.0, 19.1, 18.5]", "Delete opeartion #7", tempatures); // Verifierar lösning (DO NOT TOUCH THIS LINE)

    /* ********************************************************************************************************* */

  }


  public static void hasElements(int size, String title, List list) {
    if(list.size() != size) {
      StringBuilder builder = new StringBuilder();
      builder.append("Failed ").append(title);
      builder.append("\nExpected: ").append(size);
      builder.append("\nFound: ").append(list.size());
      
      throw new RuntimeException(builder.toString());
    } else {
      System.out.println("Successful " + title + " operation");
    }
  }

  public static void hasStructure(String structure, String title, List list) {
    hasStructure(structure, title, list, false);
  }

  public static void hasStructure(String structure, String title, List list, boolean silent) {
    if(!list.toString().equals(structure)) {
      StringBuilder builder = new StringBuilder();
      builder.append("Failed ").append(title);
      builder.append("\nExpected: ").append(structure);
      builder.append("\nFound: ").append(list.toString());

      throw new RuntimeException(builder.toString());
    } else if (!silent) {
      System.out.println("Successful " + title + " operation");
    }
  }

  public static void verifyIndex(char character, int position, String title, List<Character> list) {
    if(list.get(position) != character) {
      StringBuilder builder = new StringBuilder();
      builder.append("Failed ").append(title);
      builder.append("\nExpected: ").append(character);
      builder.append("\nFound: ").append(list.get(position))
              .append(" at position ").append(position);

      throw new RuntimeException(builder.toString());
    } else {
      System.out.println("Successful " + title + " operation");
    }
  }

}
