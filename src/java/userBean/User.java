/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author C3
 */
@ManagedBean
@SessionScoped
public class User {
    String uname, pass, fname,add, msg="", page="";

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void clearedata(){
        //uname, pass, fname,add,
        uname="";
        pass="";
        fname="";
        add="";
    
    }
    public  String  savedata(){
        Connection con=null;
        PreparedStatement stmt= null;
        ResultSet rs=null;
        try {
            con=database.Database.getConnection();//id, user_id, u_pass, u_name, u_add
            stmt=con.prepareStatement("insert into login_db (user_id, u_pass, u_name, u_add) values(?,?,?,?)");
            stmt.setString(1, uname);
            stmt.setString(2, pass);
            stmt.setString(3, fname);
            stmt.setString(4, add);
            int count=0;
            count=stmt.executeUpdate();
            if(count>0){
            msg="your registration seccessfull";
            clearedata();
            return "about";
            }else{
                msg="Registration faild";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     return null;
    }
    
    public  String  loginuser(){
        Connection con=null;
        PreparedStatement stmt= null;
        ResultSet rs=null;
        try {
            con=database.Database.getConnection();
            stmt=con.prepareStatement("select * from login where (user_id, user_pass) values(?,?)");
            stmt.setString(1, uname);
            stmt.setString(2, pass);
            
            int count=0;
            count=stmt.executeUpdate();
            if(count>0){
            msg="your login seccessfull";
                System.out.println("done");
            return "about";
            }else{
                msg="Registration faild";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     return null;
    }
    
    
    
}
