import interfaces.Stack;

public class stackClient {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Stack s = new StackImpl();
        s.push(3);
        s.isEmpty();
        Integer x =s.pop();	
        s.foo();
    }
}
