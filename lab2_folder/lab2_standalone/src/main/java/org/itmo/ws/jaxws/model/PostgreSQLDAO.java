package org.itmo.ws.jaxws.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.itmo.ws.jaxws.utils.ConnectionUtil;

import java.sql.*;
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
        String query = constructSelectQuery(carparameters);
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

    public Long createNewCar(Car car){
        String INSERT_SQL_QUERY="INSERT INTO cars (carbrand, carmodel, year,enginetype, perfomance) VALUES (?,?,?,?,?)";
        Long id = -1L;
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL_QUERY,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,car.getCarbrand());
            preparedStatement.setString(2, car.getCarmodel());
            preparedStatement.setInt(3,car.getYear());
            preparedStatement.setString(4, car.getEnginetype());
            preparedStatement.setInt(5,car.getPerfomance());
            if(preparedStatement.executeUpdate() > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                    return id;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1L;

    }

    public Boolean deleteCar(Long id) {
        String DELETE_SQL_QUERY = "DELETE FROM cars WHERE id = ?";
        int deletedRows = -1;
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL_QUERY);
            preparedStatement.setLong(1,id);
            deletedRows = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deletedRows > 0;
    }

    public Boolean updateCar(Long id, Car car){
        int updatedRows = -1;
        try(Connection connection = ConnectionUtil.getConnection()){
            String updateQuery = constructUpdateQuery(car);
            if(updateQuery == null){
                return false;
            }
            updateQuery+="WHERE id=" + id;
            Statement stmt = connection.createStatement();
            updatedRows = stmt.executeUpdate(updateQuery);

        }catch (SQLException ex){
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updatedRows > 0;
    }

    private String constructSelectQuery(Car carparameters){
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

    private String constructUpdateQuery(Car carparameters) {
        String query = "UPDATE cars SET";
        boolean moreThanOneCondition = false;
        if(carparameters.getCarbrand()!=null){
            if(moreThanOneCondition){query+=" ,";}

            query += " carbrand='"+carparameters.getCarbrand()+"'";
            moreThanOneCondition = true;
        }
        if(carparameters.getCarmodel()!=null){
            if(moreThanOneCondition){query+=" ,";}

            query += " carmodel='"+carparameters.getCarmodel()+"'";
            moreThanOneCondition = true;
        }
        if(carparameters.getEnginetype()!=null){
            if(moreThanOneCondition){query+=" ,";}

            query += " enginetype='"+carparameters.getEnginetype()+"'";
            moreThanOneCondition = true;
        }
        if(carparameters.getYear()!=-1){
            if(moreThanOneCondition){query+=" ,";}

            query += " year='"+carparameters.getYear()+"'";
            moreThanOneCondition = true;
        }
        if(carparameters.getPerfomance()!=-1){
            if(moreThanOneCondition){query+=" ,";}

            query += " perfomance='"+carparameters.getPerfomance()+"'";
            moreThanOneCondition = true;
        }
        if(moreThanOneCondition){
            return query;
        }
        return null;
    }

}
