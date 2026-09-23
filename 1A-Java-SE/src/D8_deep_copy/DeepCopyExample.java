package D8_deep_copy;

class Dog {
    public String name;

    Dog(String name) {
        this.name = name;
    }

    private String getName() {
        return this.name;
    }
}

class DogDeepCopy implements Cloneable{
    public String name;

    DogDeepCopy(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class DeepCopyExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Dog dog = new Dog("Joey");
        Dog dogCpy = dog;  // shallow copy
        dogCpy.name = "Jason";
        System.out.println(dog.name);  // Jason

        DogDeepCopy dogDeepCopy = new DogDeepCopy("Joey");
        DogDeepCopy dogDeepCopyCpy = (DogDeepCopy) dogDeepCopy.clone();
        dogDeepCopyCpy.name = "Jason";
        System.out.println(dogDeepCopy.name);  // Joey
    }
}
