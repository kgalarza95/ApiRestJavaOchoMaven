/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.galarza.kevin.apirestjavaocho.interfaces;

/**
 *
 * @author kgalarza
 */
public interface IDefaultMetodo {

    default void sumar(int num1, int num2) {
        System.out.println("la suma es " + (num1 + num2));
    }

    default void resta(int num1, int num2) {
        System.out.println("la resta es " + (num1 + num2));
    }

    public void defResultado(String operacion, int num1, int num2);
}
