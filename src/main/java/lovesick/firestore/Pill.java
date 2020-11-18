package lovesick.firestore;

public class Pill { 
  
  public String name;
  public Fields fields;
  public String createTime;
  public String updateTime;

  @Override
  public String toString() {
    return "Pill { name = " + fields.name.stringValue + ", taste = " + fields.taste.stringValue + " }";
  }

}

class Fields {
  public StringValue taste;
  public StringValue name;
}

class StringValue {
  public String stringValue;
}

/*
{  
  "name": "projects/lovesick-firestore/databases/(default)/documents/pills/kfAzN9F4B1MPcJYoYpH7",  
  "fields": {    
    "taste": {      
      "stringValue": "Bland"    
    },    
    "name": {      
      "stringValue": "Pink"    
    }  
  },  
  "createTime": "2020-11-16T10:50:46.253041Z",  
  "updateTime": "2020-11-18T11:06:06.036742Z"
}
*/

