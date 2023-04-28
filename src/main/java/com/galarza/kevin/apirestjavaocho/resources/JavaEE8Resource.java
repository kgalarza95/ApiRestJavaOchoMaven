package com.galarza.kevin.apirestjavaocho.resources;

import com.galarza.kevin.apirestjavaocho.interfaces.IDefaultMetodo;
import com.galarza.kevin.apirestjavaocho.interfaces.ILog;
import com.galarza.kevin.apirestjavaocho.interfaces.IMetodoStatic;
import com.galarza.kevin.apirestjavaocho.interfaces.IServicesNovedades;
import com.galarza.kevin.apirestjavaocho.interfaces.IStatictInf;
import com.galarza.kevin.apirestjavaocho.services.ServicesApiFecha;
import com.galarza.kevin.apirestjavaocho.services.ServicesNovedades;
import com.galarza.kevin.apirestjavaocho.util.ConsoleLog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kgalarza
 */
@Path("javaee8")
public class JavaEE8Resource {

    private Gson gson = new Gson();
    private ILog log = new ConsoleLog();
    private ServicesNovedades serviceNo = new ServicesNovedades();

    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }

    @GET
    @Path("api_fecha")
    @Produces(MediaType.APPLICATION_JSON)
    public String getApiFecha() {
        log.info("-------------INIT----------------");
        ServicesApiFecha apiFecha = new ServicesApiFecha();
        log.info("Api de fecha");
        log.info("Fecha actual: " + apiFecha.getFechaActual());
        log.info("Fecha Hora actual: " + apiFecha.getFechaHoraActual());
        log.info("Día de la semana de hoy: " + apiFecha.getDiaSemanaActual());
        log.info("-------------FIN------------------");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fechaActual", apiFecha.getFechaActual());
        jsonObject.addProperty("fechaHoraActual", apiFecha.getFechaHoraActual());
        jsonObject.addProperty("diaSemanaActual", apiFecha.getDiaSemanaActual());

        return gson.toJson(jsonObject);
    }

    @GET
    @Path("inf_optional")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOptional() {
        log.info("-------------INIT----------------");
        JsonObject jsonObject = new JsonObject();
        IServicesNovedades serviceNo2 = new ServicesNovedades();
        jsonObject.addProperty("data", serviceNo2.getRepoData());
        jsonObject.addProperty("no_data", serviceNo2.getRepoNoData());
        log.info("-------------FIN------------------");
        return gson.toJson(jsonObject);
    }

    @GET
    @Path("inf_nashorn")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNashorn() {
        log.info("-------------INIT----------------");
        serviceNo.useNashorn();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Nashorn", "Ver Consola de log");
        log.info("-------------FIN------------------");
        return gson.toJson(jsonObject);
    }

    @GET
    @Path("inf_programacion_reactiva")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProgramacionReactiva() {
        log.info("-------------INIT----------------");
        serviceNo.getDemoProgramacionReactiva();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("reactiva", "Ver Consola de log");
        log.info("-------------FIN------------------");
        return gson.toJson(jsonObject);
    }

    @GET
    @Path("inf_i_functional")
    @Produces(MediaType.APPLICATION_JSON)
    public String getIFunctional() {
        log.info("-------------INIT----------------");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("interface_func", serviceNo.calcular(550, 15));
        log.info("-------------FIN------------------");
        return gson.toJson(jsonObject);
    }

    @GET
    @Path("inf_stream")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStream() {
        log.info("-------------INIT----------------");
        serviceNo.getUseStream();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stream", "Ver Consola de log");
        log.info("-------------FIN------------------");
        return gson.toJson(jsonObject);
    }

    @GET
    @Path("inf_future")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFuture() throws InterruptedException, ExecutionException {
        log.info("-------------INIT----------------");
        CompletableFuture<String> future = serviceNo.CompletableFuture();
        System.out.println("Continuando con otras tareas...");
        String resultado = future.get();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", resultado);
        log.info("-------------FIN------------------");
        return gson.toJson(jsonObject);
    }

    @GET
    @Path("inf_default_method")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDefaultMethod() {
        log.info("-------------INIT----------------");
        IDefaultMetodo service = new ServicesNovedades();
        service.defResultado("S", 10, 15);
        service.defResultado("R", 20, 15);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("default_method", "Ver Consola de log");
        log.info("-------------FIN------------------");
        return gson.toJson(jsonObject);
    }

    @GET
    @Path("inf_static_method")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStaticMethod() {
        log.info("-------------INIT----------------");
        IMetodoStatic.getInfLicenciaJdk17();
        IStatictInf.getInfWildflyVesrion();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("static_method", "Ver Consola de log");
        log.info("-------------FIN------------------");
        return gson.toJson(jsonObject);
    }
}
