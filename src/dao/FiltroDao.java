/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class FiltroDao implements metodos<Filtro> {
    
    private static final String SQL_INSERT = "INSERT INTO filtros_aceites (codFiltro,marca,stock,existencia) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE filtros_aceite SET marca = ?, stock = ? , existencia = ? WHERE codFiltro = ?";
    private static final String SQL_DELETE = "DELETE FROM  filtros_aceite WHERE codFiltros = ?";
    private static final String SQL_READ = "SELECT * FROM filstros_aceite WHERE codFiltros = ?";
    private static final String SQL_READALL = "SELECT * FROM filstros_aceite";
    private static final Conexion con = Conexion.conectar();
    
    
    @Override
    public boolean create(Filtro g) {
        
        PreparedStatement ps;
      try{
          ps = con.getCnx().prepareStatement(SQL_INSERT);
          ps.setString(1, g.getCodigo());
          ps.setString(2, g.getMarca());
          ps.setInt(3, g.getStock());
          ps.setBoolean(4, true);
          if (ps.executeUpdate()>0){
              return true;
          }
      }catch(SQLException ex){
          System.out.println(ex.getMessage());
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
      }finally {
          con.cerrarConexion();
      }
      return false;
    }

    @Override
    public boolean delete(Object key) {
       
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            
            if (ps.executeUpdate()>0){
              return true;
            }        
        }catch(SQLException ex){
          System.out.println(ex.getMessage());
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        
        PreparedStatement ps;
        try {
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(4, c.getCodigo());
            ps.setString(1, c.getMarca());
            ps.setInt(2, c.getStock());
            ps.setBoolean(3, c.isExcistencia());
            
            if (ps.executeUpdate()>0){
                return true;
            }
            
        
        }
        
    }

    @Override
    public Filtro read(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Filtro> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
