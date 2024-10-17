package controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("HerramentalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List Usuarios() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usu_c_usuarios`()");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (consulta.isEmpty()) {
                return null;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List Usuario_sesion(String usa, String pwd) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL sp_usu_login('" + usa + "','" + pwd + "')");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (consulta.isEmpty()) {
                return null;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public int obtenerIntentos(String p_user) {
        EntityManager etm = getEntityManager();
        try {
            Query q = etm.createNativeQuery("CALL sp_a_obtener_intentos_usuario('" + p_user + "')");
            q.setParameter(1, p_user);
            Object result = q.getSingleResult();
            if (result != null) {
                return ((Number) result).intValue();
            } else {
                return -1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            etm.close();
        }
    }
    
    public Integer ObtenerIdUsuarioPorUser(String user) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        Integer idUsuario = null;
        try {
            Query query = etm.createNativeQuery("CALL sp_c_ObtenerIdUsuario('" + user + "')");
            query.setParameter(1, user);
            idUsuario = (Integer) query.getSingleResult();

            etm.getTransaction().commit();
        } catch (Exception ex) {
            if (etm.getTransaction().isActive()) {
                etm.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            etm.clear();
            etm.close();
        }
        return idUsuario;
    }
    
    public boolean ActualizarEstadoIntentosYBloqueo(int idUsuario, int nuevoIntentos) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query query = etm.createNativeQuery("CALL ActualizarEstadoIntentosYBloqueo('" + idUsuario + "', '" + nuevoIntentos + "')");
            query.setParameter(1, idUsuario);
            query.setParameter(2, nuevoIntentos);
            query.executeUpdate();
            etm.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (etm.getTransaction().isActive()) {
                etm.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        } finally {
            etm.clear();
            etm.close();
        }
    }
    
    public boolean ActualizarIntentos(String p_user, int p_intentos) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL sp_a_actualizar_intentos_usuario('" + p_user + "', '" + p_intentos + "')");
            q.setParameter(1, p_user);
            q.setParameter(2, p_intentos);
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            return exitoso > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List CalcularTiempoBloqueoUsuario(int idUsuario) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL sp_Calcular_TiempoBloqueo_usuario('" + idUsuario + "')");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (consulta.isEmpty()) {
                return null;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    
    public boolean DesbloquearUsuario(int idUsuario) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query query = etm.createNativeQuery("CALL sp_a_desbloqueo_usuario(" + idUsuario + ")");
            query.executeUpdate();
            etm.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (etm.getTransaction().isActive()) {
                etm.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        } finally {
            etm.clear();
            etm.close();
        }
    }

    public List ConsultaUsuarios() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_usa_c_usuarios`()");
            List resultados = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultados != null) {
                return resultados;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List ConsultarParametros_xCargo(String cargo) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_prm_c_ConsultarParametrosxCargo`('" + cargo + "')");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (consulta.isEmpty()) {
                return null;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public List ConsultaAreas() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_ara_c_areas`()");
            List resultados = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultados != null) {
                return resultados;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List ConsultaUsuarioId(int idusuario) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_usa_c_usuario_id`('" + idusuario + "')");
            List resultado = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado != null) {
                return resultado;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List ConsultaUsuariosFiltro(String filtro) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_usa_c_usuarios_filtro`('" + filtro + "')");
            List resultado = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado != null) {
                return resultado;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean RegistroUsuario(String nombre, String apellido, int documento, int codigo, String usuario, int rol, int area, String cargo, String correo, String Usuario_registro) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_usa_r_usuario`('" + nombre + "','" + apellido + "','" + documento + "','" + codigo + "','" + usuario + "','" + rol + "','" + area + "','" + cargo + "','" + correo + "','" + Usuario_registro + "')");
            int resultado = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ModificarUsuario(int idUsuario, String nombre, String apellido, int documento, int codigo, String usuario, int rol, int area, String cargo, String correo, String Usuario_registro) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_usa_m_usuario`('" + idUsuario + "','" + nombre + "','" + apellido + "','" + documento + "','" + codigo + "','" + usuario + "','" + rol + "','" + area + "','" + cargo + "','" + correo + "','" + Usuario_registro + "')");
            int resultado = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean cambiarEstado_Usuario(int idUsuario, int estado) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_usu_m_cambiarEstado`('" + idUsuario + "','" + estado + "')");
            int resultado = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Restablecer_password(int ius) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("UPDATE usuario SET password = YEAR(CURDATE()) WHERE id_usuario = " + ius);
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean Cambiar_password(int ius, String pwd) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("UPDATE usuario SET password = '" + pwd + "'WHERE id_usuario = " + ius);
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean Editar_usuario(int idusu, int idrol, int idarea, String nomb, String apell, int doc, int cod, String usua, String carg, String correo, String usu_reg) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_u_Editar_usuario`('" + idusu + "','" + idrol + "','" + idarea + "','" + nomb + "','" + apell + "','" + doc + "','" + cod + "','" + usua + "','" + carg + "','" + correo + "','" + usu_reg + "')");
            int resultado = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public List Firma(int documento, int codigo) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL sp_usa_c_usuario_firma('" + documento + "', '" + codigo + "')");
            List resultados = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (!resultados.isEmpty()) {
                return resultados;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean Consultar_firma(int documento, int codigo) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_fatl_C_Ficha_tec_Consultar_usuarios_firma`('" + documento + "', '" + codigo + "')");
            int resultados = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultados == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public List Areas() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_area_c_areas`()");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (consulta.isEmpty()) {
                return null;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
