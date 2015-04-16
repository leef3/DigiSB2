package conch.magic.smallbusiness.digisb2;

import java.io.Serializable;

/**
 * Created by Michael on 3/9/2015.
 */

/* represents an inventoryItem (part of a category) */
public class GroupItem implements Serializable {
    String name;
    int row;
    int value;

    GroupItem(String name, int row){
        this.name = name;
        this.row = row;
        this.value = 0;
    }
    void increase(){
        value++;
    }
    void setValue(int v) { value = v; }

    void decrease(){
        if (value > 0) value--;
    }

    int getValue(){
        return value;
    }

    String getName(){
        return name;
    }
}
