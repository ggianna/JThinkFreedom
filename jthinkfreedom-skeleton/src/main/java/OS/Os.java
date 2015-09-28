/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OS;

/**
 *
 * @author xrousakis
 */
public class Os {

    private boolean var;

    public Os() {
        if (System.getProperty("os.name").contains("Linux")) {
            var = true;
        } else {
            var = false;
        }
    }

    public Character returnChatracter() {
        if (var == true) {
            return '/';
        } else {
            return '\\' ;
        }
    }
    
}
