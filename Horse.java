import java.util.Optional;

public class Horse {

  private int speed;
  private int distanceTravelled = 0;
  private int remainingDistance = 0;
  private int raceDistance = 0;
  private int distanceToBarn = 0;
  private Boolean isHealthy = true;
  private Optional<String> horseName;
  private Optional<String> warCry;

  public Horse ( Optional<String> horseName, Optional<String> warCry, Boolean isHealthy ) {
    this.horseName = horseName;
    this.warCry = warCry;
    this.isHealthy = isHealthy;
  }

  public Optional<String> getHorseName(){
    return horseName;
  }

  public void setHorseName ( Optional<String> horseName ) {
    this.horseName = horseName;
  }

  public Optional<String> getWarCry() {
    return warCry;
  }

  public void setWarCry ( Optional<String> warCry ) {
    this.warCry = warCry;
  }

  public Boolean getIsHealthy(){
    return isHealthy;
  }

  public void setIsHealthy( Boolean isHealthy ) {
    this.isHealthy = isHealthy;
  }

  public void setSpeed ( int speed ) {
    this.speed = speed;
  }

  public int getSpeed() {
    return speed;
  }

  public int getDistanceTravelled() {
    return distanceTravelled;
  }

  public void setDistanceTravelled ( int distanceTravelled ) {
    this.distanceTravelled = distanceTravelled;
  }

  public int getRemainingDistance() {
    return remainingDistance;
  }

  public void setRemainingDistance ( int remainingDistance ) {
    this.remainingDistance = remainingDistance;
  }

  public int getRaceDistance() {
    return raceDistance;
  }

  public void setRaceDistance ( int raceDistance ) {
    this.remainingDistance = raceDistance;
  }

  public int getDistanceToBarn() {
    return distanceToBarn;
  }

  public void setDistanceToBarn ( int distanceToBarn ) {
    this.distanceToBarn = distanceToBarn;
  }

}
