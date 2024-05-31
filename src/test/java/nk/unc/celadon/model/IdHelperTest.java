/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package nk.unc.celadon.model;

import java.util.Base64;
import nk.unc.celadon.config.IdHelper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author neil
 */
public class IdHelperTest {

    public IdHelperTest() {
    }

    @Test
    void test() {
        System.out.println(IdHelper.generateHash());

    }
}
