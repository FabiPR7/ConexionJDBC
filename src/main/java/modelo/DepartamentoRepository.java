/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class DepartamentoRepository {

    private ConexionMariaDB con;
    private Connection conexion;

    public DepartamentoRepository() {
        con = new ConexionMariaDB();
    }

    public void conectar() {
        if (conexion == null) {
            conexion = con.getConecction();

            if (conexion != null) {
                System.out.println("me he conectado");
            } else {
                System.out.println("no me he conectado");
            }
        }
    }

    public String getInfo() {
        String info = "info";
        DatabaseMetaData bdInfo = null;
        try {
            bdInfo = conexion.getMetaData();
            String nombre = bdInfo.getDatabaseProductName();
            String driver = bdInfo.getDriverName();
            String url = bdInfo.getURL();
            String user = bdInfo.getUserName();
            String informacionBD = String.format(
                    "Información de la Base de Datos:\n"
                    + "--------------------------------\n"
                    + "Nombre de la BD   : %s\n"
                    + "Driver            : %s\n"
                    + "URL               : %s\n"
                    + "Usuario           : %s\n"
                    + "Máx. Conexiones   : %d\n",
                    bdInfo.getDatabaseProductName(),
                    bdInfo.getDriverName(),
                    bdInfo.getURL(),
                    bdInfo.getUserName(),
                    bdInfo.getMaxConnections()
            );
            info = informacionBD;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Conexion nulla o incorrecta");
        }

        return info;
    }

    public String getInfoTable() {
        conectar();
        String info = "";
        DatabaseMetaData bdInfo = null;
        ResultSet result = null;

        try {
            bdInfo = conexion.getMetaData();
            result = bdInfo.getTables(null, null, "departamentos", null);

            //recorremos el resultSet
            while (result.next()) {
                String catalogo = result.getString(1);
                String esquema = result.getString(2);
                String tabla = result.getString(3);
                String tipo = result.getString(4);
                info += "catalogo " + catalogo + " esquema " + esquema + " tabla " + tabla + " tipo " + tipo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        return info;
    }

    public String getInfoColumns() {
        String info = "";
        conectar();
        DatabaseMetaData bdInfo = null;
        ResultSet result = null;

        try {
            bdInfo = conexion.getMetaData();
            result = bdInfo.getColumns(null, null, "departamentos", null);
            while (result.next()) {
                String nombre = result.getString("COLUMN_NAME");
                String nula = result.getString("IS_NULLABLE");
//                String tabla = result.getString(3);
//                String tipo = result.getString(4);
                info += "Nombre: " + nombre + "     ¿es nulo?: " + nula + " \n";
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        return info;

    }

    public String GetPrimaryKeys() {
        String info = "";
        DatabaseMetaData bdInfo = null;
        ResultSet result = null;
        try {
            bdInfo = conexion.getMetaData();
            // Recupera las claves primarias de la tabla "departamentos"
            result = bdInfo.getPrimaryKeys(null, null, "departamentos");
            while (result.next()) {
                String nombreClave = result.getString("COLUMN_NAME");
                String nombrePK = result.getString("PK_NAME");
                info += "Columna: " + nombreClave + "     Nombre de PK: " + nombrePK + " \n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return info;
    }

    public String GetForeignKeys() {
        String info = "";
        DatabaseMetaData bdInfo = null;
        ResultSet result = null;
        try {
            bdInfo = conexion.getMetaData();
            // Recupera las claves foráneas de la tabla "departamentos"
            result = bdInfo.getImportedKeys(null, null, "departamentos");
            while (result.next()) {
                String fkColumnName = result.getString("FKCOLUMN_NAME");
                String pkColumnName = result.getString("PKCOLUMN_NAME");
                String pkTableName = result.getString("PKTABLE_NAME");
                info += "Columna FK: " + fkColumnName + "     Columna PK: " + pkColumnName + "     Tabla PK: " + pkTableName + " \n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return info;
    }

    public ArrayList<Departamento> getDepartamentos8() {
        conectar();
        ArrayList<Departamento> departamentos = new ArrayList<>();
        Statement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM departamentos";
            boolean ej = sentencia.execute(sql);
            if (ej) {
                resultado = sentencia.getResultSet();
                while (resultado.next()) {
                    int id = resultado.getInt(1); // es lo mismo que getInt("id");
                    String nombre = resultado.getString(2); // es lo mismo que getString("dnombre");
                    String localidad = resultado.getString(3); // es lo mismo que getString("loc");
                    Departamento departamento = new Departamento(id, nombre, localidad);
                    departamentos.add(departamento);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
                resultado.close();

            } catch (SQLException ex) {
                Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        desconectar();
        return departamentos;

    }

    public void desconectar() {
        con.desconectar(conexion);
    }

    public ArrayList<Departamento> getdepartamentosLocaliadda(String localidad) {
        conectar();
        ArrayList<Departamento> departamentos = new ArrayList<>();
        Statement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM departamentos WHERE loc ='" + localidad + "'";
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                String local = resultado.getString(3); // es lo mismo que getString("loc");
                if (local.equals(localidad)) {
                    String nombre = resultado.getString(2); // es lo mismo que getString("dnombre");
                    int id = resultado.getInt(1); // es lo mismo que getInt("id");
                    Departamento departamento = new Departamento(id, nombre, local);
                    departamentos.add(departamento);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
                if (resultado != null) {
                    resultado.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            desconectar();
        }

        return departamentos;
    }

    public String comprobar(ArrayList<Departamento> dep) {
        String mensaje = "";
        if (dep.size() == 0) {
            mensaje += "NO HAY NA";
        }
        return mensaje;
    }

    //INSERTA DATOS
    public int insertar(Departamento d) {
        conectar();
        int num = 0;
        Statement sentencia = null;
        try {
            sentencia = conexion.createStatement();
            String sql = "INSERT INTO departamentos(dnombre, loc) VALUES(" + "'" + d.getNombre() + "', '" + d.getLocalidad() + "')";
            num = sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            desconectar();
        }
        return num;
    }

    //MODIFICA DATOS DE nombre sistemas pot localidad pamplona
    /*
     *Caso de pasar dos departamentos;
    -Departamento("Sistemas")
    -Departamento("Sistemas","Pamplona")
    
    *Caso de modfiicxar nombre y localidad por id
        -id
    - Departamento(nombre,localidad)
    
    *Caso de no pasar objetos Departamento:
        -se lo quiero cambiar a los que tienen por dnombre "SISTEMAS"
        -quiero Cambiar loc por "PAMPLONA"
        - con Hasmap
    
    
    Modifcio el nombre y la localiad por id 
     */
    public int modifyById(int id, Departamento dNuevo) {
        conectar();
        int num = 0;
        Statement sentencia = null;
        try {
            sentencia = conexion.createStatement();
            String sql = "UPDATE departamentos SET dnombre='" + dNuevo.getNombre() + "', loc='" + dNuevo.getLocalidad() + "' WHERE id=" + id;
            num = sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
                desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return num;
    }

    //modifica cualqueuier propiedad de un departamento con cualquier condicion 
    public int modificar(HashMap<String, String> valoresNuevos, HashMap<String, String> condiciones) {
        conectar();
        int num = 0;
        Statement sentencia = null;
//        HashMap<String,String> valores = new HashMap<>();
//        valores.put("dnombre", "informatica");
//        valores.put("loc", "Logroño");
        try {
            String sql = "UPDATE departamentos SET ";
            sentencia = conexion.createStatement();
            String sqlRenovado = "";
            String sqlRenovado2 = ""; 
//            for (String clave : valoresNuevos.keySet()) {
//                System.out.println("Clave:" + clave + " Valor: " + valoresNuevos.get(clave));
//                valores += clave + "='" + valoresNuevos.get(clave) + "',";
//
//            }
            for (Map.Entry<String, String> entry : valoresNuevos.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                sql += key + "='" + val + "', ";
            }
            for (int i = 0; i < sql.length() - 2; i++) {
                sqlRenovado += sql.charAt(i);
            }
            sqlRenovado += " WHERE ";
            for (Map.Entry<String, String> entry : condiciones.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                sqlRenovado += key + "='" + val + "' AND ";
            }
            for (int i = 0; i < sqlRenovado.length() - 5; i++) {
                sqlRenovado2 += sqlRenovado.charAt(i);
            }
            System.out.println(sqlRenovado2);
            num = sentencia.executeUpdate(sqlRenovado2);
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sentencia.close();
                desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(DepartamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return num;
    }
    //metodo que devuelva dpto por id

    //DatabaseMetaData
    //Statement
    //PrepareStatement
}
