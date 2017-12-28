package com.tecnolpet.ot.constant;

public class SotApp {

	public final static Integer APP_ID = 1;
	public final static Integer CLIENTE= 3;

	public static class GeoTab{
		public static final String SERVER_NAME="my49.geotab.com";
		public static final String DATABASE="mobility"; 
		public static final String USUARIO="raul.mediavilla@mobility.com.ec";
		public static final String CLAVE="adri1979";
		
		public static final String NIVEL_INICIAL_GRUPO="GroupCompanyId";
		public static final Integer NIVEL_COOPERATIVA=4;
		public static final Integer NIVEL_RUTA=5;
		
		
		
	}
	public static class AlgoritmoClaves {
		public static final String GENERACLAVE = "012345678abcdefghijklm9ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	}

	public static class EstadosTipoAcceso {
		public static final String NORMAL = "ACCNOR";
		public static final String OBSEQUIO = "ACCOBS";
		public static final String RANGO = "ACCRAN";
	}
	public static class EstadosFechasRenovacion {
		public static final String REGISTRADA = "FECREG";
		public static final String ACTIVADA = "FECACT";
		public static final String CANCELADA = "FECCAN";
		public static final String FINALIZADA = "FECFIN";
		
		
	}

	public static class EstadosAcceso {
		public static final String GENERADO = "ACCGEN";
		public static final String PROCESADO = "ACCPRO";
		public static final String ENVIADO = "ACCENV";
		public static final String ACTIVADO_TEMPORAL = "ACCTMP";
		public static final String DESACTIVADO = "ACCDES";
	}

	public static class EstadosPedido {

		public static final String APROBADO = "APROBA";
		public static final String REGISTRADO = "REGIST";
		public static final String CANCELADO = "CANCEL";
		public static final String MODIFICADO = "MODIF";
		public static final String REVISADO = "REVPED";
		public static final String GENERADA = "GENSUS";
		public static final String ENVIADA = "ENVKOH";
		public static final String RECIBIDA = "RECKOH";
		public static final String REVERSADO = "REVNOT";
		

	}

	public static class EstadosGenerales {

		public static final String ACTIVO = "ACTIVO";
		public static final String ELIMINADO = "ELIMIN";
	}

	public static class EstadosSuscripciones {

		public static final String LATENTE = "SUSLAT";
		public static final String ACTIVA = "SUSACT";
		public static final String CANCELADA = "SUSCAN";
		public static final String APROBADA = "SUSAPR";
		public static final String GENERADA = "GENSUS";
		public static final String RENOVADA = "SUSREN";
		public static final String DESACTIVADA = "SUSDES";
		public static final String ACTIVADEFINITIVA = "SUSACT";
	}

	public static class TipoOperacion {

		public static final Integer NUEVASUSCRIPCION = 1;
		public static final Integer RENOVACION = 2;
		
	}
	
	public static class EstadosRenovaciones {
		public static final String RENOVACION_PROCESADA ="RENPRO";
		public static final String RENOVACION_CANCELADA ="RENCAN";
		public static final String RENOVACION_APROBADA ="RENAPR";
	}
	
	public static class EstadosAviso {
		public static final String PROCESADO ="AVIPRO";
		public static final String RECIBIDO ="AVIREC";
	}
	
	public static class EstadosDistribucion {
		public static final String REGISTRADA ="DISREG";
		public static final String ASIGNADA ="DISASG";
		public static final String RENOVADA ="DISREN";
		public static final String NO_RENOVADA ="DISNRE";
		
	}
	
	public static class EstadosActualizacionProductos {
		public static final String GENEREADA ="ACTGEN";
		public static final String BAJA ="ACTBAJ";
		public static final String ENTREGADA ="ACTENT";
	}
	
	
	
	public static class SecuenciasSot{
		public static final String ORDENES ="Ordenes";
		public static final String SERVICIOS ="Servicios";
		
	}
	
}
