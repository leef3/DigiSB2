package conch.magic.smallbusiness.digisb2;

import java.io.Serializable;

/**
 * Created by Michael on 3/9/2015.
 */

/* represents an inventoryItem (part of a category) */
//Group will contain multiple GroupItems
public class GroupItem implements Serializable {
    String name;
    int row;
    int value;

    //Constructor
    GroupItem(String name, int row){
        this.name = name;
        this.row = row;
        this.value = 0;
    }
    //Method for setting the value using the keypad directly
    void setValue(int v) { value = v; }

    //Methods for the button press to increase or decrease quantity by 1
    void increase(){
        value++;
    }
    void decrease(){
        if (value > 0) value--;
    }

    //Getter Methods
    int getValue(){
        return value;
    }
    String getName(){
        return name;
    }
}
