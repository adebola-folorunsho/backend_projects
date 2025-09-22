package drugs.repositories;

import drugs.model.Drug;

import java.util.HashMap;
import java.util.Map;

public class Drugs {
    private Map<String, Drug> drugs = new HashMap<>();

    public Drugs(){
        drugs.put("pnd", new Drug("panadol", 10, "2024-08-15", "2026-12-23"));
        drugs.put("ibf", new Drug("ibuprofen" , 10, "2024-08-15", "2026-12-23"));
        drugs.put("vitc", new Drug("setrin" , 20, "2024-08-15", "2026-12-23"));
        drugs.put("lnt", new Drug("lonart",30, "2024-08-15", "2026-12-23"));
    }

    public void update(String id, Drug drug, int quantityBought){
        drug.setQuantity(drug.getQuantity() - quantityBought);
        drugs.put(id, drug);
    }

    public Map<String, Drug> findAll(){
        return drugs;
    }

    public Drug findById(String id){
        return drugs.get(id);
    }

    public int count(){
        return drugs.size();
    }

}
