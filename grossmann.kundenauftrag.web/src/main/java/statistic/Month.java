package statistic;

public class Month implements Comparable<Month> {

    public Month() {

    }

    public Month(int year, int month) {
        this.year = year;
        this.month = month;
    }

    private int year;

    private int month;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public int compareTo(Month o) {
        if (this.year == o.year) {
            return this.month - o.month;
        } else {
            return this.year - o.year;
        }
    }

    @Override
    public String toString() {
        return year + "-" + (month / 10 == 0 ? "0"+month : month) + "-01";
    }
}
