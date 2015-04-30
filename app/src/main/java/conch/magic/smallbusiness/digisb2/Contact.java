package conch.magic.smallbusiness.digisb2;

import android.net.Uri;

import java.io.Serializable;

/**
    Represents a Contact. The URI is the "path" to the android contact
 */

//Contact class
public class Contact implements Serializable {

    //Contact class only stores a URI and a name, the URI is a link to the Anroid Address Book's Contact
    public String name;
    private String uri;
    public Contact(String n, Uri i){
        name = n;
        uri = i.toString();
    }

    //Getter method
    public Uri getUri(){
        return Uri.parse(uri);
    }

    //Comparator method for URIs
    public boolean equals(Contact other){
        return other.uri == uri;
    }
}
