/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;



/**
 *
 * @author aktar
 */
@ManagedBean
public class Clientinfo {
    
    String  c_name, c_add, msg="", selectedname ; 
    int c_mobi,client_id;

    int cb_id;
    java.util.Date p_date;
    double p_type, p_bag, p_rate, t_price, payment, remaining;
    String pay_mehod;

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getCb_id() {
        return cb_id;
    }

    public void setCb_id(int cb_id) {
        this.cb_id = cb_id;
    }

    public java.util.Date getP_date() {
        return p_date;
    }

    public void setP_date(java.util.Date p_date) {
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
    
    
    
    
    

    public String getMsg() {
        return msg;
    }

    public String getSelectedname() {
        return selectedname;
    }

    public void setSelectedname(String selectedname) {
        this.selectedname = selectedname;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

   

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_add() {
        return c_add;
    }

    public void setC_add(String c_add) {
        this.c_add = c_add;
    }

    public int getC_mobi() {
        return c_mobi;
    }

    public void setC_mobi(int c_mobi) {
        this.c_mobi = c_mobi;
    }
    
    
    public void saveclientinfo(){
        Connection con=null;
        PreparedStatement ps;
        ResultSet rs=null;
        int count=0;
        try {
            con=Database.getConnection();
            ps=con.prepareStatement("insert into client_info ( c_name, c_add, c_mobi) values (?,?,?)");
            ps.setString(1, c_name);
            ps.setString(2, c_add);
            ps.setInt(3, c_mobi);
            count=ps.executeUpdate();
            if(count>0){
                msg="your submit sucessfull";
                cleareall();
            }else{
             msg="your submit faild";
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    }
    
    public void cleareall(){
     c_name="";
     c_add="";
     msg="" ;
     c_mobi=0;
    
    
    }
    
    public List<SelectItem> getNameList() {
        List<SelectItem> retVal = new ArrayList<SelectItem>();

        try {
            Connection con = Database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            String myQuery = "select c_name FROM client_info";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                retVal.add(new SelectItem(rs.getString("c_name")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return retVal;
    }
    
    
 public void buttonAction(ActionEvent actionEvent) {
        addMessage("submit successfull");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }    


    public void clientidcheck(){
    Connection con= null;
        PreparedStatement ps=null;
        Statement stm=null;
        ResultSet rs=null;
        String count=null;
        try {
            String sname=selectedname;
            con= database.Database.getConnection();//pa_id, p_date, p_type, p_bag, p_rate, t_price
            //stm=con.createStatement();
            ps=con.prepareStatement("select client_id from client_info where c_name=?");
            ps.setString(1, sname);
            
            rs= ps.executeQuery();
            
            if(rs.next()){
                System.out.println(rs.getInt(client_id));
                
                System.out.println("selectedname is "+selectedname);
                System.out.println("client_id is "+client_id);
            }else{System.out.println("error");}
        } catch (Exception e) {
            e.printStackTrace();
        }
    
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
            clientidcheck();
            con= database.Database.getConnection();//pa_id, p_date, p_type, p_bag, p_rate, t_price
            stm=con.createStatement();
            ps=con.prepareStatement("insert into client_balance ( client_id,p_bag, p_rate, t_price) values(?,?,?,?) ");
            ps.setDouble(1, client_id);
            ps.setDouble(2, p_bag);
            ps.setDouble(3, p_rate);
            ps.setDouble(4, total);
            
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
