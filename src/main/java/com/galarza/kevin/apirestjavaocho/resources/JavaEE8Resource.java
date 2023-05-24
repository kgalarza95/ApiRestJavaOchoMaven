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
import pck_entidades.ClsParametrosMail;
import pck_mail.ClsEnvioMailFull;
import pck_seguridad.Seguridad;

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
        JsonObject jsonObject = null;
        try {

            String clave = "KEVIN";
            String calveEn = Seguridad.encrypt("Litoral2023.", clave);
            String calveDes = Seguridad.decrypt("YvmPircxIDq1GGJLgrFO7bFSsMNeG9wq", clave);

            log.info("calveEn " + calveEn);
            log.info("calveDes " + calveDes);
            log.info("=======================================================");
            ClsEnvioMailFull mail = new ClsEnvioMailFull();
            ClsParametrosMail parametros = new ClsParametrosMail();
            parametros.setID("2");
            parametros.setDESCRIPCION("Notificaciones Internas");
            parametros.setSERVIDOR("smtp.office365.com");
            parametros.setPUERTO("587");
            parametros.setUSUARIO("notificacionesinternas@bancodellitoral.com");
            parametros.setTOKEN("Will1990@");
//            parametros.setTOKEN(clave);
            parametros.setPASS("f/NzfR0eiLupf6LBMG5m01cE+nQqUP1TR8cPXE8hJ58PKOz3Xhk1XHeb2k9IlvsOVaFiB/UMtM8=");
//            parametros.setPASS("YvmPircxIDq1GGJLgrFO7bFSsMNeG9wq");
            parametros.setESTADO("A");
            parametros.setTLS("true");
            parametros.setSSL("true");
            parametros.setAUTH("true");
            parametros.setPROTOCOLO("smtp");
            parametros.setETIQUETA("Banco del Litoral");
            parametros.setPROTOCOLO_SSL("TLSv1.2");

            log.info(parametros.toString());
            log.info(parametros.getPASS());
            boolean sendMail = mail.CorreoNormal("kgalarza@bancodellitoral.com",
                    "", "prueba maven", "hola desde maven", "N",
                    "", "", parametros);

            log.info("-------------INIT----------------");
            ServicesApiFecha apiFecha = new ServicesApiFecha();
            log.info("Api de fecha");
            log.info("Fecha actual: " + apiFecha.getFechaActual());
            log.info("Fecha Hora actual: " + apiFecha.getFechaHoraActual());
            log.info("Día de la semana de hoy: " + apiFecha.getDiaSemanaActual());
            log.info("-------------FIN------------------");
            jsonObject = new JsonObject();
            jsonObject.addProperty("fechaActual", apiFecha.getFechaActual());
            jsonObject.addProperty("fechaHoraActual", apiFecha.getFechaHoraActual());
            jsonObject.addProperty("diaSemanaActual", apiFecha.getDiaSemanaActual());
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
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
