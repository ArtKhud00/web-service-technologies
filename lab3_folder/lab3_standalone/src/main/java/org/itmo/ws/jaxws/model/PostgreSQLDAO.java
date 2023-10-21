package org.itmo.ws.jaxws.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.itmo.ws.jaxws.exception.CarServiceFault;
import org.itmo.ws.jaxws.exception.ServiceException;
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
        //System.out.println("Query: "+query);
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

    public Long createNewCar(Car car) throws  ServiceException{
        String INSERT_SQL_QUERY="INSERT INTO cars (carbrand, carmodel, year,enginetype, perfomance) VALUES (?,?,?,?,?)";
        Long id = -1L;
        if(!car.isValid()){
            CarServiceFault fault = CarServiceFault.defaultInstance("Values are not provided for all fields");
            throw new ServiceException("Values are not provided for all fields", fault);
        }
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
            CarServiceFault fault = CarServiceFault.defaultInstance("Unable to add new record in table");
            throw new ServiceException("Unable to add new record in table", fault);
        }
        return -1L;

    }

    public Boolean deleteCar(Long id) throws ServiceException{
        String DELETE_SQL_QUERY = "DELETE FROM cars WHERE id = ?";
        int deletedRows = -1;
        if(id == null || id <= 0){
            CarServiceFault fault = CarServiceFault.defaultInstance("Provided incorrect id");
            throw new ServiceException("Provided incorrect id", fault);
        }
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL_QUERY);
            preparedStatement.setLong(1,id);
            deletedRows = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            CarServiceFault fault = CarServiceFault.defaultInstance("Unable to delete specified record");
            throw new ServiceException("Unable to delete specified record", fault);
        }
        if(deletedRows == 0) {
            CarServiceFault fault = CarServiceFault.defaultInstance("Nothing was deleted");
            throw new ServiceException("Nothing was deleted", fault);
        }
        return deletedRows > 0;
    }

    public Boolean updateCar(Long id, Car car) throws ServiceException{
        int updatedRows = -1;
        if(id == null || id <= 0){
            CarServiceFault fault = CarServiceFault.defaultInstance("Provided incorrect id");
            throw new ServiceException("Provided incorrect id", fault);
        }

        try(Connection connection = ConnectionUtil.getConnection()){
            String updateQuery = constructUpdateQuery(car);
            if(updateQuery == null){
                CarServiceFault fault = CarServiceFault.defaultInstance("Nothing provided to update");
                throw new ServiceException("Nothing provided to update", fault);
            }
            updateQuery+="WHERE id=" + id;
            Statement stmt = connection.createStatement();
            updatedRows = stmt.executeUpdate(updateQuery);

        }catch (SQLException ex){
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            CarServiceFault fault = CarServiceFault.defaultInstance("Unable to update specified record");
            throw new ServiceException("Unable to update specified record", fault);
        }
        if(updatedRows == 0){
            CarServiceFault fault = CarServiceFault.defaultInstance("Nothing was updated");
            throw new ServiceException("Nothing was updated", fault);
        }
        return updatedRows > 0;
    }

    private String constructSelectQuery(Car carparameters){
        StringBuilder query = new StringBuilder("select * from cars where 1=1");
        if (carparameters.getCarbrand() != null) {
            query.append(" and carbrand='").append(carparameters.getCarbrand()).append("'");
        }
        if (carparameters.getCarmodel() != null) {
            query.append(" and carmodel='").append(carparameters.getCarmodel()).append("'");
        }
        if (carparameters.getEnginetype() != null) {
            query.append(" and enginetype='").append(carparameters.getEnginetype()).append("'");
        }
        if (carparameters.getYear() != 0) {
            query.append(" and year='").append(carparameters.getYear()).append("'");
        }
        if (carparameters.getPerfomance() != 0) {
            query.append(" and perfomance='").append(carparameters.getPerfomance()).append("'");
        }
        return query.toString();
    }

    private String constructUpdateQuery(Car carparameters){
        StringBuilder queryBuilder = new StringBuilder("UPDATE cars SET");
        List<String> setConditions = new ArrayList<>();
        if (carparameters.getCarbrand() != null) {
            setConditions.add("carbrand='" + carparameters.getCarbrand() + "'");
        }
        if (carparameters.getCarmodel() != null) {
            setConditions.add("carmodel='" + carparameters.getCarmodel() + "'");
        }
        if (carparameters.getEnginetype() != null) {
            setConditions.add("enginetype='" + carparameters.getEnginetype() + "'");
        }
        if (carparameters.getYear() != 0) {
            setConditions.add("year='" + carparameters.getYear() + "'");
        }
        if (carparameters.getPerfomance() != 0) {
            setConditions.add("perfomance='" + carparameters.getPerfomance() + "'");
        }
        if (!setConditions.isEmpty()) {
            queryBuilder.append(" ").append(String.join(", ", setConditions));
            return queryBuilder.toString();
        }
        return null;
    }
}
