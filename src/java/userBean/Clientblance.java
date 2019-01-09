/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author aktar
 */
public class Clientblance {
    int cb_id, client_id;
    Date p_date;
    double p_type, p_bag, p_rate, t_price, payment, remaining;
    String pay_mehod, msg="";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCb_id() {
        return cb_id;
    }

    public void setCb_id(int cb_id) {
        this.cb_id = cb_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public Date getP_date() {
        return p_date;
    }

    public void setP_date(Date p_date) {
        this.p_date = p_date;
    }

    public double getP_type() {
        return p_type;
    }

    public void setP_type(double p_type) {
        this.p_type = p_type;
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

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }

    public String getPay_mehod() {
        return pay_mehod;
    }

    public void setPay_mehod(String pay_mehod) {
        this.pay_mehod = pay_mehod;
    }
    
    public String savedata(){
        Connection con= null;
        PreparedStatement ps=null;
        Statement stm=null;
        ResultSet rs=null;
        ResultSet rs1=null;
        //p_type, p_bag, p_rate, t_price, payment, remaining;
        double total=(p_bag*p_rate);
        System.out.println(total);
        try {
            con= database.Database.getConnection();//pa_id, p_date, p_type, p_bag, p_rate, t_price
            stm=con.createStatement();
            ps=con.prepareStatement("insert into parcec_t ( p_bag, p_rate, t_price) values(?,?,?) ");
            ps.setDouble(1, p_bag);
            ps.setDouble(2, p_rate);
            ps.setDouble(3, total);
            
            int count=0;
            count=ps.executeUpdate();
            if(count>0){
                
                System.out.println("done");
//                cleardata();
//                remining();
            
            }else{
                System.out.println("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    
    
    
    
    
}
