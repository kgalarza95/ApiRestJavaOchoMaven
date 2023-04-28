/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.galarza.kevin.apirestjavaocho.util;

import com.galarza.kevin.apirestjavaocho.interfaces.ILog;

/**
 *
 * @author kgalarza
 */
public class ConsoleLog implements ILog {

    @Override
    public void debug(String mensaje) {
        System.out.println("[DEBUG] " + mensaje);
    }

    @Override
    public void info(String mensaje) {
        System.out.println("[INFO] " + mensaje);
    }

    @Override
    public void warn(String mensaje) {
        System.out.println("[WARN] " + mensaje);
    }

    @Override
    public void error(String mensaje) {
        System.out.println("[ERROR] " + mensaje);
    }
}
