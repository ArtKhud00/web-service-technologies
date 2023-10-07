package org.itmo.ws.jaxws.model;

import org.itmo.ws.jaxws.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        try(Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cars");
            while (rs.next()) {
                String carbrand = rs.getString("carbrand");
                String carmodel = rs.getString("carmodel");
                int year = rs.getInt("year");
                String enginetype = rs.getString("enginetype");
                int perfomance = rs.getInt("perfomance");
                Car car = new Car(carbrand, carmodel, year, enginetype, perfomance);
                cars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return cars;
    }

    public List<Car> getCarsByParams(Car carparameters) {
        List<Car> cars = new ArrayList<>();
        String query = constructQuery(carparameters);
        System.out.println("Query: "+query);
        try(Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String carbrand = rs.getString("carbrand");
                String carmodel = rs.getString("carmodel");
                int year = rs.getInt("year");
                String enginetype = rs.getString("enginetype");
                int perfomance = rs.getInt("perfomance");
                Car car = new Car(carbrand, carmodel, year, enginetype, perfomance);
                cars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return cars;
    }


    private String constructQuery(Car carparameters){
        String query = "select * from cars";
        boolean moreThanOneCondition = false;
        if(carparameters.getCarbrand()!=null){
            if(moreThanOneCondition){query+=" or";}
            else{
                query+=" where";
            }
            query += " carbrand='"+carparameters.getCarbrand()+"'";
            moreThanOneCondition = true;
        }
        if(carparameters.getCarmodel()!=null){
            if(moreThanOneCondition){query+=" or";}
            else{
                query+=" where";
            }
            query += " carmodel='"+carparameters.getCarmodel()+"'";
            moreThanOneCondition = true;
        }
        if(carparameters.getEnginetype()!=null){
            if(moreThanOneCondition){query+=" or";}
            else{
                query+=" where";
            }
            query += " enginetype='"+carparameters.getEnginetype()+"'";
            moreThanOneCondition = true;
        }
        if(carparameters.getYear()!=-1){
            if(moreThanOneCondition){query+=" or";}
            else{
                query+=" where";
            }
            query += " year='"+carparameters.getYear()+"'";
            moreThanOneCondition = true;
        }
        if(carparameters.getPerfomance()!=-1){
            if(moreThanOneCondition){query+=" or";}
            else{
                query+=" where";
            }
            query += " perfomance='"+carparameters.getPerfomance()+"'";
            moreThanOneCondition = true;
        }
        return query;
    }


}
