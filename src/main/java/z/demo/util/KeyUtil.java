package z.demo.util;

import java.util.Random;

public class KeyUtil {


    public static synchronized String getUniqueKey () {
        Random random = new Random( ) ;

        Integer i = random.nextInt( 900000 )+ 100000 ;

        return "" + System.currentTimeMillis() + i ;
    }
}
