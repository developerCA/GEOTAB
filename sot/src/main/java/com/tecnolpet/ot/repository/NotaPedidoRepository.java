/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.NotaPedido;

/**
 * @author administrador
 *
 */
public interface NotaPedidoRepository extends JpaRepository<NotaPedido, Integer>{
	
	@Query("SELECT p FROM NotaPedido p WHERE p.idCatalogo.id = :registrado or p.idCatalogo.id = :revisado or p.idCatalogo.id = :modificado ORDER BY p.id DESC")
	List<NotaPedido> buscarLasNotas(@Param("registrado") Integer registrado, @Param("revisado") Integer revisado, @Param("modificado") Integer modificado);
	
	@Query("SELECT p FROM NotaPedido p WHERE p.empresa = :empresa and p.idCatalogo.id = :registrado or p.idCatalogo.id = :revisado or p.idCatalogo.id = :reversado ORDER BY p.id DESC")
	List<NotaPedido> findNotasRegistradasAndRevisadas(@Param("empresa") Empresa empresa, @Param("registrado") Integer registrado, @Param("revisado") Integer revisado, @Param("reversado") Integer reversado);
	
	@Query("SELECT p FROM NotaPedido p WHERE p.empresa = :empresa and p.fechaApertura>= :fechaDesde and p.fechaApertura<= :fechaHasta and p.cliente = :cliente ORDER BY p.id DESC")
	List<NotaPedido> findNotasPorCliente(@Param("empresa") Empresa empresa, @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta,@Param("cliente") Cliente cliente);
	
	@Query("SELECT p FROM NotaPedido p WHERE p.empresa = :empresa and p.fechaApertura>= :fechaDesde and p.fechaApertura<= :fechaHasta  ORDER BY p.id DESC")
	List<NotaPedido> findNotasPorCliente(@Param("empresa") Empresa empresa, @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);
	
	@Query("SELECT p FROM NotaPedido p WHERE p.empresa = :empresa and p.fechaHoraAprobacion>= :fechaDesde and p.fechaHoraAprobacion<= :fechaHasta and p.cliente = :cliente ORDER BY p.id DESC")
	List<NotaPedido> findNotasPorClienteCierre(@Param("empresa") Empresa empresa, @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta,@Param("cliente") Cliente cliente);
	
	@Query("SELECT p FROM NotaPedido p WHERE p.empresa = :empresa and p.fechaHoraAprobacion>= :fechaDesde and p.fechaHoraAprobacion<= :fechaHasta  ORDER BY p.id DESC")
	List<NotaPedido> findNotasPorClienteCierre(@Param("empresa") Empresa empresa, @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);
	

	@Query("SELECT p FROM NotaPedido p WHERE p.idCatalogo.id = :registrado and p.empresa = :empresa ORDER BY p.id DESC")
	List<NotaPedido> findNotasRegistradas(@Param("registrado") Integer registrado, @Param("empresa") Empresa empresa);	
	
	@Query("SELECT p  FROM NotaPedido p WHERE p.idCatalogo.id = :aprobada AND p.empresa = :empresa And p.cliente = :cliente")
	List<NotaPedido> findNotasAprobadasSuscripcion(@Param("aprobada") Integer aprobada, @Param("empresa") Empresa empresa,@Param("cliente") Cliente cliente);
	
	List<NotaPedido> findNotaPedidoByEstado(Catalogo estado);
	
	@Query("SELECT np FROM NotaPedido np WHERE np.empresa = :empresa AND np.idCatalogo = :catalogo AND np.prefactura = false")
	List<NotaPedido> findByIdCatalogoAndPrefacturaFalse(@Param("empresa") Empresa empresa, @Param("catalogo") Catalogo idCatalogo);
		
	@Query("SELECT np FROM NotaPedido np WHERE np.idCatalogo = :estado AND np.id = :id")
	List<NotaPedido> findNotaPedidoByEstadoAndId(@Param("estado") Catalogo estado, @Param("id") Integer id);

	@Query("SELECT np FROM NotaPedido np WHERE np.idCatalogo.id in (3) and np.empresa= :empresa")
	List<NotaPedido> findOrdenes(@Param("empresa") Empresa empresa);
	
	@Query("SELECT np FROM NotaPedido np WHERE np.idCatalogo.id in (3) and np.empresa= :empresa and np.cliente= :cliente")
	List<NotaPedido> findOrdenes(@Param("empresa") Empresa empresa,@Param("cliente") Cliente ciente);

	
	@Query(value="select (select count(*) from nota_pedido where codigo_empresa= :empresa and id_catalogo in (3,12,4)) totalorden , (select count(*) poraprobar from nota_pedido where codigo_empresa= :empresa and id_catalogo in (3)) poraprobar, (select count(*) cerradas  from nota_pedido where codigo_empresa= :empresa and id_catalogo in (12)) cerradas,(select count(*) abiertas from nota_pedido where codigo_empresa= :empresa and id_catalogo in (4,3)) abiertas,(select sum(dp.cantidad) equipos from nota_pedido np, detalle_nota_pedido dp  where np.codigo=dp.codigo_nota_pedido and np.codigo_empresa= :empresa and dp.id_catalogo<> 5) equipos, (select sum(dp.cantidad) servicios from nota_pedido np, detalle_nota_pedido dp,tarea_detalle_nota_pedido t where np.codigo=dp.codigo_nota_pedido and  dp.codigo=t.codigo_detalle_nota_pedido and np.codigo_empresa= :empresa and dp.id_catalogo<> 5) servicios",nativeQuery=true)
	List<Object> findDashBoard(@Param("empresa") Integer empresa);
	
	@Query(value="select (select count(*) from nota_pedido where codigo_empresa= :empresa and codigo_cliente= :cliente and id_catalogo in (3,12,4)) totalorden , (select count(*) poraprobar from nota_pedido where codigo_empresa= :empresa and codigo_cliente= :cliente and id_catalogo in (3)) poraprobar, (select count(*) cerradas  from nota_pedido where codigo_empresa= :empresa and codigo_cliente= :cliente and id_catalogo in (12)) cerradas,(select count(*) abiertas from nota_pedido where codigo_empresa= :empresa and codigo_cliente= :cliente and id_catalogo in (4,3)) abiertas,(select sum( dp.cantidad) equipos from nota_pedido np, detalle_nota_pedido dp  where np.codigo=dp.codigo_nota_pedido and np.codigo_empresa= :empresa and np.codigo_cliente= :cliente and dp.id_catalogo<> 5) equipos, (select sum(dp.cantidad) servicios from nota_pedido np, detalle_nota_pedido dp,tarea_detalle_nota_pedido t where np.codigo=dp.codigo_nota_pedido and  dp.codigo=t.codigo_detalle_nota_pedido and np.codigo_empresa= :empresa and np.codigo_cliente= :cliente and dp.id_catalogo<> 5) servicios",nativeQuery=true)
    List<Object> findDashBoard(@Param("empresa") Integer empresa,@Param("cliente") Integer cliente);
	
	@Query(value="select distinct  EXTRACT(MONTH FROM  fecha_apertura),to_char(fecha_apertura, 'Month')from nota_pedido where codigo_empresa= :empresa and id_catalogo <> 5 and EXTRACT(year FROM  fecha_apertura)= :anio and codigo_empresa= :empresa order by EXTRACT(MONTH FROM  fecha_apertura)" ,nativeQuery=true)
	List<Object> findTicks(@Param("empresa") Integer empresa,@Param("anio") Integer anio);
	
	@Query(value="select EXTRACT(MONTH FROM  fecha_apertura) ,count(*) from nota_pedido where  id_catalogo <> 5 and EXTRACT(year FROM  fecha_apertura)= :anio and codigo_empresa= :empresa group by  EXTRACT(MONTH FROM  fecha_apertura) order by  EXTRACT(MONTH FROM  fecha_apertura)",nativeQuery=true)
	List<Object> findDataTicks(@Param("empresa") Integer empresa,@Param("anio") Integer anio);
	
	@Query(value="select EXTRACT(MONTH FROM  fecha_apertura) ,count(*) from nota_pedido where id_catalogo <> 5 and EXTRACT(year FROM  fecha_apertura)= :anio and codigo_empresa= :empresa and codigo_cliente= :cliente group by  EXTRACT(MONTH FROM  fecha_apertura) order by  EXTRACT(MONTH FROM  fecha_apertura)",nativeQuery=true)
	List<Object> findDataTicks(@Param("empresa") Integer empresa,@Param("anio") Integer anio,@Param("cliente") Integer cliente);
	
	@Query(value="select EXTRACT(MONTH FROM  np.fecha_apertura) ,sum(dp.cantidad) from nota_pedido np ,detalle_nota_pedido dp where np.codigo=dp.codigo_nota_pedido and  np.id_catalogo <> 5 and dp.id_catalogo<> 5 and EXTRACT(year FROM  np.fecha_apertura)= :anio and np.codigo_empresa= :empresa group by  EXTRACT(MONTH FROM  np.fecha_apertura) order by  EXTRACT(MONTH FROM  np.fecha_apertura)",nativeQuery=true)
    List<Object> findDataTicksEquipos(@Param("empresa") Integer empresa,@Param("anio") Integer anio);

	@Query(value="select EXTRACT(MONTH FROM  np.fecha_apertura) ,sum(dp.cantidad) from nota_pedido np ,detalle_nota_pedido dp where np.codigo=dp.codigo_nota_pedido and  np.id_catalogo <> 5 and dp.id_catalogo<> 5 and EXTRACT(year FROM  np.fecha_apertura)= :anio and np.codigo_empresa= :empresa and np.codigo_cliente= :cliente group by  EXTRACT(MONTH FROM  np.fecha_apertura) order by  EXTRACT(MONTH FROM  np.fecha_apertura)",nativeQuery=true)
    List<Object> findDataTicksEquipos(@Param("empresa") Integer empresa,@Param("anio") Integer anio,@Param("cliente") Integer cliente);

	@Query(value="select EXTRACT(MONTH FROM  np.fecha_apertura) ,sum(dp.cantidad) from nota_pedido np ,detalle_nota_pedido dp,tarea_detalle_nota_pedido tdp where np.codigo=dp.codigo_nota_pedido and   dp.codigo=tdp.codigo_detalle_nota_pedido and  np.id_catalogo <> 5 and dp.id_catalogo<> 5 and EXTRACT(year FROM  np.fecha_apertura)= :anio and np.codigo_empresa= :empresa group by  EXTRACT(MONTH FROM  np.fecha_apertura) order by  EXTRACT(MONTH FROM  np.fecha_apertura)",nativeQuery=true)
	List<Object> findDataTicksServicios(@Param("empresa") Integer empresa,@Param("anio") Integer anio);
	
	@Query(value="select EXTRACT(MONTH FROM  np.fecha_apertura) ,sum(dp.cantidad) from nota_pedido np ,detalle_nota_pedido dp,tarea_detalle_nota_pedido tdp where np.codigo=dp.codigo_nota_pedido and   dp.codigo=tdp.codigo_detalle_nota_pedido and  np.id_catalogo <> 5 and dp.id_catalogo<> 5 and EXTRACT(year FROM  np.fecha_apertura)= :anio and np.codigo_empresa= :empresa and np.codigo_cliente= :cliente group by  EXTRACT(MONTH FROM  np.fecha_apertura) order by  EXTRACT(MONTH FROM  np.fecha_apertura)",nativeQuery=true)
	List<Object> findDataTicksServicios(@Param("empresa") Integer empresa,@Param("anio") Integer anio,@Param("cliente") Integer cliente);

	

}
