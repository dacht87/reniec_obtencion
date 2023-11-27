package org.acme.builder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import jakarta.enterprise.context.ApplicationScoped;

import org.acme.bean.Respuesta;
import org.acme.bean.RespuestaToken;
import org.acme.processor.GetInfoProcessor;
import org.acme.repository.PersonaRepository;

import jakarta.inject.Inject;

@ApplicationScoped
public class GetInfoRouteBuilder extends RouteBuilder {

   private JacksonDataFormat formatRpta = new JacksonDataFormat(Respuesta.class);
   private JacksonDataFormat formatRptaToken = new JacksonDataFormat(RespuestaToken.class);
   //private BindyFixedLengthDataFormat camelDataFormat = new BindyFixedLengthDataFormat(Header.class);

     @ConfigProperty(name = "app.jms.queue-validated")
    private String queue_in;

    @ConfigProperty(name = "app.jms.queue-processed")
    private String queue_out;

    @Inject
    PersonaRepository personaRepository;

    @Inject
    @ConfigProperty(name = "smallrye.jwt.sign.key.location")
    String privateKeyLocation; // Ruta al archivo privateKey.pem

    @Override
    public void configure() throws Exception {

        //JsonWebToken jwt = parser.verify(jwtCookie, secret);

        System.out.println("=====inicia marshall");  

        from(String.format("jms:queue:%s",queue_in))
            .log("Received a message - ${body} - sending to processed")
            .unmarshal(formatRptaToken)
            .process(new GetInfoProcessor(personaRepository,privateKeyLocation))
            .marshal(formatRpta)
        .to(String.format("jms:queue:%s",queue_out));
    }

}
