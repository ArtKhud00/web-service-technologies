package org.itmo.ws.jaxrs.Model;

import org.itmo.ws.jaxrs.utils.ConnectionUtil;

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

    public List<Car> getCarsByParams(String brand,
                                     String model,
                                     int year,
                                     String engineType,
                                     int perfomance) {
        List<Car> cars = new ArrayList<>();
        String query = constructQuery(brand, model, year, engineType, perfomance);
        System.out.println("Query: "+query);
        try(Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String carbrand = rs.getString("carbrand");
                String carmodel = rs.getString("carmodel");
                int car_year = rs.getInt("year");
                String enginetype = rs.getString("enginetype");
                int car_perfomance = rs.getInt("perfomance");
                Car car = new Car(carbrand, carmodel, car_year, enginetype, car_perfomance);
                cars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return cars;
    }


    private String constructQuery(String brand,
                                  String model,
                                  int year,
                                  String engineType,
                                  int perfomance){
        StringBuilder query = new StringBuilder("select * from cars where 1=1");
        if (brand != null) {
            query.append(" and carbrand='").append(brand).append("'");
        }
        if (model != null) {
            query.append(" and carmodel='").append(model).append("'");
        }
        if (engineType != null) {
            query.append(" and enginetype='").append(engineType).append("'");
        }
        if (year != 0) {
            query.append(" and year='").append(year).append("'");
        }
        if (perfomance != 0) {
            query.append(" and perfomance='").append(perfomance).append("'");
        }
        return query.toString();
    }
}
