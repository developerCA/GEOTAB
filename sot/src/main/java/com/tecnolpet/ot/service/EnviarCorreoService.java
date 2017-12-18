package com.tecnolpet.ot.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.Seguimiento;
import com.tecnolpet.ot.model.Usuario;
import com.tecnolpet.ot.model.Vendedor;
import com.tecnolpet.ot.repository.NotaPedidoRepository;
import com.tecnolpet.ot.repository.UsuarioRepository;
import com.tecnolpet.ot.repository.VendedorRepository;

@Component
public class EnviarCorreoService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private NotaPedidoRepository notaPedidoRepository;

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Environment env;

	public EnviarCorreoService() {
	}

	public void send(String to, String cc, String subject, String body)
			throws MailAuthenticationException, MailSendException,
			MessagingException, UnsupportedEncodingException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true);
		if (null!=cc){
			helper.setCc(cc);	
		}
		
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setFrom("fast@tecnolpet.com");
		helper.setText(body, true);

		javaMailSender.send(message);

	}

	@Async
	@Transactional(propagation = Propagation.REQUIRED)
	public void armarPedidoRegistroHtml(NotaPedido notaPedido)
			throws MailAuthenticationException, MailSendException,
			UnsupportedEncodingException, MessagingException {
		Vendedor vendedor = vendedorRepository.findOne(notaPedido.getVendedor()
				.getId());
		
		Usuario usuario=usuarioRepository.findOne(notaPedido.getUsuarioRegistro());
		StringBuilder html = new StringBuilder();
		String subject = "Nueva Orden de Trabajo Generada OET # "
				+ notaPedido.getNumeroReferencia() + " OIT # "
				+ notaPedido.getOrdenInterna().toString();

		html.append("<html>");
		html.append("<head>");
		html.append("<title></title>");
		html.append("</head>");
		html.append("<body>");
		html.append(
				"<h2 style='font-style:italic;'><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Orden de Trabajo &nbsp;OET # <strong>")
				.append(notaPedido.getNumeroReferencia())
				.append("</strong>&nbsp;OIT # ").append(notaPedido.getOrdenInterna())
				.append("</span></span></h2>");

		html.append("<p><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Se asignado una orden de trabajo </span></span></p>");
		html.append("<p><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Servicio requerido para el cliente <strong>").append(notaPedido.getCliente().getNombresCompletos()).append(" </strong> </span></span></p>");

		html.append("<p>Revisar en su bandeja de ordenes de trabajo usando el sistema Fast Report &nbsp;System.</p>");

		html.append("<p>Gracias.</p>");
		html.append("<p>Fast RS</p>");

		html.append("<p>&nbsp;</p>");
		html.append("</body>");
		html.append("</html>");

		send(vendedor.getEmail(),usuario.getEmailUsuario(), subject, html.toString());

	}

	@Async
	@Transactional(propagation = Propagation.REQUIRED)
	public void armarPedidoGestionHtml(NotaPedido notaPedido)
			throws MailAuthenticationException, MailSendException,
			UnsupportedEncodingException, MessagingException {

		Usuario usuarioReg=usuarioRepository.findOne(notaPedido.getUsuarioRegistro());
		
		
		StringBuilder html = new StringBuilder();
		String subject = "Orden de Trabajo para Revisión # "
				+ notaPedido.getNumeroReferencia().toString();

		html.append("<html>");
		html.append("<head>");
		html.append("<title></title>");
		html.append("</head>");
		html.append("<body>");
		html.append(
				"<h2 style='font-style:italic;'><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Orden de Trabajo &nbsp; # <strong>")
				.append(notaPedido.getNumeroReferencia()).append("</strong>")
				.append("</span></span></h2>");

		html.append("<p><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Se envió la orden de trabajo a su bandeja, por favor revisar para su aprobación </span></span></p>");

		html.append("<p>Gracias.</p>");
		html.append("<p>Fast RS</p>");
		html.append("<p>&nbsp;</p>");
		html.append("</body>");
		html.append("</html>");

		List<Usuario> usuarios = usuarioRepository.findUsuariosByCliente(
				notaPedido.getEmpresa(), notaPedido.getCliente());

		for (Usuario usuario : usuarios) {
			send(usuario.getEmailUsuario(),usuarioReg.getEmailUsuario(), subject, html.toString());
		}

	}

	@Async
	@Transactional(propagation = Propagation.REQUIRED)
	public void armarPedidoAprobarHtml(NotaPedido notaPedido)
			throws MailAuthenticationException, MailSendException,
			UnsupportedEncodingException, MessagingException {

		StringBuilder html = new StringBuilder();
		String subject = "Orden de Trabajo Aprobada # OIT "
				+ notaPedido.getOrdenInterna().toString();
		Usuario usuario = usuarioRepository.findOne(notaPedido
				.getUsuarioRegistro());

		html.append("<html>");
		html.append("<head>");
		html.append("<title></title>");
		html.append("</head>");
		html.append("<body>");
		html.append(
				"<h2 style='font-style:italic;'><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Orden de Trabajo &nbsp; # <strong>")
				.append(notaPedido.getOrdenInterna()).append("</strong>")
				.append("</span></span></h2>");

		html.append(
				"<p><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Se aprobó la orden de trabajo, del cliente:")
				.append(notaPedido.getCliente().getNombresCompletos())
				.append("</span></span></p>");

		html.append("<p>Gracias.</p>");
		html.append("<p>Fast RS</p>");
		html.append("<p>&nbsp;</p>");
		html.append("</body>");
		html.append("</html>");

		send(usuario.getEmailUsuario(),null, subject, html.toString());

	}

	@Async
	@Transactional(propagation = Propagation.REQUIRED)
	public void armarEnvioClaveHtml(String clave, String nombre,
			String username, String email) throws MailAuthenticationException,
			MailSendException, UnsupportedEncodingException, MessagingException {

		StringBuilder html = new StringBuilder();
		String subject = "Fast Resport System - Acceso al Sistema ";

		html.append("<html>");
		html.append("<head>");
		html.append("<title></title>");
		html.append("</head>");
		html.append("<body>");
		html.append(
				"<h2 style='font-style:italic;'><font face='verdana, geneva, sans-serif'><span style='font-size: 14px;'>Bienvenido ")
				.append(nombre).append("</span></font></h2>");

		html.append("<p>Hola, te damos la m&aacute;s cordial bienvenida al Sistema Fast Report Sytem &nbsp;en L&iacute;nea de Tecnolpet. Te sugerimos que realices tu cambio de clave.</p>");

		html.append("<p>&nbsp;</p>");

		html.append("<p>Usuario: ").append(username).append("</p>");

		html.append("<p>Clave:").append(clave).append("</p>");

		html.append("<p>&nbsp;</p>");
		html.append("</body>");
		html.append("</html>");

		send(email, null,subject, html.toString());

	}

	@Async
	@Transactional(propagation = Propagation.REQUIRED)
	public void armarEnvioResetClaveHtml(String clave, String nombre,
			String username, String email) throws MailAuthenticationException,
			MailSendException, UnsupportedEncodingException, MessagingException {

		StringBuilder html = new StringBuilder();
		String subject = "Fast Resport System - Cambio de Clave ";

		html.append("<html>");
		html.append("<head>");
		html.append("<title></title>");
		html.append("</head>");
		html.append("<body>");
		html.append(
				"<h2 style='font-style:italic;'><font face='verdana, geneva, sans-serif'><span style='font-size: 14px;'>Bienvenido ")
				.append(nombre).append("</span></font></h2>");

		html.append("<p>Hola, te damos la m&aacute;s cordial bienvenida al Sistema Fast Report Sytem &nbsp;en L&iacute;nea de Tecnolpet. Su clave se ha cambiado correctamente.</p>");

		html.append("<p>&nbsp;</p>");

		html.append("<p>Usuario: ").append(username).append("</p>");

		html.append("<p>Clave:").append(clave).append("</p>");

		html.append("<p>&nbsp;</p>");
		html.append("</body>");
		html.append("</html>");

		send(email, null,subject, html.toString());

	}
	
	@Async
	public void notificarSeguimiento(Seguimiento seguimiento) throws MailAuthenticationException, MailSendException, UnsupportedEncodingException, MessagingException{
		
		NotaPedido notaPedido=seguimiento.getTareaDetalleNotaPedido().getDetalleNotaPedido().getNotaPedido();
Usuario usuarioReg=usuarioRepository.findOne(notaPedido.getUsuarioRegistro());
		
		
		StringBuilder html = new StringBuilder();
		String subject = "Avance de la Orden de Trabajo # "
				+ notaPedido.getNumeroReferencia().toString();

		html.append("<html>");
		html.append("<head>");
		html.append("<title></title>");
		html.append("</head>");
		html.append("<body>");
		html.append(
				"<h2 style='font-style:italic;'><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Orden de Trabajo &nbsp; # <strong>")
				.append(notaPedido.getNumeroReferencia()).append("</strong>")
				.append("</span></span></h2>");

		
		html.append("<p><span style='font-size:14px;'><span style='font-family:verdana,geneva,sans-serif;'>Se ha registrado un avance en el equipo ");
		html.append(seguimiento.getTareaDetalleNotaPedido().getDetalleNotaPedido().getProducto().getNombre());
		html.append(" con serie:").append(seguimiento.getTareaDetalleNotaPedido().getDetalleNotaPedido().getProducto().getSerie()).append("</span></span></p>");
		
				
		html.append("<p>Gracias.</p>");
		html.append("<p>Fast RS</p>");
		html.append("<p>&nbsp;</p>");
		html.append("</body>");
		html.append("</html>");

		List<Usuario> usuarios = usuarioRepository.findUsuariosByCliente(
				notaPedido.getEmpresa(), notaPedido.getCliente());

		for (Usuario usuario : usuarios) {
			send(usuario.getEmailUsuario(),usuarioReg.getEmailUsuario(), subject, html.toString());
		}

	}

}
