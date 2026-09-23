package D8_deep_copy;

//If object a has member b, b has c, and we copy a.
//    Shallow: if a.b.c modified, aCopy.b.c also changed;
//
//    Deep: if a.b.c modified, aCopy.b.c not changed
//
//Is it right?


//


class A implements Cloneable{
    B b = new B();
    int d = 10;

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}
class B {
    int c = 10;
}
public class TwoDDemo {

}
