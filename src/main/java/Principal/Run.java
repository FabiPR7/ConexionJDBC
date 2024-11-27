/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import controlador.Controller;
import modelo.DepartamentoRepository;
import modelo.EmpleadoRepository;
import vista.Vista;

/**
 *
 * @author Diurno
 */
public class Run {
    public static void main(String[] args) {
        DepartamentoRepository dr = new DepartamentoRepository();
        Vista v = new Vista();
        EmpleadoRepository er = new EmpleadoRepository();
        Controller c = new Controller(dr,v,er);
    }
}
