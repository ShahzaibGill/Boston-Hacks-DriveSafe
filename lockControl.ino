/*
  Temperature Measurement
  Reads an analog input on A0(output from LM35DZ), change it to the real room temperature and print it to serial monitor.
 */
float powervoltage=5;//define the power supply voltage.
float temperature;

void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
}
void loop() {
  // read the input on analog pin 0:
  float sensorValue = analogRead(A0);
  // print out the value you read:
  
  checkTemp();  
  
  Serial.print("The room temperature degree is:");
  Serial.println(temperature);
  
  while (temperature > 19 && temperature < 23)
  {
    
  }
  
  while (temperature > 23 && temperature < 26)
  {
    delay(5000);
    checkTemp();
      
  while (temperature > 26)
   {
     delay(5000);
     checkTemp(); 
    }

float checkTemp
{
  return temperature=(sensorValue/1023)*powervoltage*100;
}
