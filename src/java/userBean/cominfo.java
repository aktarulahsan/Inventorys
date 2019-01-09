
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author aktar
 */
@ManagedBean
@SessionScoped
public class cominfo {
    double pbag;
    double rate, total=0 , remaining_b, payment;
    String pcc,opc, pay_method;
    
    public double getRemaining_b() {
        return remaining_b;
    }

    public void setRemaining_b(double remaining_b) {
        this.remaining_b = remaining_b;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    public String getPay_method() {
        return pay_method;
    }

    // payment, remaining_b, pay_method
    public void setPay_method(String pay_method) {    
        this.pay_method = pay_method;
    }

    public double getPbag() {
        return pbag;
    }

    public void setPbag(double pbag) {
        this.pbag = pbag;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
    
    
    public String savedata(){
        Connection con= null;
        PreparedStatement ps=null;
        Statement stm=null;
        ResultSet rs=null;
        ResultSet rs1=null;
        
        total=(pbag*rate);
        System.out.println(total);
        try {
            con= database.Database.getConnection();//pa_id, p_date, p_type, p_bag, p_rate, t_price
            stm=con.createStatement();
            ps=con.prepareStatement("insert into parcec_t ( p_bag, p_rate, t_price) values(?,?,?) ");
            ps.setDouble(1, pbag);
            ps.setDouble(2, rate);
            ps.setDouble(3, total);
            
            int count=0;
            count=ps.executeUpdate();
            if(count>0){
                
                System.out.println("done");
                cleardata();
                remining();
            
            }else{
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    public void cleardata(){
    pbag=0;
    rate=0;
    total=0;
    
    
    }
    
    public String remining(){
        Connection con= null;
        PreparedStatement ps=null;
        Statement stm=null;
        ResultSet rs=null;
        ResultSet rst=null;
        double dd= 0;
        
        
        double lastotal_price=0;
        double rem_balance=0;
        try {
            con= database.Database.getConnection();//pa_id, p_date, p_type, p_bag, p_rate, t_price
            stm=con.createStatement();
           
            rs=stm.executeQuery("SELECT t_price FROM parcec_t where pa_id=(select max(pa_id) from parcec_t)");
            if(rs.next()){
                
                lastotal_price=rs.getDouble(1);
                System.out.println("last price "+  lastotal_price);
            }else{
                    System.out.println("error");
                    }
            
            
            rst=stm.executeQuery("SELECT remaining_b FROM parcec_t where pa_id=(select max(pa_id) from parcec_t where remaining_b != 0)");
            if(rst.next()){
                rem_balance=rst.getDouble(1);
                System.out.println("remaining balance "  +rem_balance);
            }else{
                    System.out.println("error");
                    }
            
            
            dd=lastotal_price+rem_balance;
            System.out.println("remaining balance update"+ dd);
           
             ps=con.prepareStatement("insert into parcec_t (remaining_b) values (?)");
            ps.setDouble(1, dd);
            int count=0;
            count=ps.executeUpdate();
            if(count>0){
                System.out.println("remaining balance is update "+dd);
            }else{
                System.out.println("fail");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    
    
    
    
    public double getremaingb(){
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            con=database.Database.getConnection();
            st=con.createStatement();
            rs=st.executeQuery("select remaining_b FROM parcec_t where pa_id=(select max(pa_id) from parcec_t where remaining_b != 0)"); 
            if(rs.next()){
            remaining_b= rs.getDouble(1);
            }else{
                System.out.println("remaining_b error");
            }
            return remaining_b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public String paytoremaining(){
        Connection con=null;
        Statement st=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ResultSet rst=null;
        double tt=0;
        int count=0;
        try {
            con=database.Database.getConnection();
            st=con.createStatement();
            rs=st.executeQuery("select remaining_b FROM parcec_t where pa_id=(select max(pa_id) from parcec_t where remaining_b != 0)"); 
            if(rs.next()){
                remaining_b= rs.getDouble(1);
                tt = remaining_b - payment;
                System.out.println("tt is "+ tt); 
                savenewremaining();
                cleardata2();
               
                
            }else{
                System.out.println("calculate error");
            }
            
           
                
            
        } catch (Exception e) {
        }
    return null;
    }
    
    public void savenewremaining(){
        Connection con=null;
        Statement st=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
       
        double tt=0;
        int count=0;
        try {
            con=database.Database.getConnection();
            ps=con.prepareStatement("insert into parcec_t (payment, remaining_b, pay_method) values (?,?,?) ");
            System.out.println("remaining b"+remaining_b);
                tt=remaining_b-payment;
                System.out.println(ps);
                ps.setDouble(1, payment);
                ps.setDouble(2, tt);
                ps.setString(3, pay_method);
                
                count=ps.executeUpdate();
                if(count>0){
                //remaining_b=tt;
                    System.out.println("payment" +payment+(remaining_b)+remaining_b+(payment)+payment); 
                getremaingb();
                
                    
                }else{
                    System.out.println("payment insert error");
                }
                
                
                
        } catch (Exception e) {
        }
    
    }
    
    
     public void cleardata2(){
    payment=0;
    pay_method="";
    
     }
//    
//    public List<SelectItem> pType() {
//        List<SelectItem> retVal = new ArrayList<SelectItem>();
//        retVal.add(new SelectItem("Pcc"));
//        retVal.add(new SelectItem("Opc"));
//        
//        return retVal;
//        
//    
//    }
//    
    
}
