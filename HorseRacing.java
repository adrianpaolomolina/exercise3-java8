import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;
import java.util.Optional;

public class HorseRacing {

  Scanner scan = new Scanner(System.in);
  Random health = new Random();
  HorseView hView = new HorseView();
  Horse horse;
  int raceDistance = 0;
  Boolean isStarted = false;
  ArrayList<Horse> ranking = new ArrayList<Horse>();
  ArrayList<Horse> listOfHorses = new ArrayList<Horse>();
  final int MIN_DISTANCE = 250;
  final int START_LINE = 10;

  public void horseRun(){
    addHorse();
    getParticipants();
    inputRaceDistance();
    setRaceDistanceForHorses();
    travelToBarn();
    startRace();
  }

  public Optional<String> getHorseName() {
    return horse.getHorseName();
  }

  public void setHorseName ( Optional<String> horseName ) {
    horse.setHorseName(horseName);
  }

  public Optional<String> getWarCry() {
    return horse.getWarCry();
  }

  public void setWarCry ( Optional<String> warCry ) {
    horse.setWarCry(warCry);
  }

  public Boolean getIsHealthy() {
    return horse.getIsHealthy();
  }

  public void setIsHealthy ( Boolean isHealthy ) {
    horse.setIsHealthy(isHealthy);
  }

  public void setSpeed ( int randomNumber ) {
    horse.setSpeed(randomNumber);
  }

  public int randomNumber() {
    Random randomSpeed = new Random();
    int upperBound = 9;
    int lowerBound = 1;
    return randomSpeed.nextInt(upperBound) + lowerBound;
  }

  public int getSpeed() {
    return horse.getSpeed();
  }

  public void addHorse () {
    IntStream.range(0, validateHorseCount())
            .forEach(increment -> getHorseInput());
  }

  public void getHorseInput(){
    Optional<String> tempHorseName = Optional.empty();
    Optional<String> tempWarcry = Optional.empty();
    Boolean tempHealth = true;
    System.out.print("Type Horse Name: ");
    tempHorseName = Optional.of(scan.nextLine().toUpperCase());
    System.out.print("Type Horse Warcry: ");
    tempWarcry = Optional.ofNullable(scan.nextLine());
    if(tempWarcry.get().equals("")){
      tempWarcry = Optional.of("NO WARCRY");
    }
    if(tempHorseName.get().equals("")){
      tempHorseName = Optional.of("Horse"+horseNumberNoName);
      horseNumberNoName++;
    }
    tempHealth = health.nextBoolean();
    if(tempHealth.equals(true)){
      listOfHorses.add(new Horse(tempHorseName, tempWarcry,
                      tempHealth));
    }
  }

  public void checkHealthy () {
    if ( listOfHorses.size() < 2 ) {
      System.out.println("1 Healthy Horse Only. Generating New Set Of Horses"
                        + " to complete participants");
      listOfHorses.add(new Horse(Optional.of("Adobo"),
                      Optional.of("NO WARCRY"), true));
      listOfHorses.add(new Horse(Optional.of("Caldereta"),
                      Optional.of("NO WARCRY"), true));
    }
  }

  public void getParticipants () {
    checkHealthy();
    System.out.println("===== HERE ARE THE PARTICIPANTS ====");
    System.out.println("*** Other Participants are Unhealthy ****");
    listOfHorses.forEach( h -> hView.printHorseDetails(h.getHorseName().get(),
                          h.getWarCry().get(), h.getIsHealthy()));
  }

  public int validateHorseCount() {
    int horseCount=0;
    boolean isCorrectNumberOfHorse = false;
      do{
        try{
          System.out.print("How many Horse Would Race: ");
          horseCount = scan.nextInt();
            if ( horseCount > 1 ) {
              isCorrectNumberOfHorse = true;
              scan.nextLine();
            } else {
              System.out.println("Please Input More Than 1 Horse To Start Race");
            }
        } catch( InputMismatchException e ){
          System.out.println("Please input a number! ");
          scan.next();
        }
      } while ( isCorrectNumberOfHorse == false );
    return horseCount;
  }

  public void inputRaceDistance() {
    do {
      System.out.println("*** MININUM DISTANCE: " + MIN_DISTANCE + " ***");
      System.out.print("Input Distance: ");
      try {
        raceDistance = scan.nextInt();
      } catch ( InputMismatchException e ) {
        System.out.println("Please input a number! ");
        scan.next();
      }
    } while(raceDistance < 250);
    System.out.println("===== RACE IS UP TO: " + raceDistance + "m ====");
  }

  public void setRaceDistanceForHorses () {
    listOfHorses.forEach( horse -> horse.setRaceDistance(raceDistance));
    listOfHorses.forEach( horse -> horse.setRemainingDistance(raceDistance));
  }

  public int getDistanceTravelled() {
    return horse.getDistanceTravelled();
  }

  public void setDistanceTravelled ( int distanceTravelled ) {
    horse.setDistanceTravelled(distanceTravelled);
  }

  public int getRemainingDistance() {
    return horse.getRemainingDistance();
  }

  public void setRemainingDistance( int remainingDistance ) {
    horse.setRemainingDistance(remainingDistance);
  }

  public void startRace () {
    hView.displayStartRace();
    listOfHorses.parallelStream().forEach((horse) -> {
      while ( horse.getDistanceTravelled() < raceDistance) {
              gallop(horse);
              hView.displayProgressOfHorses(horse);
        if ( horse.getDistanceTravelled() >= raceDistance ) {
          ranking.add(new Horse(horse.getHorseName(), horse.getWarCry(),
                        horse.getIsHealthy()));
          hView.displayHorseFinished(horse);
        }
      }
    });
    hView.printRank(ranking);
  }

  public void travelToBarn () {
    System.out.println("\n============== BARN ===============\n");
    listOfHorses.parallelStream().forEach((horse) ->{
      while ( horse.getDistanceToBarn() < START_LINE ){
        gallop(horse);
        hView.displayDistanceFromStartingLine(horse, START_LINE);
        if ( horse.getDistanceToBarn() >= START_LINE ) {
          System.out.println(horse.getHorseName().get() + " IS READY TO START RACING!");
        }
      }
    });
    isStarted = true;
  }

  public void gallop ( Horse horse ) {
    horse.setSpeed(randomNumber());
    if (isStarted) {
      updateHorsePositionRace( horse );
    } else {
      updateHorsePositionBarn( horse );
    }
  }

  public void updateHorsePositionRace ( Horse horse ) {
    horse.setRemainingDistance(horse.getRemainingDistance()-horse.getSpeed());
    horse.setDistanceTravelled(horse.getDistanceTravelled()+horse.getSpeed());
  }

  public void updateHorsePositionBarn ( Horse horse ) {
    horse.setDistanceToBarn(horse.getDistanceToBarn() + horse.getSpeed());
  }

}
