//4 ergasia asfaleia
//icsd08011-icsd08041

import java.io.IOException;
import java.io.Serializable;


public class User implements Serializable{
    
 private String onoma,Description,User_name;
    
public User(String A,String B,String C) { 
     onoma=A;
     Description=B;
     User_name=C;
} 
 
public String getOnoma(){return onoma;}
public String getdescription(){return Description;}
public String getuser_name(){return User_name;}


public String toString(){

return "Onoma: "+onoma+ "Description: "+Description+ "username "+User_name; 
}
}
