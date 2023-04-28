/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.galarza.kevin.apirestjavaocho.services;

import com.galarza.kevin.apirestjavaocho.dto.Response;
import com.galarza.kevin.apirestjavaocho.interfaces.ICalculoSueldo;
import com.galarza.kevin.apirestjavaocho.interfaces.IDefaultMetodo;
import com.galarza.kevin.apirestjavaocho.interfaces.IServicesNovedades;
import com.galarza.kevin.apirestjavaocho.repository.RepositoryNovedades;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author kgalarza
 */
public class ServicesNovedades implements IServicesNovedades, IDefaultMetodo {

    private RepositoryNovedades repo;

    public ServicesNovedades() {
        repo = new RepositoryNovedades();
    }

    @Override
    public String getRepoData() {
        Optional<Response> respuesta = repo.getDataRepoData();
        Response response = null;
        String json;
        if (respuesta.isPresent()) {
            response = respuesta.get();
            System.out.println("respuesta " + response);
            json = "ok";
        } else {
            json = "error";
            //manejo cuando no existe
            System.out.println("Null manejado");
        }
        return json;
    }

    @Override
    public String getRepoNoData() {
        Optional<Response> respuesta = repo.getDataRepoNoData();
        Response response = null;
        String json;
        if (respuesta.isPresent()) {
            response = respuesta.get();
            System.out.println("respuesta " + response);
            json = "ok";
        } else {
            json = "error controlado";
            //manejo cuando no existe
            System.out.println("Null manejado");
        }
        return json;
    }

    //no forma parte de java 8
    @Deprecated
    public void getDemoProgramacionReactiva() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> flujoNumeros = Stream.of(numeros.toArray(new Integer[0]));
        Stream<Integer> flujoPares = flujoNumeros.filter(numero -> numero % 2 == 0);
        int[] numerosParesMultiplicados = flujoPares.mapToInt(numero -> numero * 2).toArray();

        //metodo de referencia
        Arrays.stream(numerosParesMultiplicados).forEach(System.out::println);
    }

    ICalculoSueldo operacion = (sueldo, desceunto) -> (sueldo - desceunto);

    public double calcular(double a, double b) {
        return operacion.calcular(a, b);
    }

    public void getUseStream() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numeros.stream()
                .filter(n -> n > 4)
                .forEach(System.out::println);

    }

    @Override
    public void defResultado(String operacion, int num1, int num2) {
        switch (operacion) {
            case "S":
                sumar(num1, num2);
                break;
            case "R":
                resta(num1, num2);
                break;
            default:
                throw new AssertionError();
        }
    }

    public void useNashorn() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        try {
            engine.eval("print('Hola, mundo con js desde Java!')");
            engine.eval("print('Oracle Nashorn se mantuvo hasta Java 14, después de lo cual se eliminó de JDK. ')");
            engine.eval("print('Nashorn se introdujo originalmente en JDK 8 como un reemplazo del motor de scripting Rhino.')");
            engine.eval("print('Debido al rápido ritmo de cambio en los constructos del lenguaje ECMAScript, Oracle encontró que Nashorn era difícil de mantener y lo eliminó en versiones posteriores de JDK')");
            engine.eval("print('')");
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }

    public CompletableFuture<String> CompletableFuture() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> accionDemoraEjecutar());
        return future;
    }

    public void getInfLicenciaJdk17() {
        System.out.println("Java SE 17 es distribuido bajo los términos y condiciones de la licencia \"Oracle No-Fee Terms and Conditions\" (NFTC) que permite su uso gratuito, incluyendo su uso en producción, durante tres años a partir de su lanzamiento en septiembre de 2021.");
        System.out.println("Después de ese período, se requerirá una licencia comercial para su uso en producción. La documentación de información de licencia de Oracle Java SE 17 proporciona detalles adicionales sobre los términos y condiciones de la licencia, así como información sobre las licencias de terceros y las limitaciones de uso");
        System.out.println("");
    }

    private String accionDemoraEjecutar() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return "Operación terminada";
    }

}
