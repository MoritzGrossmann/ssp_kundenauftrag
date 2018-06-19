package statistic;

public class Month implements Comparable<Month> {

    public Month() {

    }

    public Month(int year, int month) {
        if (this.month > 12 || this.month < 1) {
            throw new IllegalArgumentException("Argument month must be between 1 and 12");
        }

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

    public Month next() {
        return this.month == 12 ? new Month(this.year + 1, 1) : new Month(this.year, this.month +1);
    }

    public Month previous() {
        return this.month == 1 ? new Month(this.year - 1, 12) : new Month(this.year, this.month -1);
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
