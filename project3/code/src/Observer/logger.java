package Observer;

public class logger implements Observer, Writer {
    private String content;
    public void update(String content1){
        this.content=content1;
        display();
    }
    public void display(){
        //write it
    }
}
