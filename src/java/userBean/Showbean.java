/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import java.sql.*;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author aktar
 */
@ManagedBean
@SessionScoped
public class Showbean {
   int pa_id;
   Date p_date;
   double  p_bag, p_rate, t_price, payment, remaining_b;
   String p_type,pay_method;

    public int getPa_id() {
        return pa_id;
    }

    public void setPa_id(int pa_id) {
        this.pa_id = pa_id;
    }

    public Date getP_date() {
        return p_date;
    }

    public void setP_date(Date p_date) {
        this.p_date = p_date;
    }

    public double getP_bag() {
        return p_bag;
    }

    public void setP_bag(double p_bag) {
        this.p_bag = p_bag;
    }

    public double getP_rate() {
        return p_rate;
    }

    public void setP_rate(double p_rate) {
        this.p_rate = p_rate;
    }

    public double getT_price() {
        return t_price;
    }

    public void setT_price(double t_price) {
        this.t_price = t_price;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getRemaining_b() {
        return remaining_b;
    }

    public void setRemaining_b(double remaining_b) {
        this.remaining_b = remaining_b;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    
   public void showalldata(){
       Connection con=null;
       PreparedStatement ps=null;
       ResultSet rs=null;
       String f;
       try {
           con=database.Database.getConnection();
           ps=con.prepareStatement("select * from parcec_t ");
           
            rs=ps.executeQuery();
           while(rs.next()){
                //pa_id, p_date, p_type, p_bag, p_rate, t_price, payment, remaining_b, pay_method
                setPa_id(rs.getInt(1));
               setP_bag(rs.getDouble(4));
              setP_rate(rs.getDouble(5));
              setT_price(rs.getDouble(6));
              setPayment(rs.getDouble(7));
             setRemaining_b(rs.getDouble(8));
              
           }
             
              
          
       } catch (Exception e) {
           e.printStackTrace();
       }
      
   }
    
}
