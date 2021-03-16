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
public class Employee {
      String Ecode;
      String Ename;
      String Esalary;

      public Employee(String code, String name, String salary) {
            this.Ecode = code;
            this.Ename = name;
            this.Esalary = salary;
      }

      public String getEcode() {
            return Ecode;
      }

      public void setEcode(String Ecode) {
            this.Ecode = Ecode;
      }

      public String getEname() {
            return Ename;
      }

      public void setEname(String Ename) {
            this.Ename = Ename;
      }

      public String getEsalary() {
            return Esalary;
      }

      public void setEsalary(String Esalary) {
            this.Esalary = Esalary;
      }

     

      @Override
      public String toString() {
            return Ecode + "-" + Ename;
      }
      
}
