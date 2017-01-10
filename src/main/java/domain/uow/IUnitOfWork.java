package domain.uow;

import domain.Entity;

public interface IUnitOfWork {
	
	public void saveChanges();
	public void undo();
	public void marAsNew(Entity entity, IUnitOfWorkRepository repository);
	public void marAsDeleted(Entity entity, IUnitOfWorkRepository repository);
	public void marAsChanged(Entity entity, IUnitOfWorkRepository repository);
}
