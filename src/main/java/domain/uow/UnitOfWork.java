package domain.uow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import domain.Entity;
import domain.EntityState;

public class UnitOfWork implements IUnitOfWork{
	
	private Connection connection;
	
	public UnitOfWork(Connection connection) throws SQLException {
		this.connection = connection;
		this.connection.setAutoCommit(false);				
	}

	private Map<Entity, IUnitOfWorkRepository> entities = 
			new LinkedHashMap<Entity, IUnitOfWorkRepository>();

	public void saveChanges() {
		// TODO Auto-generated method stub
		for(Entity entity : entities.keySet()){
			
			switch(entity.getState()){
			case Deleted:
				entities.get(entity).persistDelete(entity);
				break;
			case Changed:
				entities.get(entity).persistUpdate(entity);
				break;
			case New:
				entities.get(entity).persistAdd(entity);
			
			default:
				break;
			
			}
			
		}
		
		
		try {
			connection.commit();
			entities.clear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void undo() {
		// TODO Auto-generated method stub
		try {
			connection.rollback();
			entities.clear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void marAsNew(Entity entity, IUnitOfWorkRepository repository) {
		// TODO Auto-generated method stub
		entity.setState(EntityState.New);
		entities.put(entity, repository);
		
	}

	public void marAsDeleted(Entity entity, IUnitOfWorkRepository repository) {
		// TODO Auto-generated method stub
		entity.setState(EntityState.Deleted);
		entities.put(entity, repository);
	}

	public void marAsChanged(Entity entity, IUnitOfWorkRepository repository) {
		// TODO Auto-generated method stub
		entity.setState(EntityState.Changed);
		entities.put(entity, repository);
	}
	
	
	
}
