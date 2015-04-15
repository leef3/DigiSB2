package conch.magic.smallbusiness.digisb2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable{

    public String string;
    public GroupItem item;
    int row;
    public final ArrayList<GroupItem> children = new ArrayList<GroupItem>();

    public Group(String title, int r) {
        string = title;
        row = r;
    }

}