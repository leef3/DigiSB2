package conch.magic.smallbusiness.digisb2;

import java.io.Serializable;

/**
 * Created by Michael on 3/9/2015.
 */
public class GroupItem implements Serializable {
    String name;
    int row;

    GroupItem(String name, int row){
        this.name = name;
        this.row = row;
    }

    int getValue(){
        return row;
    }

    String getName(){
        return name;
    }
}
