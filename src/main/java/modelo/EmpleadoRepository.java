/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.cj.result.LocalDateTimeValueFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class EmpleadoRepository {

    private ConexionMariaDB con;
    private Connection conexion;

    public EmpleadoRepository() {
        con = new ConexionMariaDB();
    }

    public EmpleadoRepository(ConexionMariaDB con) {
        con = new ConexionMariaDB();
    }

    public void conectar() {
        conexion = con.getConecction();
        if (conexion == null) {
            System.out.println("Me he conectado");
        }
    }

    public ArrayList<Empleado> getEmpleados() {
        conectar();
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        Statement sentencia = null;
        try {
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM empleados;";
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()) {
                int id = resultado.getInt(1);
                String apellido = resultado.getString(2);
                String oficio = resultado.getString(3);
                int dir = resultado.getInt(4);
                Timestamp fecha_alta = resultado.getTimestamp(5);
                int salario = resultado.getInt(6);
                int comision = resultado.getInt(7);
                int dept_no = resultado.getInt(8);
                LocalDateTime fecha_alta_real = fecha_alta.toLocalDateTime();
                Empleado empleado = new Empleado(id, apellido, oficio, dir, fecha_alta_real, salario, comision, dept_no);
                empleados.add(empleado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
                desconectar();

            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return empleados;
    }

    public void desconectar() {
        con.desconectar(conexion);
    }

    public ArrayList<Empleado> salarioMinimo(int salarioMin) {
        conectar();
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        Statement sentencia = null;
        try {
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM empleados WHERE salario>" + salarioMin;
            ResultSet resultado = sentencia.executeQuery(sql);
            empleados = getAllEmpleados(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleados;
    }

    public ArrayList<Empleado> getAllEmpleados(ResultSet resultado) {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        try {
            while (resultado.next()) {
                int id = resultado.getInt(1);
                String apellido = resultado.getString(2);
                String oficio = resultado.getString(3);
                int dir = resultado.getInt(4);
                Timestamp fecha_alta = resultado.getTimestamp(5);
                int salario = resultado.getInt(6);
                int comision = resultado.getInt(7);
                int dept_no = resultado.getInt(8);
                LocalDateTime fecha_alta_real = fecha_alta.toLocalDateTime();
                Empleado empleado = new Empleado(id, apellido, oficio, dir, fecha_alta_real, salario, comision, dept_no);
                empleados.add(empleado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empleados;
    }

    public Empleado getEmpleadobyID(int id) {
        conectar();
        Empleado e = new Empleado();
        Statement sentencia = null;
        try {
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM empleados WHERE id=" + id;
            ResultSet result = sentencia.executeQuery(sql);
            ArrayList<Empleado> empleados = getAllEmpleados(result);
            e = empleados.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
                desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return e;
    }

    public int insertEmple(Empleado e) {
        conectar();
        Empleado e1 = e;
        Statement sentencia = null;
        int result = 0;
        try {
            sentencia = conexion.createStatement();
            Timestamp fecha = Timestamp.valueOf(e.getFecha_alta());
            String sql = "INSERT INTO empleados(apellido,oficio,dir,fecha_alta,salario,comision,dept_no) VALUES('" + e.getApellido()
                    + "','" + e.getOficio() + "', '" + e.getDirector() + "', '" + fecha + "', '" + e.getSalario() + "', '" + e.getComision()
                    + "', '" + e.getDept_no() + "')";
            result = sentencia.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
                desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public void incrementarSalario(int id, HashMap<String, String> condiciones) {
        conectar();  
        Statement sentencia = null;  
        try {
            String sql = "UPDATE empelados SET salario= salario+" + id + " WHERE";
            sentencia = conexion.createStatement();
            for (Map.Entry<String, String> entry : condiciones.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                sql += " "+key+"='"+val+"'"; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

//insertar empleado
    

//incrementar salario en 100 euros (o en cualwquier cantidad) de los empleados que eucmoppla ciertas condicionnes
