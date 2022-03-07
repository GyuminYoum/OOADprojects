package Command;

public class Invoker {
    Command slot;
    public Invoker(){}
    public void setCommand(Command cm1){
        slot=cm1;
    }
    public void Perform(){
        slot.execute();
    }
}
