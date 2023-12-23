package practice3.task1;

import java.util.Observable;
import java.util.Observer;

public class Utils {
    static class TempSensor extends Observable {
        public void changeMessage(String message) {
            setChanged();
            notifyObservers(message);
        }
    }

    static class Temperature implements Observer {
        int temp;
        @Override
        public void update(Observable o, Object arg){
            temp = (int)(Math.random()*(16)+15);
            System.out.println("\nТемпература: " + temp);
        }
        public int getTemp() {
            return temp;
        }
    }

    static class CO2Sensor extends Observable {
        public void changeMessage(String message){
            setChanged();
            notifyObservers(message);
        }
    }

    static class CO2 implements Observer {
        int co2;
        @Override
        public void update(Observable o, Object arg){
            co2 = (int)(Math.random()*(71)+30);
            System.out.println("CO2: " + co2);
        }
        public int getCo2() {
            return co2;
        }
    }

    static class AlarmSystem extends Observable {
        public void changeMessage(String message){
            setChanged();
            notifyObservers(message);
        }
    }

    static class Alarm implements Observer {
        @Override
        public void update(Observable o, Object arg){
            System.out.println(arg);
        }
    }
}
