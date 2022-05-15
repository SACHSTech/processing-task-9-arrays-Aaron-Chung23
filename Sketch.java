import processing.core.PApplet;

public class Sketch extends PApplet {
	
  //Global variables
  float[] circleY = new float[10];
  float[] circleX = new float[10];
  boolean[] snow = new boolean[20];
  boolean playerUp = false;
  boolean playerDown = false;
  boolean playerLeft = false;
  boolean playerRight = false;
  float playerX = 200;
  float playerY = 200;
  int fallSpeed = 2;
  float snowRadius = 25;
  float playerRadius= 15;
	
  float health = 90;
  float healthCap = 90;
  float healthSquares = 90;
  float dist;
  boolean click = false;
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
      snow[i]= true;
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
    background(55);
    //Calls Methods
    player();

    snowfall();

    movePlayer();

    healthBar();

    //Makes it so if the HP bar is zero, it turns the screen white and stops the program

    if (health == 0){
      background(255,255,255);
      stop();
    }

  }

  /**
   * Calls the snow fall with hit detection
   */

  public void snowfall(){
   
    //Makes snow and makes it fall randomly
    fill(255,255,255);
      for (int i = 0; i < circleY.length; i++) {
        
        if (snow[i] == true){
        ellipse(circleX[i], circleY[i], 30, 30);
        }
      }
      for (int i = 0; i < circleY.length; i++) {
      circleY[i] += fallSpeed;

        if (circleY[i] > height) {
          circleY[i] = 0;
        }

        circleY[i] += fallSpeed;
      
          if (circleY[i] > height) {
            circleY[i] = 0;
            snow[i] = true;
            circleX[i] = random(width);
          }
      //collision detection if player is hit by snow
      if (dist(playerX, playerY, circleX[i], circleY[i]) <= (20) && (snow[i] == true)){
        health -= 30;
        snow[i] = false;
        }
      //Detection for snow clicks
      if(click && (dist(mouseX, mouseY, circleX[i], circleY[i]) <= (20))){
        snow[i] = false;
        }
      }
  }
  
  /**
  * Controls used to move the blue ball
  */
  public void movePlayer(){

    if (playerUp) {
      playerY--;
    }
    else if (playerDown) {
      playerY++;
    }
    else if(playerLeft){
      playerX--;
    }
    else if(playerRight){
      playerX++;
    }
    
    //Makes sure the blue ball doesn't move off screen
    
    if(playerY > height) {
      playerY = 0;
    }
    if(playerX > width) {
      playerX = 0;
    }
    if(playerY < 0) {
      playerY = height;
    }
    if(playerX < 0) {
      playerX = width;
    }
}
/**
 * Draws the blue ball on screen
 */
public void player(){
  fill(23, 45, 99);
    ellipse(playerX, playerY, playerRadius, playerRadius);
}
/**
 * Detects what key has been pressed
 */
public void keyPressed(){
  if (key == 'w') {
    playerUp = true;
  } 
  else if (key == 's') {
    playerDown = true;
  } 
  else if(key == 'a'){
    playerLeft = true;
  }
  else if(key == 'd'){
    playerRight = true; 
  }
  
  
  if (keyCode == UP){
    fallSpeed = 1;
  }
      
  if (keyCode == DOWN){
    fallSpeed = 3;
    }

}
/**
 * Detects what key has been released
 */
public void keyReleased(){
  if (key == 'w') {
    playerUp = false;
  } 
  else if (key == 's') {
    playerDown = false;
  } 
  else if(key == 'a'){
    playerLeft = false;
  }
  else if(key == 'd'){
    playerRight = false; 
  }

  if (keyCode == UP){
    fallSpeed = 2;
  }
      
  if (keyCode == DOWN){
    fallSpeed = 2;
    }
}
/** 
 * Draws the Health bar on screen
 */
public void healthBar(){
  float drawWidth = (health / healthCap) * healthSquares;
  fill(255,0,0);
  rect(0, 0, drawWidth, 30);

 stroke(0);
 noFill();
 rect(0, 0, 30, 30);
 rect(0, 0, 60, 30);
 rect(0, 0, 90, 30);

}
/**
* Detects when mouse is pressed
*/
public void mousePressed(){
  click = true;
}
/** 
 * Detects when mouse is released
 */
public void mouseReleased(){
  click = false;
}

  // define other methods down here.
}