package org.acme.builder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import jakarta.enterprise.context.ApplicationScoped;

import org.acme.bean.Respuesta;
import org.acme.bean.Respuesta2;
import org.acme.processor.GetInfoProcessor;
import org.acme.repository.PersonaRepository;

import jakarta.inject.Inject;

@ApplicationScoped
public class GetInfoRouteBuilder extends RouteBuilder {

   private JacksonDataFormat formatRpta = new JacksonDataFormat(Respuesta.class);
   private JacksonDataFormat formatRpta2 = new JacksonDataFormat(Respuesta2.class);
 
    @ConfigProperty(name = "app.camel.rest.route.distribute")
    private String route;

    @ConfigProperty(name = "app.camel.rest.host.get-info")
    private String host_get_info;

    @ConfigProperty(name = "app.camel.rest.port.get-info")
    private int port_get_info;

    @Inject
    PersonaRepository personaRepository;

    @Override
    public void configure() throws Exception {

        restConfiguration()
            .component("platform-http")
            .host(host_get_info)
            .port(port_get_info);

       
        rest("/receptor")
            .post("/mensaje")
            .to("direct:procesarMensaje");

        from("direct:procesarMensaje")
             .log("Received a message - ${body} - sending to processed")
             .unmarshal(formatRpta2)
            .process(new GetInfoProcessor(personaRepository))
            .marshal(formatRpta)
            .to(route);
           

    }

}
