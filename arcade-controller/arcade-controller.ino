#include <Bounce.h>

// Give all buttons a dedicated GPIO pin
const int button1Pin = 1;
const int button2Pin = 2;
const int button3Pin = 3;
const int button4Pin = 4;
const int button5Pin = 5;
const int button6Pin = 6;

// Give all joystick directions a dedicated GPIO pin
const int joystickLeftPin = 10;
const int joystickRightPin = 9;
const int joystickUpPin = 8;
const int joystickDownPin = 7;

// Make button objects
Bounce button1 = Bounce(button1Pin, 10);  // 10 ms debounce
Bounce button2 = Bounce(button2Pin, 10);  // 10 ms debounce
Bounce button3 = Bounce(button3Pin, 10);  // 10 ms debounce
Bounce button4 = Bounce(button4Pin, 10);  // 10 ms debounce
Bounce button5 = Bounce(button5Pin, 10);  // 10 ms debounce
Bounce button6 = Bounce(button6Pin, 10);  // 10 ms debounce

// Make joystick objects
Bounce joystickUp= Bounce(joystickUpPin, 10);  // 10 ms debounce
Bounce joystickDown = Bounce(joystickDownPin, 10);  // 10 ms debounce
Bounce joystickRight = Bounce(joystickRightPin, 10);  // 10 ms debounce
Bounce joystickLeft = Bounce(joystickLeftPin, 10);  // 10 ms debounce

unsigned long time;


void setup() {
  // Initialize buttons
  pinMode(button1Pin, INPUT_PULLUP);
  pinMode(button2Pin, INPUT_PULLUP);
  pinMode(button3Pin, INPUT_PULLUP);
  pinMode(button4Pin, INPUT_PULLUP);
  pinMode(button5Pin, INPUT_PULLUP);
  pinMode(button6Pin, INPUT_PULLUP);

  // Initialize joystick
  pinMode(joystickUpPin, INPUT_PULLUP);
  pinMode(joystickDownPin, INPUT_PULLUP);
  pinMode(joystickRightPin, INPUT_PULLUP);
  pinMode(joystickLeftPin, INPUT_PULLUP);
}

void loop() {
  // Update all the button and joystick objects
  button1.update();
  button2.update();
  button3.update();
  button4.update();
  button5.update();
  button6.update();

  joystickUp.update();
  joystickDown.update();
  joystickRight.update();
  joystickLeft.update();

  // Check each button and joystick for "falling" edge.
  // falling = high (not pressed - voltage from pullup resistor)
  //           to low (pressed - button connects pin to ground)

  // When in a if-statement, the while-loop checks if the button/joystick is being kept pressed. This way we can get multiple inputs, without having to press all the time.
  if (button1.fallingEdge()) {
    //Because 'W' is being used as both a 'pause'-button and 'off'-button, we make it so that if the 'W' is being hold for 3 seconds it also prints a 'Z'(off).
    bool flag1 = true;
    bool flag2 = true;
    while(!button1.update() && flag2){
      if(flag1){
        time = millis();
        Keyboard.press(KEY_W);
        delay(50);
        Keyboard.release(KEY_W);
        flag1 = false;
      }
      if(millis() - time > 3000 && !flag1){
        Keyboard.press(KEY_Z);
        delay(50);
        Keyboard.release(KEY_Z);
        flag2 = false;
      }
    }
  }
  if (button2.fallingEdge()) {
    while(!button2.update()){
      Keyboard.press(KEY_A);
      delay(150);
      Keyboard.release(KEY_A);
    }
  }
  if (button3.fallingEdge()) {
    while(!button3.update()){
      Keyboard.press(KEY_S);
      delay(150);
      Keyboard.release(KEY_S);
    }
  }
  if (button4.fallingEdge()) {
    while(!button4.update()){
      Keyboard.press(KEY_D);
      delay(150);
      Keyboard.release(KEY_D);
    }
  }
  if (button5.fallingEdge()) {
    while(!button5.update()){
      Keyboard.press(KEY_E);
      delay(150);
      Keyboard.release(KEY_E);
    }
  }
  if (button6.fallingEdge()) {
    while(!button6.update()){
      Keyboard.press(KEY_Q);
      delay(150);
      Keyboard.release(KEY_Q);
    }
  }

  //Every joystick direction is coded such that if we hold it for almost a second, it speeds up.
  if (joystickUp.fallingEdge()) {
    int counter = 0;
    while(!joystickUp.update()){
      if(counter < 5){
        time = millis();
        Keyboard.press(KEY_UP);
        delay(150);
        Keyboard.release(KEY_UP);
        counter++;
      }
      if(counter >= 5){
        Keyboard.press(KEY_UP);
        delay(50);
        Keyboard.release(KEY_UP);
      }
    }
    counter = 0;
  }
  if (joystickDown.fallingEdge()) {
    int counter = 0;
    while(!joystickDown.update()){
      if(counter < 5){
        time = millis();
        Keyboard.press(KEY_DOWN);
        delay(150);
        Keyboard.release(KEY_DOWN);
        counter++;
      }
      if(counter >= 5){
        Keyboard.press(KEY_DOWN);
        delay(50);
        Keyboard.release(KEY_DOWN);
      }
    }
    counter = 0;
  }
  if (joystickRight.fallingEdge()) {
    int counter = 0;
    while(!joystickRight.update()){
      if(counter < 5){
        time = millis();
        Keyboard.press(KEY_RIGHT);
        delay(150);
        Keyboard.release(KEY_RIGHT);
        counter++;
      }
      if(counter >= 5){
        Keyboard.press(KEY_RIGHT);
        delay(50);
        Keyboard.release(KEY_RIGHT);
      }
    }
    counter = 0;
  }
  if (joystickLeft.fallingEdge()) {
    int counter = 0;
    while(!joystickLeft.update()){
      if(counter < 5){
        time = millis();
        Keyboard.press(KEY_LEFT);
        delay(150);
        Keyboard.release(KEY_LEFT);
        counter++;
      }
      if(counter >= 5){
        Keyboard.press(KEY_LEFT);
        delay(50);
        Keyboard.release(KEY_LEFT);
      }
    }
    counter = 0;
  }
}
