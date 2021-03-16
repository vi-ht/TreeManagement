/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmenttree_layout;

/**
 *
 * @author Thanh Vi
 */
public class Department {

    String Dcode;
    String Dname;

      public Department(String code, String name) {
            this.Dcode = code;
            this.Dname = name;
      }

      public String getDcode() {
            return Dcode;
      }
      public void setDcode(String Dcode) {
            this.Dcode = Dcode;
      }
      public String getDname() {
            return Dname;
      }
      public void setDname(String Dname) {
            this.Dname = Dname;
      }

      
     
      @Override
      public String toString() {
            return Dcode + "-" + Dname;
      }
      
}
