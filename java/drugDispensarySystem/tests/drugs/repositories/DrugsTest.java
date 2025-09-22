package drugs.repositories;

import drugs.model.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrugsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void drugCanBeFound_byId_ifRegisteredTest(){
        Drugs drugs = new Drugs();
        assertNotNull(drugs.findById("pnd"));
    }

    @Test
    public void findByIdReturnsNull_ifDrugNotFoundTest(){
        Drugs drugs = new Drugs();
        assertNull(drugs.findById("pod"));
    }

    @Test
    public void updateDrugQuantity_afterDispenseByPharmTest(){
        Drugs drugs = new Drugs();
        Drug drugToBeBought = drugs.findById("pnd");
        assertNotNull(drugToBeBought);
        drugs.update("pnd", drugToBeBought, 5);
        assertEquals(5, drugs.findById("pnd").getQuantity());
    }
}