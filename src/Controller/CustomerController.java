/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBconnection.DBconnection;
import Model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sachithra
 */
public class CustomerController {
    
   public static ArrayList<Member> getallCustomer() throws ClassNotFoundException, SQLException{
       String sql="select *from member";
            Connection connection=DBconnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst=pstm.executeQuery(sql);
             ArrayList<Member> allmembers=new ArrayList<>();
             while(rst.next()){
                 Member member=new Member();
                 member.setId(rst.getString(1));
                 member.setName(rst.getString(2));
                 member.setHome(rst.getString(3));
                 member.setPhone(rst.getInt(4));
                 
                 allmembers.add(member);
             }
             return allmembers;
   }
   public static boolean addCustomer(Member member) throws ClassNotFoundException, SQLException{
         String sql="insert into member values(?,?,?,?)";
        Connection connection=DBconnection.getInstance().getConnection();
            PreparedStatement prm=connection.prepareStatement(sql);
             prm.setObject(1, member.getId());
             prm.setObject(2, member.getName());
             prm.setObject(3, member.getHome());
             prm.setObject(4, member.getPhone());
             int n=prm.executeUpdate();
             if(n>0){
                 return true;
            }
             else{
              
                  return false;
             }
   }
   public static Member searchMember(String name) throws ClassNotFoundException, SQLException{
        Connection connection=DBconnection.getInstance().getConnection();
         Statement statement=connection.createStatement();
         String sql="select * from member where name='"+name+"'";
         ResultSet rst=statement.executeQuery(sql);
         rst.next();
         Member member=new Member(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4));
         return member;
   }
   public static boolean deletemember(String name) throws ClassNotFoundException, SQLException{
        Connection connection=DBconnection.getInstance().getConnection();
        String sql="DELETE FROM member WHERE name=?";
        PreparedStatement ptm=connection.prepareStatement(sql);
        ptm.setObject(1,name);
         int a=ptm.executeUpdate();
        if(a>0){
            return true;
        }
        else{
            return false;
        }
   }
   public static boolean updatemember(Member member) throws ClassNotFoundException, SQLException{
       Connection connection=DBconnection.getInstance().getConnection();
       String sql="UPDATE member SET id=?,home=?, phone=? WHERE id=?";
       PreparedStatement ptm=connection.prepareStatement(sql);
       ptm.setObject(1,member.getId());
       ptm.setObject(2,member.getHome());
       ptm.setObject(3,member.getPhone());
       ptm.setObject(4,member.getId());
       int vb=ptm.executeUpdate();
       if(vb>0){
           return true;
   }
       else{
           return false;
       }
       }
}
