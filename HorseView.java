import java.util.ArrayList;
import java.util.stream.IntStream;
import java.time.LocalTime;

public class HorseView {

  public void printHorseDetails ( String horseName, String warCry,
                                Boolean isHealthy ) {
      System.out.println( "Horse Name: " + horseName );
      System.out.println( "Horse WarCry: " + warCry );
      System.out.println( "Horse is Healthy: " + isHealthy );
  }

  public void printRank ( ArrayList<Horse> ranking ) {
        IntStream.rangeClosed( 1, ranking.size() )
        .forEach( increment -> rankMessage ( ranking, increment ) );
  }

  public void displayDistanceFromStartingLine ( Horse horse,
              final int START_LINE ) {
    System.out.println( horse.getHorseName().get()
                      + " is " + (START_LINE-horse.getDistanceToBarn())
                      + "m from the Starting Line" );
  }

  public void displayProgressOfHorses ( Horse horse ) {

    System.out.println( horse.getHorseName().get() + " "
    + "         " + horse.getSpeed() + "m "
    + "         " + horse.getDistanceTravelled() + "m "
    + "         " + horse.getRemainingDistance() + "m");

    System.out.println("----------------------------------"
                      +"----------------------------------");
  }

  public void displayStartRace() {
    System.out.println("====================================");
    System.out.println("\n3!\n2!\n1!\n\nRACE START!!!\n\n");

    System.out.println("Horse Name     "
                      + "Steps      "
                      + "Travelled   "
                      + "Remaining Distance ");
  }

  public void displayHorseFinished ( Horse horse ) {
    System.out.println("============================================\n"
                      + horse.getHorseName().get() + " HAS FINISHED THE RACE!"
                      + horse.getWarCry().get() + "!!! \n"
                      + "============================================");
  }

  public void rankMessage ( ArrayList<Horse> ranking, int increment){
    System.out.println("Rank " + increment + ": "
                      + ranking.get((increment-1)).getHorseName().get() + " | "
                      + ranking.get((increment-1)).getWarCry().get() + "!!! ");
  }

}
