package domain.Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mappers.IMapResultSetToEntity;
import domain.Entity;
import domain.IHaveId;
import domain.Repositories.UserRepository;
import domain.uow.IUnitOfWork;
import domain.uow.IUnitOfWorkRepository;

public abstract class RepositoryBase<TEntity extends IHaveId> 
implements IUnitOfWorkRepository, IRepository<TEntity>{

	protected Connection connection;
	protected Statement createTable;
	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement get;
	protected PreparedStatement list;
	
	protected IMapResultSetToEntity<TEntity> mapper;
	protected IUnitOfWork uow;
	
	public RepositoryBase(Connection connection,
			IMapResultSetToEntity<TEntity> mapper,
			IUnitOfWork uow){
		
		try{
			
			this.uow = uow;
			this.mapper = mapper;
			this.connection = connection;
			createTable = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
			boolean tableExists = false;
			while (rs.next()){
				if(tableName().equalsIgnoreCase(rs.getString("TABLE_NAME"))){
					tableExists = true;
					break;
				}				
			}
			if(!tableExists)
				createTable.executeUpdate(createTableSql());
			
			insert = connection.prepareStatement(insertSql());
			delete = connection.prepareStatement(deleteSql());
			update = connection.prepareStatement(updateSql());
			get = connection.prepareStatement(getSql());
			list = connection.prepareStatement(listSql());
			
			
		}catch (SQLException e){
			
			e.printStackTrace();
		}		
	}
	
	
	public void persistDelete(Entity u){
		try{
			delete.setInt(1, u.getId());
			delete.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}				
	}
	
	public void delete(TEntity entity){
		uow.marAsDeleted((Entity)entity, this);
	}
	
	public void persistUpdate(Entity u){
		try{
			setUpdateQuery((TEntity)u);
			update.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}				
	}
	
	public void update(TEntity entity){
		uow.marAsChanged((Entity)entity, this);
	}
	
	public void persistAdd(Entity u){
		try {
			setInsertQuery((TEntity)u);
			insert.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(TEntity entity){
		uow.marAsNew((Entity)entity, this);
	}
	
	
	public List<TEntity> getAll() {
		List<TEntity> users = new ArrayList<TEntity>();
		
		try {
			ResultSet rs = list.executeQuery();
			
			while(rs.next()){
				users.add(mapper.map(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public TEntity get(int id){
		
		try {
			get.setInt(1, id);
			ResultSet rs = get.executeQuery();
			rs.next();
			return mapper.map(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	protected String deleteSql() {
		return "DELETE FROM " + tableName() + " WHERE id=?";
	}

	protected String getSql() {
		return "SELECT * FROM " + tableName() + " WHERE id = ?";
	}

	protected String listSql() {
		return "SELECT * FROM " + tableName();
	}

	
	protected abstract void setUpdateQuery(TEntity p) throws SQLException;

	protected abstract void setInsertQuery(TEntity p) throws SQLException;

	protected abstract String tableName();

	protected abstract String createTableSql();

	protected abstract String insertSql();

	protected abstract String updateSql();
	

}
