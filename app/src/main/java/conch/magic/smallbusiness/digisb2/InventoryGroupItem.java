package conch.magic.smallbusiness.digisb2;

/**
 * Created by Michael on 3/9/2015.
 */
public class InventoryGroupItem extends GroupItem {
    int value;
    InventoryGroupItem(String title, int row){
        super(title, row);
        value = 0;
    }

    void increase(){
        value++;
    }
    void setValue(int v) { value = v; }
    int getValue(){
        return value;
    }

    void decrease(){
        if (value > 0) value--;
    }
}
