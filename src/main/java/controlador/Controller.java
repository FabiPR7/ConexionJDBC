/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.Departamento;
import modelo.DepartamentoRepository;
import modelo.Empleado;
import modelo.EmpleadoRepository;
import vista.Vista;

/**
 *
 * @author Diurno
 */
public class Controller {

    private DepartamentoRepository dr;
    private Vista v;
    private EmpleadoRepository er;

    public Controller(DepartamentoRepository dr, Vista vi, EmpleadoRepository err) {
        this.dr = dr;
        this.v = vi;
        this.er = err;
        //        mostrarInfoTable();
//        mostrarInfo();
//        mostrarInfoColumn();
//        mostrarInfoColumnPrimaryKeys();
//        mostrarInfoColumnForeignKey();
//        getDepartamentos();
//        getDepartamanetosLocalidad();
        //   getEmpleados();
      //  updateById();
 //     updateporto();
    insertarEmpleado();
 
    }

    public void mostrarInfo() {
        v.mostrarTexto(dr.getInfo());
    }

    public void mostrarInfoTable() {
        v.mostrarTexto(dr.getInfoTable());
    }

    public void mostrarInfoColumn() {
        v.mostrarTexto(dr.getInfoColumns());
    }

    public void mostrarInfoColumnPrimaryKeys() {
        v.mostrarTexto(dr.GetPrimaryKeys());
    }

    public void mostrarInfoColumnForeignKey() {
        v.mostrarTexto(dr.GetForeignKeys());
    }

    public void getDepartamentos() {
        ArrayList<Departamento> departamentos = dr.getDepartamentos8();
        for (Departamento departamento : departamentos) {
            v.mostrarTexto(departamento.toString());
        }
    }

    public void getDepartamanetosLocalidad() {
        ArrayList<Departamento> departamentos = dr.getdepartamentosLocaliadda("Madrid");
        for (Departamento departamento : departamentos) {
            v.mostrarTexto(departamento.toString());
        }
    }

    public void getEmpleados() {
        ArrayList<Empleado> empleados = er.getEmpleados();
        for (Empleado empleado : empleados) {
            v.mostrarTexto(empleado.toString());
        }
    }

    public void insertar() {
        Departamento d = new Departamento("ADMINISTRACION", "LOGROÑO");
        v.mostrarTexto(String.valueOf("Se ha añadido nuevas " + dr.insertar(d)) + " fila(s)");
    }

    public void updateById() {
        Departamento d = new Departamento("INFORMATICA", "pamplona");
        int filas = dr.modifyById(1, d);
        v.mostrarTexto("Se ha mostrado "+filas+" correctamente");
    }
    public void updateporto(){
        HashMap<String,String> valoresNuevos = new HashMap<>();
        HashMap<String,String> condiciones = new HashMap<>();
        valoresNuevos.put("dnombre", "INFORMATICO");
        valoresNuevos.put("loc", "MADRID");
        condiciones.put("loc", "pamplona");
        condiciones.put("dnombre", "INFORMATICA"); 
       int num = dr.modificar(valoresNuevos, condiciones);
       v.mostrarTexto("MODIFICADOS "+num+ " FILAS");
    }
    
    public void insertarEmpleado(){
        LocalDateTime fecha =LocalDateTime.now();
        Empleado e = new Empleado("Raiz","Limpieza",1,fecha,1000,100,2);
     int a =  er.insertEmple(e);
     v.mostrarTexto("Se ha actualizado "+a+" filas");
    
    }
}
