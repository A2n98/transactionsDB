package models;

public class Task2Model {
    public float avg;
    public int count;
    public String period;

    @Override
    public String toString() {
        return "Среднее значение: " + avg + " Количество: " + count + " Период: " + period;
    }
}
