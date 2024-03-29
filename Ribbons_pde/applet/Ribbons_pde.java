import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class Ribbons_pde extends PApplet {

/////////////////////////////
//  Ribbons by Chris Hall  //
/////////////////////////////

int width = 700;
int height = 400;
int mode;

public void setup(){
  size(700,400);
  background(0);
  frameRate(60);
  noStroke();
  smooth();
  
  mode = 0;
  rightX = width;          // Starting position
  leftX = 0;        
}

float rads = 0.0f;
float inc = TWO_PI/50.0f;
float amp = 10;
int colorChange = 3;
float initElliW = 25;
float elliW = 25;
float elliH = 25;
float hChange = 4;
float rVal = random(75,255);
float bVal = random(75,255);
float gVal = random(75,255);

public void draw(){
  drawRibbon();
}

float rightX = width;
float leftX, leftY, rightY; 
float leftRads = rads;

public void drawRibbon(){
    rightX = rightX+2;
    leftX = leftX-2;
  if(rightX > width && leftX < 0){      // Start a new Ribbon
    if(mode == 0)
      rightX = 0;
    else if(mode == 1)
      rightX = width/2;
    else
      rightX = random(width);
    
    rightY = random(height);
    leftX = rightX;
    leftY = rightY;
    amp = random(2,7);
    rVal = random(75,255);
    gVal = random(75,255);
    bVal = random(75,255);
    initElliW = random(25,50);
    elliW = initElliW;
    inc = TWO_PI/random(10,150);
    leftRads = rads;
  }
  
  if(rVal > 255 || gVal > 255 || bVal > 255){
    colorChange = -3;
  }
  if(rVal < 75 || gVal < 75 || bVal < 75){
    colorChange = 3;
  }
  
  rVal = rVal + colorChange;          // Handle Colors
  gVal = gVal + colorChange;          
  bVal = bVal + colorChange;
  
  rightY = rightY+amp*sin(rads);
  leftY = leftY+amp*sin(leftRads);
  elliW = initElliW + (initElliW/2*cos(rads));  //Changes width of ellipse
  
  rads = rads+inc;
  leftRads = rads-inc;
  
  fill(rVal,gVal,bVal);
  ellipse(rightX,rightY,elliW,elliH);
  if(mode >= 1)
    ellipse(leftX,leftY,elliW,elliH);
  if(mode >= 2){
    ellipse(rightY,rightX,elliW,elliH);
    ellipse(leftY,leftX,elliW,elliH);
  }  
}

public void mouseClicked(){
  mode = (mode+1)%3;   // change display mode
  rightX = width;    // will make positions reset
  leftX = 0;
  background(0);       // clear canvas 
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "Ribbons_pde" });
  }
}
