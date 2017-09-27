// Authors: Aditya Gupta and Jay Kim
// Class-A9: Demo Class

/**
 Tests out the constructor and methods of the Gun class
*/
public class GunTester_4GuptaKim {
   /**
    Main method - tests the shooting, reloading, and scope of four different guns
    
    @param args command-line arguments
   */
   public static void main(String[] args) {
      // local variable, simulates an AK-47 (assault rifle)
      Gun AK_47 = new Gun("AK-47", 30, 0.85, false);
      AK_47.print();
      AK_47.shoot();
      AK_47.print();
      AK_47.reload();
      AK_47.print();
      AK_47.shoot(5);
      AK_47.print();
      
      System.out.println();
      
      // local variable, simulates a P90 (submachine gun)
      Gun P90 = new Gun("P90", 50, 0.6, false);
      P90.print();
      P90.shoot();
      P90.print();
      P90.reload();
      P90.print();
      
      System.out.println();
      
      // local variable, simulates an AWP (sniper rifle)
      Gun AWP = new Gun("AWP", 10, 0.95, true);
      AWP.print();
      AWP.shoot();
      AWP.print();
      AWP.toggleScope();
      AWP.reload();
      AWP.print();
      AWP.toggleScope();
      AWP.print();
      
      System.out.println();
      
      // local variable, simulates a plasma cannon (made-up gun)
      Gun plasmaCannon = new Gun("Plasma Cannon", 1, 0.5, false);
      plasmaCannon.print();
      plasmaCannon.shoot();
      plasmaCannon.print();
      plasmaCannon.shoot();
      plasmaCannon.print();
      plasmaCannon.reload();
      plasmaCannon.print();
      plasmaCannon.shoot(2);
      plasmaCannon.print();
   }
}

/**
 Simulates a Gun: can shoot (and sometimes miss, depending on accuracy), reload, and, if it has a scope, can scope in to increase accuracy
*/
class Gun {
   // instance variables
   String name;
   int maxAmmo;
   int currentAmmo;
   double accuracy;
   double adjustedAccuracy;
   boolean hasScope;
   boolean isScoped;
   
   /**
    Initializes a Gun object, taking paramters of name, maximum ammo, accuracy, and whether the gun has a scope
    
    @param name     the name of the gun
    @param maxAmmo  the gun's ammo capacity
    @param accuracy the chance (out of 1) that the gun will hit its target
    @param hasScope whether the gun has a scope
   */
   public Gun(String name, int maxAmmo, double accuracy, boolean hasScope) {
      this.name = name;
      this.maxAmmo = maxAmmo;
      this.currentAmmo = maxAmmo;
      this.accuracy = accuracy;
      this.adjustedAccuracy = accuracy;
      this.hasScope = hasScope;
   }
   
   /**
    Shoots gun, misses depending on accuracy
    
    @return false if out of ammo, true if it shoots successfully
   */
   public boolean shoot() {
      if (currentAmmo == 0) {
         System.out.println(name + " is out of ammo!");
         return false;
      }
      
      if (Math.random() < accuracy) {
         System.out.println(name + " hit its target!");
      } else {
         System.out.println(name + " missed!");
      }
      
      currentAmmo--;
      
      return true;
   }
   
   /**
    Shoots gun multiple times
    
    @param times number of times to shoot the gun
   */
   public void shoot(int times) {
      for (int i = 0; i < times; i++) {
         if (!shoot()) {
            return;
         }
      }
   }
   
   /**
    Reloads the gun, setting currentAmmo to maxAmmo
   */
   public void reload() {
      currentAmmo = maxAmmo;
      System.out.println("Reloaded " + name);
   }
   
   /**
    Toggles the gun's scope (if it has one)
   */
   public void toggleScope() {
      if (!hasScope) {
         System.out.println(name + " does not have a scope!");
         return;
      }
      
      isScoped = !isScoped;
      
      adjustedAccuracy = accuracy;
      if (isScoped) {
         adjustedAccuracy += 0.1;
      }
      
      if (adjustedAccuracy >= 1) {
         adjustedAccuracy = 1;
      }
   }
   
   /**
    String representation of the gun
   
    @return the gun's name, current ammo, max ammo, and accuracy; if the gun has a scope, the method also returns whether it is scoped
   */
   public String toString() {
      return name + ": " + currentAmmo + "/" + maxAmmo + (hasScope ? ", scoped: " + isScoped  + ", current" : ",") + " accuracy: " + adjustedAccuracy * 100 + "%";
   }
   
   /**
    Prints out all the data from the toString() method
   */
   public void print() {
      System.out.println(this);
   }
}