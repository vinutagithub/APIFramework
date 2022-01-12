package PositiveTestCase;

import BaseServices.BaseServices;
import Util.ConfigUtil;
import org.testng.annotations.Test;

public class PostTest extends BaseServices {


    @Test
    public void test1(){
        System.out.println(createToken());
    }
}
