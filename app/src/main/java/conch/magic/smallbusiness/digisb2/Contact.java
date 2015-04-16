package conch.magic.smallbusiness.digisb2;

import android.net.Uri;

import java.io.Serializable;

/**
    Represents a Contact. The URI is the "path" to the android contact
 */
public class Contact implements Serializable {

    public String name;
    public String uri;
    public Contact(String n, Uri i){
        name = n;
        uri = i.toString();
    }

    public Uri getUri(){
        return Uri.parse(uri);
    }

    public boolean equals(Contact other){
        return other.uri == uri;
    }
}
