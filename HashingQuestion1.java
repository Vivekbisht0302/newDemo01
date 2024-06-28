package ApnaClg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class HashingQuestion1 {
    public static void main(String[] args) {
        HashMap<String,String> tickets=new HashMap<>();
        tickets.put("chennai","bengaluru");
        tickets.put("mumbai","delhi");
        tickets.put("goa","chennai");
        tickets.put("delhi","goa");

        String start="";
        HashSet<String> set1=new HashSet<>();
        LinkedHashSet<String> ans=new LinkedHashSet<>();

        for(String k:tickets.keySet()){
            set1.add(k);
        }
        // System.out.println(set1);

        for(String k:tickets.keySet()){
            String value=tickets.get(k);
            if(set1.contains(value)){
                set1.remove(value);
            }
        }
        for(String seti:set1){
            start=seti;
        }       
        // System.out.println(start);

        String tempKey=start;
        ans.add(tempKey);

        while(!tickets.isEmpty()){
            if(!tickets.containsKey(tempKey)){
                break;
            }
            tempKey=tickets.get(tempKey);
            ans.add(tempKey);
        }
        System.out.println(ans);
    }
    
    
}
