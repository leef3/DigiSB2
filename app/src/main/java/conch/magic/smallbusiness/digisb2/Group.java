package conch.magic.smallbusiness.digisb2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* Represents a category that contains it's children (or inventoryItems) */
//Group contains multiple GroupItems
public class Group implements Serializable{

    public String string;
    public GroupItem item;
    int row;
    public final ArrayList<GroupItem> children = new ArrayList<GroupItem>();

    //Constructor
    public Group(String title, int r) {
        string = title;
        row = r;
    }

}