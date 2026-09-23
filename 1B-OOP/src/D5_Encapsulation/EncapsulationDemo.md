# Encapsulation

Encapsulation means bundling an object's state together with the logic that operates on it, and controlling how — or whether — code outside the class can read or change that state. In Java, a common way to do this is to make fields `private` and expose access through public methods, but that's a technique, not the definition: not every field needs a public setter, and sometimes withholding one *is* the encapsulation (e.g. a value that should never change once the object is constructed). The point isn't "private fields + getters/setters" as a checklist — it's hiding implementation details and protecting the object's invariants from outside interference.

## `Inventory` and `Item`: an object made of other objects

```java
class Item {
}

class Inventory {
    private int capacity;
    private String type;
    private Item item1; // aggregate field
    private Item item2; // aggregate field

    public Inventory() {}

    public Inventory(int capacity, String type) {
        this.capacity = capacity;
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Inventory {" +
                "capacity = " + capacity +
                ", type = '" + type + '\'' +
                '}';
    }
}
```

`Item` is deliberately left empty — it isn't meant to be a fully fleshed-out class. It exists so `Inventory` has a second class to hold references to (`item1`, `item2`), to make the point that an encapsulated field doesn't have to be a primitive or a `String` — it can be a reference to another object just as easily. `item1` and `item2` aren't set to anything in this demo; they're there purely to illustrate the field declaration, not to be used.

`capacity` and `type` follow the standard encapsulation pattern: both fields are `private`, and the only way to read or change them from outside `Inventory` is through `getCapacity()`/`setCapacity()` and `getType()`/`setType()`.

## `Player`: aggregating an `Inventory`

```java
class Player extends Object {
    private String name;
    private int level;
    private Inventory inventory; // aggregate field

    public Player(String name, int level, Inventory inventory) {
        this.name = name;
        this.level = level;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "D5_Encapsulation.Player{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", inventory=" + inventory +
                '}';
    }
}
```

Same pattern one level up: `Player`'s fields are all `private`, including its `inventory` field, which is itself an encapsulated `Inventory` object. (`extends Object` is written explicitly here, but every Java class already extends `Object` implicitly whether it's written or not — this line doesn't change anything.)

## Running the demo

```java
public static void main(String[] args) {
    Inventory inventory = new Inventory();
    inventory.setType("backpack");
    inventory.setCapacity(20);

    Player player = new Player("bob", 10, inventory);
    System.out.println(player.getInventory().getType());
    System.out.println(player.getInventory().getCapacity());

    System.out.println(player);
}
```

Output:

```
backpack
20
D5_Encapsulation.Player{name='bob', level=10, inventory=Inventory {capacity = 20, type = 'backpack'}}
```

`inventory` is built with the no-args constructor and then populated entirely through its setters (`setType`, `setCapacity`) — its fields are never touched directly, because they're `private`. `player.getInventory().getType()` shows that reading a nested encapsulated object still works the same way: go through the getter chain, not direct field access.
