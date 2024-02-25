
class Parent{
    int x;
    int y;

    public Parent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void method(){
        System.out.println("paren method");
    }

    public void parentOnlyMethod(){
        System.out.println("I am parent only method");
    }
}

class Child extends Parent{
    int z;

    public Child(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public void method() {
        System.out.println("child overrides parent");
    }

    public void childOnlyMethod(){
        System.out.println("i am child only method");
    }
}
public class ParentTypeChildObject {

    public static void main(String[] args) {
        // child object but Parent type. so only Parent access will be there
        // but child given implementations will be there for method with parent level access;
        Parent p = new Child(1,2,3);
        p.method();
        p.parentOnlyMethod();
        Child c = (Child) p;
        System.out.println(c.getZ());
        c.method();
        c.parentOnlyMethod();
        c.childOnlyMethod();
    }
}
