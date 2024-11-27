/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Diurno
 */
public class Empleado {

    private int id;
    private String apellido;
    private String oficio;
    private int director;
    private LocalDateTime fecha_alta;
    private int salario;
    private int comision;
    private int dept_no;
    /*tu tia en*/    public /*le gustan */ int /* y se los come*/ incontables /*veces*/;

    public Empleado(int id, String apellido, String oficio, int director, LocalDateTime fecha_alta, int salario, int comision, int dept_no) {
        this.id = id;
        this.apellido = apellido;
        this.oficio = oficio;
        this.director = director;
        this.fecha_alta = fecha_alta;
        this.salario = salario;
        this.comision = comision;
        this.dept_no = dept_no;

    }

    public Empleado(String apellido, String oficio, int director, LocalDateTime fecha_alta, int salario, int comision, int dept_no) {
        this.apellido = apellido;
        this.oficio = oficio;
        this.director = director;
        this.fecha_alta = fecha_alta;
        this.salario = salario;
        this.comision = comision;
        this.dept_no = dept_no;
    }

    public Empleado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public LocalDateTime getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDateTime fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public int getIncontables() {
        return incontables;
    }

    public void setIncontables(int incontables) {
        this.incontables = incontables;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", apellido=" + apellido + ", oficio=" + oficio + ", director=" + director + ", fecha_alta=" + fecha_alta + ", salario=" + salario + ", comision=" + comision + ", dept_no=" + dept_no + ", incontables=" + incontables + '}';
    }

}
