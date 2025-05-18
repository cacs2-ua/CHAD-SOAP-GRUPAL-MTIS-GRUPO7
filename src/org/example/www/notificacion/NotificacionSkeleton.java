package org.example.www.notificacion;
import utils.Utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import exception.WSKeyNoValidaException;

public class NotificacionSkeleton {

    /**
     * Auto generated method signature
     * 
     * @param enviar 
     * @return enviarResponse 
     * @throws MessagingException  
     * @throws WSKeyNoValidaException 
     */
    public org.example.www.notificacion.EnviarResponse enviar(
            org.example.www.notificacion.Enviar enviar
    ) throws MessagingException, WSKeyNoValidaException {
        
        EnviarResponse response = new EnviarResponse();

        String WSKey = enviar.getWSKey();
        Utils.verificarWSKey(WSKey);

        String from = enviar.getDatosEntrada().getFrom();
        String to = enviar.getDatosEntrada().getTo();
        String subject = enviar.getDatosEntrada().getSubject();
        String text = enviar.getDatosEntrada().getText();

        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");

        Session session = Session.getInstance(props);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            response.setMensajeSalida("Email enviado correctamente desde: '" + from + "' a '" + to + "'");

        } catch (MessagingException  e) {
            e.printStackTrace();
            throw e;
        }

        return response;
    }
}
