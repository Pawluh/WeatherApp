package paczwa.model;

public class TempInteger {

    private int temp;

    public TempInteger(int temp){
        this.temp = temp;
    }

    @Override
    public String toString(){
        return String.valueOf(this.temp);
    }
}
