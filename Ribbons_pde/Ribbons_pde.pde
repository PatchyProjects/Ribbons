float currentX = 0;
float currentY = 0; 
int width = 700;
int height = 400;

void setup(){
  size(width,height);
  background(0);
  frameRate(60);
  noStroke();
  smooth();
  
  currentX = 0;          // Starting position
  currentY = height/2;        
}

float rads = 0.0;
float inc = TWO_PI/50.0;
float amp = 10;
int colorChange = 3;
float initElliW = 25;
float elliW = 25;
float elliH = 25;
float hChange = 4;
float rVal = random(75,255);
float bVal = random(75,255);
float gVal = random(75,255);

void draw(){
  currentX = currentX+2;
  if(currentX > width){      // Start a new Ribbon
    currentX = 0;
    currentY = random(height);
    amp = random(2,7);
    rVal = random(75,255);
    gVal = random(75,255);
    bVal = random(75,255);
    initElliW = random(25,50);
    elliW = initElliW;
    inc = TWO_PI/random(10,150);
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
  
  currentY = currentY+amp*sin(rads);
  elliW = initElliW + (initElliW/2*cos(rads));  //Changes width of ellipse
  
  rads = rads+inc;
  ellipse(currentX,currentY,elliW,elliH);
  fill(rVal,gVal,bVal);
}

void mouseClicked(){
  currentX = width;    // will reset
  background(0);       // clear canvas
}

