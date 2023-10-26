package org.itmo.ws.jaxrs.model;

import org.itmo.ws.jaxrs.utils.ConnectionUtil;

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

    public List<Car> getCarsByParams(String brand,
                                     String model,
                                     int year,
                                     String engineType,
                                     int perfomance) {
        List<Car> cars = new ArrayList<>();
        String query = constructSelectQuery(brand, model, year, engineType, perfomance);
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
                Car car = new Car(carbrand, carmodel, year, enginetype, perfomance);
                cars.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return cars;
    }


    public Long createNewCar(String brand, String model,
                             int year, String engineType,
                             int perfomance){
        String INSERT_SQL_QUERY="INSERT INTO cars (carbrand, carmodel, year,enginetype, perfomance) VALUES (?,?,?,?,?)";
        Long id = -1L;
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL_QUERY,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, engineType);
            preparedStatement.setInt(5, perfomance);
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

    public Boolean updateCar(Long id, String brand,
                             String model, int year,
                             String engineType, int perfomance){
        int updatedRows = -1;
        try(Connection connection = ConnectionUtil.getConnection()){
            String updateQuery = constructUpdateQuery(brand, model, year, engineType, perfomance);
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

    private String constructSelectQuery(String brand,
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

    private String constructUpdateQuery(String brand,
                                        String model,
                                        int year,
                                        String engineType,
                                        int perfomance){
        StringBuilder queryBuilder = new StringBuilder("UPDATE cars SET");
        List<String> setConditions = new ArrayList<>();
        if (brand != null) {
            setConditions.add("carbrand='" + brand + "'");
        }
        if (model != null) {
            setConditions.add("carmodel='" + model + "'");
        }
        if (engineType != null) {
            setConditions.add("enginetype='" + engineType + "'");
        }
        if (year != 0) {
            setConditions.add("year='" + year + "'");
        }
        if (perfomance != 0) {
            setConditions.add("perfomance='" + perfomance + "'");
        }
        if (!setConditions.isEmpty()) {
            queryBuilder.append(" ").append(String.join(", ", setConditions));
            return queryBuilder.toString();
        }
        return null;
    }
}
