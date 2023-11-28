
package org.acme.processor;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.acme.bean.Respuesta;
import org.acme.bean.Respuesta2;
import org.acme.repository.PersonaRepository;
import org.acme.model.PersonaModel;

import java.util.List;


public class GetInfoProcessor implements Processor{

    PersonaRepository personaRepository;
    
        
    public GetInfoProcessor(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
 
    }

    public void process(Exchange exchange) throws Exception {

        System.out.println("=====GET INFOR PROCESOR");  

        //Header query = exchange.getIn().getBody(Header.class);
        Respuesta2 query = exchange.getIn().getBody(Respuesta2.class);

        System.out.println(query.tipoConsulta);
        //System.out.println(validateToken(query.token));
        System.out.println("=====SUBTRAMA ");  

        System.out.println(query.tipoSubConsulta);

        PersonaModel model=null;

        if(query.tipoConsulta.equalsIgnoreCase("2"))
        {

            System.out.println("=====GET INFOR PROCESOR111111"); 
            //System.out.println("=====GET INFOR PROCESOR111111 Token:" + query.token); 

            List<PersonaModel> lista=personaRepository.findByTipoYNumeroDocumento("DNI",query.nroDNI);

            System.out.println("=====GET INFOR PROCESOR22222: "+lista.size());  

            if(lista.size()>0)
                model=lista.get(0);
        }

        exchange.getIn().setBody(new Respuesta(model==null?"persona no encontrada" : query.getTramaHeader() + model.npersona_id+"-"+model.npersona_idtipdoc+"-"+model.cpersona_nrodoc+"-"+model.cpersona_apepaterno+"-"+model.cpersona_apematerno+"-"+model.cpersona_nombres+"-"+model.dpersona_fnacimiento+"-"+model.cpersona_codsexo));
    }


//     private boolean validateToken(String token) throws ParseException {
//         try {
//             // Cargar la clave privada RSA desde un archivo PEM
//             RSAPrivateKey privateKey = loadPrivateKeyFromPem("private_key.pem"); // Reemplaza con la ruta correcta a tu archivo privateKey.pem

//             //JwtClaimsBuilder claims = Jwt.verify(token, privateKey);

//             // Crear un objeto SmallRyeJwtParser con la clave privada
//             // JwtParserBuilder parserBuilder = JwtParserBuilder.newBuilder();
//             // parserBuilder.signatureKeyId("1"); // Puedes ajustar esto según tu configuración
//             // parserBuilder.privateKey(privateKey);

//             //JsonWebToken callerPrincipal = parser.verify(token, privateKey); //parse(token, privateKey);

//             // Parsear y verificar el token JWT
//             //JWTCallerPrincipal callerPrincipal = parserBuilder.build().parse(token);

//             // Realizar otras validaciones según tus necesidades
//             // Por ejemplo, verificar reclamaciones específicas del token

//             // Si llegamos aquí sin excepciones, el token es válido
//             return true;
// //        } catch (ParseException | IOException e) {
//         } catch (IOException e) {
//             // Manejar la excepción (por ejemplo, loggearla)
//             e.printStackTrace();
//             return false;
//         }
//     }

    // private RSAPrivateKey loadPrivateKeyFromPem(String pemFilePath) throws IOException {

    //     System.out.println("=====GET INFOR PROCESORx1");  

    //     InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pemFilePath);

    //     System.out.println("=====GET INFOR PROCESORx2");  

    //     //byte[] keyBytes = Files.readAllBytes(Paths.get(pemFilePath));
    //     byte[] keyBytes = inputStream.readAllBytes();

    //     System.out.println("=====GET INFOR PROCESORx3");  
    //     System.out.println(keyBytes);  

    //     // Eliminar los encabezados y pies del archivo PEM
    //     String privateKeyPEM = new String(keyBytes)
    //             .replace("-----BEGIN PRIVATE KEY-----", "")
    //             .replace("-----END PRIVATE KEY-----", "")
    //             //.replace("\n", "")
    //             .replaceAll("\\s", "")
    //             ;

    //     System.out.println("=====GET INFOR PROCESORx4");  
    //     System.out.println(privateKeyPEM.length());  

    //     // Decodificar la clave privada
    //     byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);

    //     System.out.println("=====GET INFOR PROCESORx5");  

    //     try {

    //         System.out.println("=====GET INFOR PROCESORx6");  
    //         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    //         System.out.println("=====GET INFOR PROCESORx7");  
    //         PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
    //         System.out.println("=====GET INFOR PROCESORx8");  
    //         return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    //         //return (SecretKey) keyFactory.generatePrivate(keySpec);
    //     } catch (Exception e) {
    //         throw new RuntimeException("Error al cargar la clave privada desde el archivo PEM", e);
    //     }
    // }

}