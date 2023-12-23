package practice3.task1;


public class Task1 {
    public static void main(String[] args){
        Utils.TempSensor tempSensor = new Utils.TempSensor();
        Utils.Temperature level = new Utils.Temperature();
        tempSensor.addObserver(level);

        Utils.CO2Sensor co2Sensor = new Utils.CO2Sensor();
        Utils.CO2 level1 = new Utils.CO2();
        co2Sensor.addObserver(level1);

        Utils.AlarmSystem alarmSystem = new Utils.AlarmSystem();
        Utils.Alarm alarm = new Utils.Alarm();
        alarmSystem.addObserver(alarm);


        while (true) {
            try {
                tempSensor.changeMessage("");
                co2Sensor.changeMessage("");
                if (level.getTemp() > 25 && level1.getCo2() > 70)
                    alarmSystem.changeMessage("Превышение температуры и CO2");
                else if (level.getTemp() > 25)
                    alarmSystem.changeMessage("Превышение температуры");
                else if (level1.getCo2() > 70)
                    alarmSystem.changeMessage("Превышение СO2");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}





